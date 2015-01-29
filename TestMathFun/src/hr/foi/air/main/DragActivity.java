package hr.foi.air.main;

import hr.foi.air.generator.Question_generator;
import hr.foi.air.generator.Questions;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.gc.materialdesign.views.ButtonRectangle;

import air.testmathfun.R;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DragActivity extends BaseActivity implements OnTouchListener,
		OnClickListener, OnGlobalLayoutListener {

	private final short NO_POSITION = -1;
	private int numOfQuestions = 10;
	private int levelNo = 1;
	private boolean finished = false;
	private SecureRandom sRnd = new SecureRandom();
	private List<Rect> registeredLocs = new ArrayList<Rect>();
	private List<Rect> initialPositions = new ArrayList<Rect>();

	private short questionNum = 0;
	private Questions[] questions = new Questions[1];

	private boolean isTaskPositioned = false;
	private RelativeLayout layout = null;
	private int previousRawX = 0;
	private int previousRawY = 0;
	private int initialBitmapSize = 0;
	private int middleLayVertCenter = 0;

	private boolean goneOutLeft = false;
	private boolean goneOutRight = false;
	private int goneOutX = -1;
	private boolean goneOutTop = false;
	private boolean goneOutDown = false;
	private int goneOutY = -1;

	private Timer timer = null;
	private TextView tvPassedTime = null;
	private int secondsPassed = 0;
	private String passedTimeString = null;

	private int numOfCorrectAnswers = 0;

	private List<ObjToShow> questionChars = null;

	/**
	 * Represent object which needs to be shown
	 *
	 */
	class ObjToShow {
		private boolean isFixed;
		private String text;

		public ObjToShow() {
		}

		public ObjToShow(String text, boolean isFixed) {
			this.isFixed = isFixed;
			this.text = text;
		}
	}

	/**
	 * Image view class which represents draggable elements
	 *
	 */
	class MyImageView extends ImageView implements Comparable<MyImageView> {

		private short index;
		private short position;
		private String text;
		private boolean isFixed;

		public MyImageView(Context context) {
			super(context);
			position = -1;
		}

		public MyImageView(Context context, AttributeSet attrs) {
			super(context);
			position = -1;
		}

		public MyImageView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			position = -1;
		}

		public boolean isFixed() {
			return isFixed;
		}

		public void setFixed(boolean isFixed) {
			this.isFixed = isFixed;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public short getIndex() {
			return index;
		}

		public void setIndex(short index) {
			this.index = index;
		}

		public short getPosition() {
			return position;
		}

		public void setPosition(short position) {
			this.position = position;
		}

		@Override
		public int compareTo(MyImageView another) {
			if (this.position == another.position)
				return 0;
			else if (this.position > another.position)
				return 1;
			else
				return -1;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.drag, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void initView() {
		Bundle b = getIntent().getExtras();
		levelNo = b.getInt("razina");
		numOfQuestions = b.getInt("numOfQuestions");
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_drag);
		setQuestionsSet();
		layout = (RelativeLayout) findViewById(R.id.mainDragLayout);
		layout.getViewTreeObserver().addOnGlobalLayoutListener(this);
		ButtonRectangle bNext = (ButtonRectangle) findViewById(R.id.buttonNext);
		bNext.setOnClickListener(this);
		tvPassedTime = (TextView) findViewById(R.id.tvPassedTime);
		passedTimeString = getResources().getString(R.string.vrijeme);
		setTimer();
	}

	@Override
	public int getLayout() {
		return R.layout.activity_drag;
	}

	/**
	 * Sets questions for play
	 */
	private void setQuestionsSet() {
		ArrayList<Questions> questionList = Question_generator.generate(
				numOfQuestions, levelNo);
		questions = questionList.toArray(questions);
	}

	@Override
	public void onGlobalLayout() {
		if (!isTaskPositioned) {
			isTaskPositioned = !isTaskPositioned;
			questionChars = setQuestionOnIndex(questionNum);
			setPreview();
		}
	}

	/**
	 * Sets playing background
	 */
	private void setPreview() {
		registeredLocs.clear();
		initialPositions.clear();

		TextView tvLevelNo = (TextView) findViewById(R.id.tvLevelNo);
		tvLevelNo.setText(getResources().getString(R.string.Razina) + " "
				+ Integer.toString(levelNo));
		TextView tvQuestionNo = (TextView) findViewById(R.id.tvQuestionNum);
		tvQuestionNo.setText(getResources().getString(R.string.questionNum)
				+ " " + Integer.toString(questionNum + 1) + "/"
				+ Integer.toString(questions.length));

		RelativeLayout rl = (RelativeLayout) findViewById(R.id.middleLayout);
		if (rl.getChildCount() > 0) {
			rl.removeAllViews();
		}
		int middleLayoutHeight = rl.getHeight();
		int middleLayoutWidth = rl.getWidth();
		middleLayVertCenter = (int) (rl.getMeasuredHeight() / 2);
		initialBitmapSize = middleLayoutHeight / 6;
		int previousX = 0;

		int objectsCount = questionChars.size();
		int bmpHeight = getBitmapWidth(initialBitmapSize);
		int allBmpsTogetherSize = bmpHeight * objectsCount;
		int startPos = (middleLayoutWidth - allBmpsTogetherSize) / 2;
		short index = 0;
		for (Iterator<ObjToShow> iter = questionChars.iterator(); iter
				.hasNext();) {
			MyImageView image = new MyImageView(this);
			ObjToShow o = iter.next();
			Bitmap bmp = createBitmapFromText(o.text, initialBitmapSize,
					Color.RED);
			image.setFixed(o.isFixed);
			image.setText(o.text);
			image.setImageBitmap(bmp);
			if (!o.isFixed) {
				image.setIndex(index++);
			}
			image.setOnTouchListener(this);
			rl.addView(image);
			RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) image
					.getLayoutParams();
			if (image.isFixed) {
				rlp.leftMargin = startPos + previousX;
				rlp.topMargin = middleLayVertCenter - bmp.getHeight() / 2;
			} else {
				registeredLocs.add(new Rect(startPos + previousX,
						middleLayVertCenter - (bmpHeight / 2), startPos
								+ previousX + bmp.getWidth(),
						middleLayVertCenter + (bmpHeight / 2)));
				int srX = 0;
				int srY = 0;
				boolean ok = false;
				while (!ok) {
					srX = sRnd.nextInt();
					if (srX < 0) {
						srX = srX * (-1);
					}
					rlp.leftMargin = (srX % (rl.getWidth() - bmp.getWidth()));
					srY = sRnd.nextInt();
					if (srY < 0) {
						srY = srY * (-1);
					}
					rlp.topMargin = (srY % (rl.getHeight() - bmp.getHeight()));
					ok = true;
					if (rlp.topMargin <= middleLayVertCenter + (bmpHeight / 2)
							&& rlp.topMargin >= middleLayVertCenter
									- (bmpHeight / 2)) {
						ok = false;
					}
					if ((rlp.topMargin + bmp.getHeight()) <= middleLayVertCenter
							+ (bmpHeight / 2)
							&& (rlp.topMargin + bmp.getHeight()) >= middleLayVertCenter
									- (bmpHeight / 2)) {
						ok = false;
					}
					for (Rect r : initialPositions) {
						if (((rlp.leftMargin >= r.left && rlp.leftMargin <= r.right) || (rlp.leftMargin
								+ bmp.getWidth() >= r.left && rlp.leftMargin
								+ bmp.getWidth() <= r.right))
								&& ((rlp.topMargin >= r.top && rlp.topMargin <= r.bottom) || (rlp.topMargin
										+ bmpHeight >= r.top && rlp.topMargin
										+ bmpHeight <= r.bottom))) {
							ok = false;
						}
					}
				}
				if (rlp.leftMargin < 0)
					rlp.leftMargin = 0;
				if (rlp.topMargin < 0)
					rlp.topMargin = 0;
				initialPositions.add(new Rect(rlp.leftMargin, rlp.topMargin,
						rlp.leftMargin + bmpHeight, rlp.topMargin + bmpHeight));
			}
			previousX += bmp.getWidth();

		}
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		int X = (int) event.getRawX();
		int Y = (int) event.getRawY();
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			MyImageView miv = (MyImageView) view;
			if (miv != null) {
				if (miv.isFixed || finished)
					return false;
			}
			previousRawX = X;
			previousRawY = Y;
			goneOutLeft = false;
			goneOutTop = false;
			goneOutRight = false;
			goneOutDown = false;
			miv.setPosition(NO_POSITION);
			miv.bringToFront();
			break;
		case MotionEvent.ACTION_UP:
			MyImageView miv1 = (MyImageView) view;
			if (miv1 != null) {
				RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) miv1
						.getLayoutParams();
				short index = 0;
				for (Iterator<Rect> iter = registeredLocs.iterator(); iter
						.hasNext();) {
					Rect r = iter.next();
					if (((rlp.leftMargin >= r.left && rlp.leftMargin <= r.right) || (rlp.leftMargin
							+ miv1.getWidth() >= r.left && rlp.leftMargin
							+ miv1.getWidth() <= r.right))
							&& ((rlp.topMargin >= r.top && rlp.topMargin <= r.bottom) || (rlp.topMargin
									+ miv1.getHeight() >= r.top && rlp.topMargin
									+ miv1.getHeight() <= r.bottom))) {
						if (!isRegisteredLocFree(index)) {
							rlp.topMargin = r.top - miv1.getHeight();
						} else {
							rlp.leftMargin = r.left;
							rlp.topMargin = r.top;
							miv1.setPosition(index);
						}
						miv1.setLayoutParams(rlp);
						break;
					}
					++index;
				}
			}
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
					.getLayoutParams();
			int newStepX = 0;
			int newStepY = 0;
			if (goneOutLeft && X >= goneOutX) {
				goneOutLeft = false;
			}
			if (goneOutRight && X <= goneOutX) {
				goneOutRight = false;
			}
			if (goneOutTop && Y >= goneOutY) {
				goneOutTop = false;
			}
			if (goneOutDown && Y <= goneOutY) {
				goneOutDown = false;
			}
			if (!goneOutLeft && !goneOutRight) {
				int parentWidth = ((RelativeLayout) view.getParent())
						.getWidth();
				newStepX = X - previousRawX;
				layoutParams.leftMargin = layoutParams.leftMargin + newStepX;
				if (layoutParams.leftMargin < 0) {
					goneOutLeft = true;
					goneOutX = X + (layoutParams.leftMargin * (-1));
					layoutParams.leftMargin = 0;
					previousRawX = goneOutX;
				} else if (layoutParams.leftMargin > (parentWidth - view
						.getWidth())) {
					goneOutRight = true;
					goneOutX = X
							- ((layoutParams.leftMargin + view.getWidth()) - parentWidth);
					layoutParams.leftMargin = parentWidth - view.getWidth();
					previousRawX = goneOutX;
				} else {
					previousRawX = X;
				}
			}
			if (!goneOutTop && !goneOutDown) {
				newStepY = Y - previousRawY;
				layoutParams.topMargin = layoutParams.topMargin + newStepY;
				int parentHeight = ((RelativeLayout) view.getParent())
						.getHeight();
				if (layoutParams.topMargin < 0) {
					goneOutTop = true;
					goneOutY = Y + (layoutParams.topMargin * (-1));
					layoutParams.topMargin = 0;
					previousRawY = goneOutY;
				} else if (layoutParams.topMargin > (parentHeight - view
						.getHeight())) {
					goneOutDown = true;
					goneOutY = Y
							- ((layoutParams.topMargin + view.getHeight()) - parentHeight);
					layoutParams.topMargin = parentHeight - view.getHeight();
					previousRawY = goneOutY;
				} else {
					previousRawY = Y;
				}
			}
			layoutParams.rightMargin = -250;
			layoutParams.bottomMargin = -250;
			view.setLayoutParams(layoutParams);
			break;
		}
		return true;
	}

	/**
	 * Checks if one of registered locations is free
	 * 
	 * @param index
	 *            location index to check
	 * @return true if free, otherwise false
	 */
	private boolean isRegisteredLocFree(short index) {
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.middleLayout);
		for (int i = 0; i < rl.getChildCount(); ++i) {
			if (((MyImageView) rl.getChildAt(i)).getPosition() == index)
				return false;
		}
		return true;
	}

	/**
	 * Sets current question
	 * 
	 * @param index
	 *            current question index
	 * @return list of objects to show on screen
	 */
	private List<ObjToShow> setQuestionOnIndex(short index) {
		Questions q = questions[index];
		int[] nums = q.getNumbers();
		char[] chars = q.getSimbols();
		List<ObjToShow> questionObjects = new ArrayList<ObjToShow>();
		int numsLength = nums.length;
		int charsLength = chars.length;
		if (numsLength > charsLength) {
			int i = 0;
			for (int n : nums) {
				questionObjects.add(new ObjToShow(Integer.toString(n), false));
				if (i < charsLength) {
					questionObjects.add(new ObjToShow(Character
							.toString(chars[i]), true));
					++i;
				}
			}
		}
		return questionObjects;
	}

	/**
	 * Calculates bitmap width depending on the size of text
	 * 
	 * @param textSize
	 * @return
	 */
	private int getBitmapWidth(float textSize) {
		Typeface tf = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		Paint paint = new Paint();
		paint.setTypeface(tf);
		paint.setTextSize(textSize);
		paint.setTextAlign(Paint.Align.CENTER);
		float baseline = (int) (-paint.ascent() + 0.5f);
		return (int) (baseline + paint.descent() + 0.5f);
	}

	/**
	 * Creates bitmap from text passed
	 * 
	 * @param text
	 *            text to write on bitmap
	 * @param textSize
	 *            size of text to show
	 * @param textColor
	 *            color of text to show
	 * @return bitmap with text and color
	 */
	private Bitmap createBitmapFromText(String text, float textSize,
			int textColor) {
		Typeface tf = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		Paint paint = new Paint();
		paint.setTypeface(tf);
		paint.setTextSize(textSize);
		paint.setColor(textColor);
		paint.setTextAlign(Paint.Align.CENTER);
		float baseline = (int) (-paint.ascent() + 0.5f);
		int height = (int) (baseline + paint.descent() + 0.5f);
		Bitmap image = Bitmap.createBitmap(height, height,
				Bitmap.Config.ARGB_8888);
		if (text.length() > 2) {
			paint.setTextScaleX((float) 0.71);
		}
		Canvas canvas = new Canvas(image);
		canvas.drawText(text, height / 2, baseline, paint);
		return image;
	}

	/**
	 * Initializes timer
	 */
	private void setTimer() {
		timer = new Timer(true);
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				runOnUiThread(new Runnable() {

					/**
					 * Shows elapsed time
					 */
					@Override
					public void run() {
						++secondsPassed;
						int min = secondsPassed / 60;
						int sec = secondsPassed % 60;
						StringBuilder sb = new StringBuilder();
						if (sec < 10)
							sb.append("0");
						sb.append(sec);
						tvPassedTime.setText(passedTimeString + " "
								+ Integer.toString(min) + ":" + sb.toString());
					}
				});
			}
		}, (long) (1 * 1000), 1 * 1000);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.buttonNext:
			RelativeLayout rl = (RelativeLayout) findViewById(R.id.middleLayout);
			List<MyImageView> list = new ArrayList<MyImageView>();
			for (int i = 0; i < rl.getChildCount(); ++i) {
				MyImageView miv = (MyImageView) rl.getChildAt(i);
				if (!miv.isFixed && miv.getPosition() != NO_POSITION) {
					list.add(miv);
				}
			}
			Collections.sort(list);
			if (isAnswerCorrect(list, questionNum)) {
				++numOfCorrectAnswers;
			}
			if (questionNum == numOfQuestions - 1) {
				timer.cancel();
				finished = true;
				showResults();
				break;
			}
			questionChars = setQuestionOnIndex(++questionNum);
			setPreview();
			break;
		}
	}

	/**
	 * Checks if current answer is correct
	 * 
	 * @param numbersToCheck
	 *            list of objects to check
	 * @param questionNum
	 *            current question number
	 * @return true if correct, otherwise false
	 */
	boolean isAnswerCorrect(List<MyImageView> numbersToCheck, int questionNum) {
		Questions g = questions[questionNum];
		MyImageView[] mivs = new MyImageView[1];
		mivs = numbersToCheck.toArray(mivs);
		if (mivs.length != 3)
			return false;
		int a = Integer.parseInt(mivs[0].getText());
		int b = Integer.parseInt(mivs[1].getText());
		int r = Integer.parseInt(mivs[2].getText());
		char op = g.getSimbols()[0];
		switch (op) {
		case '+':
			if (a + b == r)
				return true;
			break;
		case '-':
			if (a - b == r)
				return true;
			break;
		case '*':
			if (a * b == r)
				return true;
			break;
		case '/':
			if (a / b == r)
				return true;
			break;
		}
		return false;
	}

	/**
	 * Shows results for current play
	 */
	void showResults() {
		Intent intent = new Intent(getBaseContext(), Result.class);
		intent.putExtra("brojTocnihOdgovora", numOfCorrectAnswers * 1000
				/ secondsPassed);
		startActivity(intent);
	}

}
