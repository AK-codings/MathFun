package hr.foi.air.main;

import java.util.Random;

import air.testmathfun.R;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Razina_1 extends BaseActivity {

	private int i = 0, broj1, broj2, tocno = 0;
	private String operacije[] = { "+", "-" };
	private String operacija;
	private boolean flag;
	private TextView tv;
	private EditText et;

	@Override
	public void initView() {
		tv = (TextView) findViewById(R.id.textView2);
		et = (EditText) findViewById(R.id.editText2);

		ispisi();

		Button bt = (Button) findViewById(R.id.button2);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flag = false;
				flag = racunaj(broj1, broj2, operacija);
				if (flag == true)
					tocno++;
				i++;

				if (i > 9) {
					String ukupno = Integer.toString(tocno);
					Intent intent = new Intent(getBaseContext(), Rezultat.class);
					intent.putExtra("rezultat", ukupno);
					startActivity(intent);
				}

				ispisi();

			}
		});
	}

	void ispisi() {
		Random crazy = new Random();
		operacija = operacije[crazy.nextInt(2)];
		broj1 = crazy.nextInt(30);
		broj2 = crazy.nextInt(30);

		et.setText("");
		tv.setText("Koliko je " + broj1 + operacija + broj2 + "?");
	}

	boolean racunaj(int broj1, int broj2, String operacija) {
		int rezultat;
		if (operacija.equals("+")) {
			rezultat = broj1 + broj2;
		} else {
			rezultat = broj1 - broj2;
		}

		String r1 = et.getText().toString();
		String r2 = Integer.toString(rezultat);

		return r1.equals(r2);
	}

	@Override
	public int getLayout() {
		return R.layout.razina_1;
	}
};
