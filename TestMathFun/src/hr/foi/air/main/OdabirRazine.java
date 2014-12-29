package hr.foi.air.main;

import java.util.ArrayList;
import java.util.List;

import com.gc.materialdesign.views.ButtonRectangle;

import hr.foi.air.adapter.RazineAdapter;
import hr.foi.air.db.Difficulties;
import hr.foi.air.db.Modules;
import hr.foi.air.generator.Generator_pitanja;
import hr.foi.air.generator.Pitanje;
import air.testmathfun.R;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class OdabirRazine extends BaseActivity implements OnItemClickListener, OnClickListener{
	private List<Difficulties> razine;
	private Difficulties difficulties;
	private RazineAdapter adapter;
	private ListView lvRazine;
	private Dialog dialog;
	private ButtonRectangle btModul1, btModul2, btModul3;
	private TextView tvImeRazine;
	private long razina;
	private Intent intent;
	
	@Override
	public int getLayout() {
		return R.layout.odabir_razine;
	}
	
	@Override
	public void initView() {

		Toast.makeText(this, "Igraè: "+getIntent().getExtras().getString("ime"), Toast.LENGTH_SHORT).show();
		
		adapter=new RazineAdapter(getBaseContext(), Difficulties.getAllDifficulties());
		lvRazine=(ListView) findViewById(R.id.lvRazine);
		lvRazine.setAdapter(adapter);
		
		lvRazine.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Difficulties.setAllToInactive();
		difficulties=(Difficulties) lvRazine.getItemAtPosition(position);
		razina=difficulties.getId();
		difficulties.setActive(1);
		difficulties.save();
		createDialog(difficulties.getName());
	}

	private void createDialog(String difficultiesName) {
		dialog=new Dialog(this);
		dialog.setContentView(R.layout.modules_dialog);
		dialog.setTitle("Odaberi vrstu zadatka");
		dialog.show();
		
		btModul1=(ButtonRectangle) dialog.findViewById(R.id.btModul1);
		btModul2=(ButtonRectangle) dialog.findViewById(R.id.btModul2);
		btModul3=(ButtonRectangle) dialog.findViewById(R.id.btModul3);
		tvImeRazine=(TextView) dialog.findViewById(R.id.tvImeRazine);
		
		tvImeRazine.setText("-"+difficultiesName+"-");
		
		btModul1.setOnClickListener(this);
		btModul2.setOnClickListener(this);
		btModul3.setOnClickListener(this);

		
	}

	@Override
	public void onClick(View v) {
		Modules.setAllToInactive();
		intent=new Intent(getBaseContext(), Zadatak.class);
		intent.putExtra("razina", razina);
		
		switch (v.getId()) {
		case R.id.btModul1:
			Modules.setToActive("Modul_1");
			intent.putExtra("modul", 1);
			startActivity(intent);
			break;
		case R.id.btModul2:
			Modules.setToActive("Modul_2");
			intent.putExtra("modul", 2);
			startActivity(intent);
			break;
		case R.id.btModul3:
			Toast.makeText(getBaseContext(), "Trenutno nije u funkciji!", Toast.LENGTH_SHORT).show();
//			Modules.setToActive("Modul_3");
//			intent.putExtra("modul", 3);
//			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
