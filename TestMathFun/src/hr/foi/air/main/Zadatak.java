package hr.foi.air.main;


import hr.foi.air.fragments.Modul_1;
import hr.foi.air.fragments.Modul_2;
import hr.foi.air.fragments.Modul_3;
import hr.foi.air.generator.Generator_pitanja;
import hr.foi.air.generator.Pitanje;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.gc.materialdesign.views.ButtonRectangle;

import air.testmathfun.R;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Zadatak extends BaseActivity implements OnClickListener {
//	System.out.println(getIntent().getExtras().get("modul").toString());
//	System.out.println(getIntent().getExtras().get("razina"));	
	private final static int BROJ_PITANJA_U_RAZINI =10;
	private TextView tvRazina, tvVrijeme;
	private ArrayList<Pitanje> listaPitanja;
	private int modul, vrijeme;
	private long razina;
	private FragmentManager fManager;
	private List<Fragment> listaFragmenata;
	private ButtonRectangle btIducePitanje;
	private int trenutniFragment=0, brojTocnihOdgovora=0;
	private Intent intent;
	private Timer timer;
	
	@Override
	public int getLayout() {
		return R.layout.zadatak;
	}
	
	@Override
	public void initView() {
				
		razina=getIntent().getExtras().getLong("razina");
		modul=getIntent().getExtras().getInt("modul");
		
		fManager=getFragmentManager();
		listaFragmenata=new ArrayList<Fragment>();
		
		listaPitanja=Generator_pitanja.generiraj(BROJ_PITANJA_U_RAZINI, (int) razina);
					
		tvRazina=(TextView) findViewById(R.id.tvRazinaZadatak);
		tvVrijeme=(TextView) findViewById(R.id.tvVrijeme);
		tvRazina.setText("-Razina "+razina+"-");

		
		btIducePitanje=(ButtonRectangle) findViewById(R.id.btIducePitanje);
		btIducePitanje.setOnClickListener(this);

		postaviVrijeme();
		
		
		createFragments();

	}



	@Override
	public void onBackPressed() {
		Toast.makeText(getBaseContext(), "Morate rješiti zadatak do kraja!", Toast.LENGTH_SHORT).show();
		//super.onBackPressed();
	}
	
	
	private void createFragments() {
		if (modul==1) {
			for (int i = 0; i < BROJ_PITANJA_U_RAZINI; i++) {
				listaFragmenata.add(new Modul_1(listaPitanja.get(i)));
			}
			fManager.beginTransaction().add(R.id.container, listaFragmenata.get(trenutniFragment)).commit();
		}else if(modul==2){
			for (int i = 0; i < BROJ_PITANJA_U_RAZINI; i++) {
				listaFragmenata.add(new Modul_2(listaPitanja.get(i)));
			}
			fManager.beginTransaction().add(R.id.container, listaFragmenata.get(trenutniFragment)).commit();
		}else{
			for (int i = 0; i < BROJ_PITANJA_U_RAZINI; i++) {
				listaFragmenata.add(new Modul_3(listaPitanja.get(i)));
			}
			fManager.beginTransaction().add(R.id.container, listaFragmenata.get(trenutniFragment)).commit();
		}
		
	}
	


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btIducePitanje:
				if(Modul_2.calculate(listaPitanja.get(trenutniFragment))==true) brojTocnihOdgovora++;;
				if(trenutniFragment!=BROJ_PITANJA_U_RAZINI-1){
					fManager.beginTransaction().replace(R.id.container, listaFragmenata.get(++trenutniFragment)).commit();
				}else{
					//start result activity, this is temporary
					intent=new Intent(getBaseContext(), Rezultat.class);
					intent.putExtra("brojTocnihOdgovora", brojTocnihOdgovora*1000/vrijeme);
					startActivity(intent);
				}
			break; 

		default:
			break;
		}

	}
	
	
	private void postaviVrijeme() {
		timer=new Timer();
				
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable(){
			          public void run() 
			          { 
			        	  vrijeme++;	
			        	  tvVrijeme.setText("Vrijeme: "+Integer.toString(vrijeme));
			          }
			     }); 
				
			}
		},0,1000);
		
	}
	
	
};
