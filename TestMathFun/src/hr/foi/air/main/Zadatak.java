package hr.foi.air.main;


import hr.foi.air.fragments.Modul_1;
import hr.foi.air.fragments.Modul_2;
import hr.foi.air.fragments.Modul_3;
import hr.foi.air.generator.Generator_pitanja;
import hr.foi.air.generator.Pitanje;

import java.util.List;


import air.testmathfun.R;
import android.app.FragmentManager;
import android.widget.TextView;
import android.widget.Toast;

public class Zadatak extends BaseActivity {
//	System.out.println(getIntent().getExtras().get("modul").toString());
//	System.out.println(getIntent().getExtras().get("razina"));	
	private TextView tvRazina;
	private List<Pitanje> listaPitanja;
	private int modul;
	private long razina;
	private FragmentManager fManager;
	
	@Override
	public int getLayout() {
		return R.layout.zadatak;
	}
	@Override
	public void initView() {
		razina=getIntent().getExtras().getLong("razina");
		modul=getIntent().getExtras().getInt("modul");
		
		fManager=getFragmentManager();
		
		listaPitanja=Generator_pitanja.generiraj(10, (int) razina);
		
		System.out.println(listaPitanja);
		
		tvRazina=(TextView) findViewById(R.id.tvRazinaZadatak);
		tvRazina.setText("-Razina "+razina+"-");
		
		if (modul==1) {
			fManager.beginTransaction().add(R.id.container, new Modul_1()).commit();
		}else if(modul==2){
			fManager.beginTransaction().add(R.id.container, new Modul_2()).commit();
		}else{
			fManager.beginTransaction().add(R.id.container, new Modul_3()).commit();
		}

		
	}

	@Override
	public void onBackPressed() {
		Toast.makeText(getBaseContext(), "Prekid zadatka", Toast.LENGTH_SHORT).show();
		super.onBackPressed();
	}

};
