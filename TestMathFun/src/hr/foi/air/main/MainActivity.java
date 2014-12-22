package hr.foi.air.main;

import java.util.Date;

import com.gc.materialdesign.views.ButtonRectangle;

import air.testmathfun.R;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends BaseActivity implements OnClickListener {
	private ButtonRectangle btIgraj;
	private ButtonRectangle btPravila;
	
	@Override
	public int getLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void initView() {
		btIgraj = (ButtonRectangle) findViewById(R.id.btIgraj);
		btPravila = (ButtonRectangle) findViewById(R.id.btPravila);
		btIgraj.setOnClickListener(this);			
		btPravila.setOnClickListener(this);
		

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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btIgraj:
			startActivity(new Intent(getBaseContext(), Nickname.class));
			break;
		case R.id.btPravila:
			startActivity(new Intent(getBaseContext(), Pravila.class));
			break;
		default:
			break;
		}
		
	}

}
