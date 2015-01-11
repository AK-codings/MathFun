package hr.foi.air.main;

import hr.foi.air.fragments.Modul_yes_no;
import hr.foi.air.fragments.Modul_solve;
import hr.foi.air.fragments.Modul_drag_and_drop;
import hr.foi.air.generator.Question_generator;
import hr.foi.air.generator.Questions;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.gc.materialdesign.views.ButtonRectangle;
import air.testmathfun.R;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class Question extends BaseActivity implements OnClickListener {
	// System.out.println(getIntent().getExtras().get("modul").toString());
	// System.out.println(getIntent().getExtras().get("razina"));
	private final static int NUMBER_OF_QUESTIONS = 10;
	private TextView tvLevel, tvTime;
	private ArrayList<Questions> questionList;
	private int modul, time;
	private long level;
	private FragmentManager fManager;
	private List<Fragment> fragmentList;
	private ButtonRectangle btNextQuestion, btCorrect, btIncorrect;
	private int currentFragment = 0, numberOfCorrectAnswers = 0;
	private Intent intent;
	private Timer timer;

	@Override
	public int getLayout() {
		return R.layout.zadatak;
	}

	@Override
	public void initView() {

		level = getIntent().getExtras().getLong("razina");
		modul = getIntent().getExtras().getInt("modul");

		fManager = getFragmentManager();
		fragmentList = new ArrayList<Fragment>();

		questionList = Question_generator.generate(NUMBER_OF_QUESTIONS,
				(int) level);

		tvLevel = (TextView) findViewById(R.id.tvRazinaZadatak);
		tvTime = (TextView) findViewById(R.id.tvVrijeme);
		tvLevel.setText("-Razina " + level + "-");

		btNextQuestion = (ButtonRectangle) findViewById(R.id.btIducePitanje);
		btCorrect=(ButtonRectangle) findViewById(R.id.btTocno);
		btIncorrect=(ButtonRectangle) findViewById(R.id.btNetocno);
		
		btNextQuestion.setOnClickListener(this);
		btCorrect.setOnClickListener(this);
		btIncorrect.setOnClickListener(this);
		
		postaviVrijeme();

		createFragments();

	}

	@Override
	public void onBackPressed() {
		Toast.makeText(getBaseContext(), "Morate rješiti zadatak do kraja!",
				Toast.LENGTH_SHORT).show();
		 super.onBackPressed();
	}

	private void createFragments() {
		if (modul == 1) {
			for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
				fragmentList.add(new Modul_yes_no(questionList.get(i)));
			}
			fManager.beginTransaction()
					.add(R.id.container, fragmentList.get(currentFragment))
					.commit();
			btNextQuestion.setVisibility(View.GONE);
		} else if (modul == 2) {
			for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
				fragmentList.add(new Modul_solve(questionList.get(i)));
			}
			fManager.beginTransaction()
					.add(R.id.container, fragmentList.get(currentFragment))
					.commit();
			btCorrect.setVisibility(View.GONE);
			btIncorrect.setVisibility(View.GONE);
		} else {
			for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
				fragmentList.add(new Modul_drag_and_drop(questionList.get(i)));
			}
			fManager.beginTransaction()
					.add(R.id.container, fragmentList.get(currentFragment))
					.commit();
			btNextQuestion.setVisibility(View.GONE);
			btCorrect.setVisibility(View.GONE);
			btIncorrect.setVisibility(View.GONE);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btIducePitanje:
				if (Modul_solve.calculate(questionList.get(currentFragment)) == true)
					numberOfCorrectAnswers++;
			break;
		case R.id.btTocno:
			if (Modul_yes_no.calculate(questionList.get(currentFragment), "tocno") == true)
				numberOfCorrectAnswers++;
		break;
		case R.id.btNetocno:
			if (Modul_yes_no.calculate(questionList.get(currentFragment), "netocno") == true)
				numberOfCorrectAnswers++;
		break;
		default:
			break;
		}

		provjeriBrojOdgovora();
		
	}

	private void provjeriBrojOdgovora() {
		if (currentFragment != NUMBER_OF_QUESTIONS - 1) {
			fManager.beginTransaction()
					.replace(R.id.container,
							fragmentList.get(++currentFragment))
					.commit();
		} else {
			// start result activity, this is temporary
			intent = new Intent(getBaseContext(), Result.class);
			intent.putExtra("brojTocnihOdgovora", numberOfCorrectAnswers * 1000
					/ time);
			startActivity(intent);
		}
	}

	private void postaviVrijeme() {
		timer = new Timer();

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					public void run() {
						time++;
						tvTime.setText("Vrijeme: "
								+ Integer.toString(time));
					}
				});

			}
		}, 0, 1000);

	}

};
