package hr.foi.air.main;

import hr.foi.air.db.Difficulties;
import hr.foi.air.db.Highscore;
import hr.foi.air.db.Modules;
import hr.foi.air.db.Users;

import java.util.ArrayList;
import java.util.List;

import com.gc.materialdesign.views.ButtonRectangle;

import air.testmathfun.R;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Rezultat extends BaseActivity implements OnClickListener {
	private Users users;
	private Modules modules;
	private Difficulties difficulties;
	private List<Highscore> listaRezultata;
	private Highscore highscore;
	private int rezultat;
	private boolean flag=false;
	private TextView tvRazina,tvModul;
	private ListView lvNajboljiIgraci, lvNajboljiRezultati;
	private ButtonRectangle btNatrag;
	private ArrayList<String> listaNajboljihIgraca, listaNajboljihRezultata;
	private ArrayAdapter<String> aaNajboljiRezultati, aaNajboljiIgraci;
	
	@Override
	public int getLayout() {
		return R.layout.rezultat;
	}

	@Override
	public void initView() {
		rezultat=getIntent().getExtras().getInt("brojTocnihOdgovora");
		
		users=Users.getActiveUsers();
		modules=Modules.getActiveModules();
		difficulties=Difficulties.getActiveDifficulties();
		listaRezultata=Highscore.getBestFiveResults(difficulties);
		
		tvRazina=(TextView) findViewById(R.id.tvRazina);
		tvModul=(TextView) findViewById(R.id.tvModul);
		lvNajboljiIgraci=(ListView) findViewById(R.id.lvNajboljiIgraci);
		lvNajboljiRezultati=(ListView) findViewById(R.id.lvNajboljiRezultati);
		btNatrag=(ButtonRectangle) findViewById(R.id.btNatrag);
		
		btNatrag.setOnClickListener(this);
		
		tvRazina.setText("-Razina "+ difficulties.getId() +"-");
		tvModul.setText(modules.getOpis());
		
				
		provjeriNajboljeRezultate();
		ispisiRezultate();
	}

	private void ispisiRezultate() {
		listaRezultata=Highscore.getBestFiveResults(difficulties);
		
		listaNajboljihIgraca=new ArrayList<String>();
		listaNajboljihRezultata=new ArrayList<String>();
		
		for (int i = 0; i < listaRezultata.size(); i++) {
			listaNajboljihRezultata.add(Integer.toString(listaRezultata.get(i).getHighscore()));	
			listaNajboljihIgraca.add(listaRezultata.get(i).getUser_id().getName());
		}
		
		aaNajboljiRezultati=new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, listaNajboljihRezultata);
		aaNajboljiIgraci=new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, listaNajboljihIgraca);
		
		lvNajboljiIgraci.setAdapter(aaNajboljiIgraci);
		lvNajboljiRezultati.setAdapter(aaNajboljiRezultati);
		
	}

	private void provjeriNajboljeRezultate() {
		
		for (int i = 0; i < listaRezultata.size(); i++) {
			if (listaRezultata.get(i).getHighscore()<rezultat) {
				flag=true;
			}
		}
		if (listaRezultata.size()<5) {
			highscore=new Highscore(rezultat, users, modules, difficulties);
			highscore.save();
		}else if(flag){
			System.out.println("DA");
			Highscore.updateHightlist(modules, difficulties, users, rezultat);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btNatrag:
			startActivity(new Intent(getBaseContext(), MainActivity.class));
			break;

		default:
			break;
		}
		
	}
	@Override
	public void onBackPressed() {
		startActivity(new Intent(getBaseContext(), MainActivity.class));
	}
}
