package hr.foi.air.main;

import air.testmathfun.R;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends BaseActivity {
	
	@Override
	public int getLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void initView() {
		Button btPromjeni = (Button) findViewById(R.id.button1);
		Button btnPravila = (Button) findViewById(R.id.btnPravila);

		btPromjeni.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getBaseContext(), Nickname.class));
			}
		});

		btnPravila.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getBaseContext(), Pravila.class));
			}
		});

		// Thread thread = new Thread(){
		// public void run() {
		// try {
		// MediaPlayer mp=new MediaPlayer().create(getBaseContext(),
		// R.raw.math);
		// mp.start();
		// Thread.sleep(4000);
		// Intent intent=new Intent(getBaseContext(), nickname.class);
		// startActivity(intent);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// };
		// };
		// thread.start();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

}
