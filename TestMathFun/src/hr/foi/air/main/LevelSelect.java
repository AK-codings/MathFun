package hr.foi.air.main;

import com.gc.materialdesign.views.ButtonRectangle;

import hr.foi.air.adapter.LevelsAdapter;
import hr.foi.air.db.Difficulties;
import hr.foi.air.db.Modules;
import air.testmathfun.R;
import android.app.Dialog;
import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class LevelSelect extends BaseActivity implements OnItemClickListener, OnClickListener{
	//private List<Difficulties> razine;
	private Difficulties difficulties;
	private LevelsAdapter adapter;
	private ListView lvLevels;
	private Dialog dialog;
	private ButtonRectangle btModul1, btModul2, btModul3;
	private TextView tvLevelName;
	private long level;
	private Intent intent;
	public final static String KEY_MODULE="modul", KEY_RAZINA="razina";
	
	/**
	 * Metoda koja povezuje layout
	 */
	@Override
	public int getLayout() {
		return R.layout.odabir_razine;
	}
	
	/**
	 * Metoda koja se izvršava nakon povezivanja layouta
	 */
	@Override
	public void initView() {

		Toast.makeText(this, "Igraè: "+getIntent().getExtras().getString("ime"), Toast.LENGTH_SHORT).show();
		
		adapter=new LevelsAdapter(getBaseContext(), Difficulties.getAllDifficulties());
		lvLevels=(ListView) findViewById(R.id.lvRazine);
		lvLevels.setAdapter(adapter);
		
		lvLevels.setOnItemClickListener(this);
		
	}

	/**
	 * Osluškuje klik na listViewu za razinu igre
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Difficulties.setAllToInactive();
		difficulties=(Difficulties) lvLevels.getItemAtPosition(position);
		level=difficulties.getId();
		difficulties.setActive(1);
		difficulties.save();
		createDialog(difficulties.getName());
	}

	/**
	 * Metoda koja stvara dialog za odabir Modula ovisno o razini (drag and drop, yes/no, solve)
	 * @param difficultiesName
	 */
	private void createDialog(String difficultiesName) {
		dialog=new Dialog(this);
		dialog.setContentView(R.layout.modules_dialog);
		dialog.setTitle("Odaberi vrstu zadatka");
		dialog.show();
		
		btModul1=(ButtonRectangle) dialog.findViewById(R.id.btModul1);
		btModul2=(ButtonRectangle) dialog.findViewById(R.id.btModul2);
		btModul3=(ButtonRectangle) dialog.findViewById(R.id.btModul3);
		tvLevelName=(TextView) dialog.findViewById(R.id.tvImeRazine);
		
		tvLevelName.setText("-"+difficultiesName+"-");
		
		btModul1.setOnClickListener(this);
		btModul2.setOnClickListener(this);
		btModul3.setOnClickListener(this);

		
	}

	/**
	 * Osluškuje klik na button u dialogu za odabir modula
	 */
	@Override
	public void onClick(View v) {
		Modules.setAllToInactive();
		intent=new Intent(getBaseContext(), Question.class);
		intent.putExtra(LevelSelect.KEY_RAZINA, level);
		
		switch (v.getId()) {
		case R.id.btModul1:
			Modules.setToActive("Modul_1");
			intent.putExtra(LevelSelect.KEY_MODULE, 1);
			startActivity(intent);
			break;
		case R.id.btModul2:
			Modules.setToActive("Modul_2");
			intent.putExtra(LevelSelect.KEY_MODULE, 2);
			startActivity(intent);
			break;
		case R.id.btModul3:
			Toast.makeText(getBaseContext(), "Trenutno nije u funkciji!", Toast.LENGTH_SHORT).show();
//			Modules.setToActive("Modul_3");
//			intent.putExtra(KEY_MODULE, 3);
//			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
