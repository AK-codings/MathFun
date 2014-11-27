package hr.foi.air.main;

import air.testmathfun.R;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.TextView;

public class Rezultat extends BaseActivity {

	@Override
	public int getLayout() {
		return R.layout.rezultat;
	}

	@Override
	public void initView() {
		TextView tv = (TextView) findViewById(R.id.textView4);

		tv.setText(getResources().getString(R.string.result_part_1)
				+ getIntent().getExtras().getString("rezultat")
				+ " od ukupno 10 bodova!");

		Thread thread = new Thread() {
			public void run() {
				try {
					MediaPlayer mp = new MediaPlayer().create(getBaseContext(),
							R.raw.cgg);
					mp.start();
					Thread.sleep(5000);
					Intent intent = new Intent(getBaseContext(), Nickname.class);
					startActivity(intent);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		};
		thread.start();
	}
}
