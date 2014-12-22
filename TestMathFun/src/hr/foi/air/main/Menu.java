package hr.foi.air.main;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Menu extends ListActivity {
	String []menu= {"Razina_1", "Nesto_drugo", "Nesto_trece", "Nesto_cetvrto", "Nesto_peto", "Nesto_sesto", "Nesto_sedmo", "Nesto_osmo", "Nesto_deveto", "Nesto_deseto"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		
		Toast.makeText(getBaseContext(), "Dobrodošli: "+getIntent().getExtras().getString("ime"), Toast.LENGTH_LONG).show();
		
		setListAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, menu));
		
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String kliknuto=menu[position];
		try {
			Class myClass = Class.forName("hr.foi.air.main."+kliknuto);
			Intent intent=new Intent(getBaseContext(), Razina_1.class);
			startActivity(intent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
