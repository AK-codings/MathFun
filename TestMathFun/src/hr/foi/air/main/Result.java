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
import android.opengl.Visibility;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends BaseActivity implements OnClickListener {
	private Users users;
	private Modules modules;
	private Difficulties difficulties;
	private List<Highscore> resultList;
	private Highscore highscore;
	private int result, sviRezultati;
	private boolean flag = false;
	private TextView tvLevel, tvModul;
	private ListView lvBestPlayer, lvBestResaut;
	private ButtonRectangle btBack;
	private ArrayList<String> bestPlayersList, bestResultList;
	private ArrayAdapter<String> aaBestResoult, aaBestPlayer;

	/**
	 * Metoda za povezivanje layouta
	 */
	@Override
	public int getLayout() {
		return R.layout.rezultat;
	}

	/**
	 * Metoda koja se izvršava nakon povezivanja layouta
	 */
	@Override
	public void initView() {
		sviRezultati = getIntent().getExtras().getInt("sviRezultati");

		tvLevel = (TextView) findViewById(R.id.tvRazina);
		tvModul = (TextView) findViewById(R.id.tvModul);

		lvBestPlayer = (ListView) findViewById(R.id.lvNajboljiIgraci);
		lvBestResaut = (ListView) findViewById(R.id.lvNajboljiRezultati);
		btBack = (ButtonRectangle) findViewById(R.id.btNatrag);
		btBack.setOnClickListener(this);

		if (sviRezultati == 1) {
			tvLevel.setVisibility(View.GONE);
			tvModul.setVisibility(View.GONE);
			Toast.makeText(getBaseContext(), "Ispis svih najboljih rezultata",
					Toast.LENGTH_SHORT).show();
		} else {
			result = getIntent().getExtras().getInt("brojTocnihOdgovora");

			users = Users.getActiveUsers();
			modules = Modules.getActiveModules();
			difficulties = Difficulties.getActiveDifficulties();

			tvLevel.setText("-Razina " + difficulties.getId() + "-");
			tvModul.setText(modules.getOpis());

			Toast.makeText(getBaseContext(), "Vaš rezultat: " + result,
					Toast.LENGTH_SHORT).show();
		}

		provjeriNajboljeRezultate();
		ispisiRezultate();

	}

	/**
	 * Metoda koja ispisuje najboljih pet rezultata iz baze
	 */
	private void ispisiRezultate() {
		if (sviRezultati == 1) {
			resultList = Highscore.getBestFiveResults();
		} else {
			resultList = Highscore.getBestFiveResults(difficulties, modules);
		}

		bestPlayersList = new ArrayList<String>();
		bestResultList = new ArrayList<String>();

		for (int i = 0; i < resultList.size(); i++) {
			bestResultList.add(Integer.toString(resultList.get(i)
					.getHighscore()));
			bestPlayersList.add(resultList.get(i).getUser_id().getName());
		}

		aaBestResoult = new ArrayAdapter<String>(getBaseContext(),
				android.R.layout.simple_dropdown_item_1line, bestResultList);
		aaBestPlayer = new ArrayAdapter<String>(getBaseContext(),
				android.R.layout.simple_dropdown_item_1line, bestPlayersList);

		lvBestPlayer.setAdapter(aaBestPlayer);
		lvBestResaut.setAdapter(aaBestResoult);

	}

	/**
	 * Metoda koja provjerava dali je naš rezultat bolji od najboljih 5
	 * rezultata na tom modulu i razini, te ako je unosi rezultat u bazu
	 */
	private void provjeriNajboljeRezultate() {

		if (sviRezultati == 1) {
			resultList = Highscore.getBestFiveResults();
		} else {
			resultList = Highscore.getBestFiveResults(difficulties, modules);
		}

		for (int i = 0; i < resultList.size(); i++) {
			if (resultList.get(i).getHighscore() < result) {
				flag = true;
			}
		}
		if (resultList.size() < 5) {
			highscore = new Highscore(result, users, modules, difficulties);
			highscore.save();
		} else if (flag) {
			Highscore.updateHightlist(modules, difficulties, users, result);
		}
	}

	/**
	 * Osluskuje klik na button
	 */
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

	/**
	 * Metoda za kontrolu tipke back
	 */
	@Override
	public void onBackPressed() {
		startActivity(new Intent(getBaseContext(), MainActivity.class));
	}
}
