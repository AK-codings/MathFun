package air.testmathfun;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class rezultat extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rezultat);
		TextView tv=(TextView) findViewById(R.id.textView4);
		
		tv.setText("Cestitamo, imali ste "+getIntent().getExtras().getString("rezultat")+" od ukupno 10 bodova!");
		
		Thread thread = new Thread(){
			public void run() {
				try {
					MediaPlayer mp=new MediaPlayer().create(getBaseContext(), R.raw.cgg);
					mp.start();
					Thread.sleep(5000);
					Intent intent=new Intent(getBaseContext(), nickname.class);
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
