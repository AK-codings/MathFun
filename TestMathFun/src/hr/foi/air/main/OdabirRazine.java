package hr.foi.air.main;

import hr.foi.air.adapter.RazineAdapter;
import hr.foi.air.db.Difficulties;
import air.testmathfun.R;
import android.widget.ListView;

public class OdabirRazine extends BaseActivity {
	RazineAdapter adapter;
	ListView razine;
	
	@Override
	public int getLayout() {
		return R.layout.odabir_razine;
	}
	
	@Override
	public void initView() {
		adapter=new RazineAdapter(getBaseContext(), Difficulties.createDifficultiesList());
		razine=(ListView) findViewById(R.id.lvRazine);
		razine.setAdapter(adapter);
	}



}
