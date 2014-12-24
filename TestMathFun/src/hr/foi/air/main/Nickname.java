package hr.foi.air.main;
import hr.foi.air.db.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import air.testmathfun.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;

public class Nickname extends BaseActivity implements OnClickListener, OnItemClickListener {
	private EditText et;
	private ButtonRectangle bt;
	private ListView lvIgraci;
	private AlertDialog.Builder dialog;
	private List<Users> listaIgraca;
	private ArrayList<String> alImenaIgraca;
	private ArrayAdapter<String> aaImenaIgraca;
	private Intent intent;
	private Users igrac;
	private boolean flag;
	
	@Override
	public int getLayout() {
		return R.layout.nickname;
	}

	@Override
	public void initView() {

		bt = (ButtonRectangle) findViewById(R.id.btKreni);
		et = (EditText) findViewById(R.id.etUnesiIgraca);
		lvIgraci=(ListView) findViewById(R.id.lvListIgraca);
		lvIgraci.setOnItemClickListener(this);
		bt.setOnClickListener(this);
		populateList();
		
}

	private void populateList() {
		
		listaIgraca=Users.getLastPlayers();
		alImenaIgraca=new ArrayList<String>();
		for (int i = 0; i < listaIgraca.size(); i++) {
			alImenaIgraca.add(listaIgraca.get(i).getName());
		}
		aaImenaIgraca=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alImenaIgraca);
		lvIgraci.setAdapter(aaImenaIgraca);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btKreni:
			if (et.getText().toString().trim().equals("")) {
				Toast.makeText(getBaseContext(), "Ime igraca ne moze biti prazno", Toast.LENGTH_SHORT).show();
			}else{
				makeDialog();
			}
			break;
		default:
			break;
		}
	}

	private void makeDialog() {
		dialog=new AlertDialog.Builder(this);
		dialog.setTitle("Novi igraè");
		dialog.setMessage("Da li želite stvoriti novog igraèa?");
		dialog.setPositiveButton("Da", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				flag=createPlayer();
				if(flag==true){
					intent= new Intent(getBaseContext(), OdabirRazine.class);
					intent.putExtra("ime", et.getText().toString());
					startActivity(intent);					
				}else{
					dialog.dismiss();
				}

			}
		});
		dialog.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		dialog.show();				
	}
	
	private  boolean createPlayer() {
		Users.setAllToInactive();
		if(Users.getUser(et.getText().toString()) == null){
			Users newUser=new Users(et.getText().toString(), new Date().getTime());
			newUser.setActive(1);
			newUser.save();
			return true;
		}else{
			Toast.makeText(getBaseContext(), "Taj korisnik veæ postoji!", Toast.LENGTH_SHORT).show();
			return false;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Users.setAllToInactive();
		igrac=listaIgraca.get(position);
		igrac.setActive(1);
		igrac.setLastPlayed(new Date().getTime());
		igrac.save();
		intent= new Intent(getBaseContext(), OdabirRazine.class);
		intent.putExtra("ime", igrac.getName());
		startActivity(intent);		
		
	}
}