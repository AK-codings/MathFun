package air.testmathfun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class nickname extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nickname);
		
		Button bt=(Button) findViewById(R.id.button1);
		final EditText et=(EditText) findViewById(R.id.editText2);
		
		
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getBaseContext(), menu.class);
				intent.putExtra("ime", et.getText().toString());
				startActivity(intent);
			}
		});
	}
}
