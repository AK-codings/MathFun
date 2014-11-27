package hr.foi.air.main;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

	public abstract void initView();

	public abstract int getLayout();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayout());
		initView();
	}
}
