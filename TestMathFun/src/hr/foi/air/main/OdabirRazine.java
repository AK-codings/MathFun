package hr.foi.air.main;

import java.util.List;

import com.gc.materialdesign.views.ButtonRectangle;

import hr.foi.air.adapter.RazineAdapter;
import hr.foi.air.db.Difficulties;
import hr.foi.air.db.Modules;
import air.testmathfun.R;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class OdabirRazine extends BaseActivity implements OnItemClickListener, OnClickListener{
	private List<Difficulties> razine;
	private Difficulties difficulties;
	private RazineAdapter adapter;
	private ListView lvRazine;
	private Dialog dialog;
	private ButtonRectangle btModul1, btModul2, btModul3;
	
	@Override
	public int getLayout() {
		return R.layout.odabir_razine;
	}
	
	@Override
	public void initView() {

		adapter=new RazineAdapter(getBaseContext(), Difficulties.getAllDifficulties());
		lvRazine=(ListView) findViewById(R.id.lvRazine);
		lvRazine.setAdapter(adapter);
		
		lvRazine.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Difficulties.setAllToInactive();
		difficulties=(Difficulties) lvRazine.getItemAtPosition(position);
		difficulties.setActive(1);
		difficulties.save();
		createDialog();
	}

	private void createDialog() {
		dialog=new Dialog(this);
		dialog.setContentView(R.layout.modules_dialog);
		dialog.setTitle("Odaberi vrstu zadatka");
		dialog.show();
		
		btModul1=(ButtonRectangle) dialog.findViewById(R.id.btModul1);
		btModul2=(ButtonRectangle) dialog.findViewById(R.id.btModul2);
		btModul3=(ButtonRectangle) dialog.findViewById(R.id.btModul3);
		
		btModul1.setOnClickListener(this);
		btModul2.setOnClickListener(this);
		btModul3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Modules.setAllToInactive();
		
		switch (v.getId()) {
		case R.id.btModul1:
			Modules.setToActive("Modul_1");
			break;
		case R.id.btModul2:
			Modules.setToActive("Modul_2");
			break;
		case R.id.btModul3:
			Modules.setToActive("Modul_3");
			break;
		default:
			break;
		}
	}
}
