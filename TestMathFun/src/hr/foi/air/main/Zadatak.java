package hr.foi.air.main;


import air.testmathfun.R;
import android.app.FragmentManager;
import android.widget.TextView;
import android.widget.Toast;

public class Zadatak extends BaseActivity {
	
//	System.out.println(getIntent().getExtras().get("modul").toString());
//	System.out.println(getIntent().getExtras().get("razina"));	
	
	private TextView tvRazina;
	
	@Override
	public int getLayout() {
		return R.layout.zadatak;
	}
	@Override
	public void initView() {
		tvRazina=(TextView) findViewById(R.id.tvRazinaZadatak);
		tvRazina.setText(getIntent().getExtras().getString("razina").toString());
		
		// working with fragments 
//		getSupportFragmentManager().beginTransaction().add(R.id.container, new Modul_1()).commit();
//		getSupportFragmentManager().beginTransaction().add(R.id.container, new Modul_2()).commit();
//		getSupportFragmentManager().beginTransaction().add(R.id.container, new Modul_3()).commit();
		
	}

	@Override
		public void onBackPressed() {
			Toast.makeText(getBaseContext(), "Morate riješiti zadatak do kraja!", Toast.LENGTH_SHORT).show();
		}
	
};
