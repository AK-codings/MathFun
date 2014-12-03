package hr.foi.air.main;



import air.testmathfun.R;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Nickname extends BaseActivity {

	@Override
	public int getLayout() {
		return R.layout.nickname;
	}

	@Override
	public void initView() {

		Button bt = (Button) findViewById(R.id.button1);
		final EditText et = (EditText) findViewById(R.id.editText2);

		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), Menu.class);
				intent.putExtra("ime", et.getText().toString());
				startActivity(intent);
			}
		});
	}
}
