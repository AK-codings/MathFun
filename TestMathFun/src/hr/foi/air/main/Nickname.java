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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;

public class Nickname extends BaseActivity implements OnClickListener, OnItemClickListener {
	private EditText et;
	private ButtonRectangle bt;
	private ListView lvUsers;
	private AlertDialog.Builder dialog;
	private List<Users> userList;
	private ArrayList<String> alUserName;
	private ArrayAdapter<String> aaUserName;
	private Intent intent;
	private Users user;
	private boolean flag;
	
	/**
	 * Metoda koja povezuje layout
	 */
	@Override
	public int getLayout() {
		return R.layout.nickname;
	}

	/**
	 * Metoda koja se izvršava nakon povezivanja layouta
	 */
	@Override
	public void initView() {

		bt = (ButtonRectangle) findViewById(R.id.btKreni);
		et = (EditText) findViewById(R.id.etUnesiIgraca);
		lvUsers=(ListView) findViewById(R.id.lvListIgraca);
		lvUsers.setOnItemClickListener(this);
		bt.setOnClickListener(this);
		populateList();
		
}

	/**
	 * Metoda koja ispisuje zadnje aktivne igraèe
	 */
	private void populateList() {
		
		userList=Users.getLastPlayers();
		alUserName=new ArrayList<String>();
		for (int i = 0; i < userList.size(); i++) {
			alUserName.add(userList.get(i).getName());
		}
		aaUserName=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alUserName);
		lvUsers.setAdapter(aaUserName);
		
	}

	/**
	 * Osluškuje klik na button
	 */
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

	/**
	 * Metoda koja stvara dialog na kojem se potvrðuju stvaranje novog igraèa
	 */
	private void makeDialog() {
		dialog=new AlertDialog.Builder(this);
		dialog.setTitle("Novi igraè");
		dialog.setMessage("Da li želite stvoriti novog igraèa?");
		dialog.setPositiveButton("Da", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				flag=createPlayer();
				if(flag==true){
					intent= new Intent(getBaseContext(), LevelSelect.class);
					intent.putExtra("ime", strToUpper(et.getText().toString()));
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
	
	/**
	 * Metoda za stvaranje novog igraèa, odnosno ukoliko taj igraè veè postoji vraèa false
	 * @return boolean
	 */
	private  boolean createPlayer() {
		Users.setAllToInactive();
		if(Users.getUser(strToUpper(et.getText().toString())) == null){
			Users newUser=new Users(strToUpper(et.getText().toString()), new Date().getTime());
			newUser.setActive(1);
			newUser.save();
			return true;
		}else{
			Toast.makeText(getBaseContext(), "Taj korisnik veæ postoji!", Toast.LENGTH_SHORT).show();
			return false;
		}
	}

	/**
	 * Osluškuje klik unutar listViewa
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Users.setAllToInactive();
		user=userList.get(position);
		user.setActive(1);
		user.setLastPlayed(new Date().getTime());
		user.save();
		intent= new Intent(getBaseContext(), LevelSelect.class);
		intent.putExtra("ime", user.getName());
		startActivity(intent);		
		
	}
	
	/**
	 * Metoda koja stvara igraèa u bazi s prvim slovom imena i prezimena veliko (npr Ivo Iviæ Ivanec)
	 * @param source
	 * @return String
	 */
   private String strToUpper(String source){
	    source=source.toLowerCase();
        boolean cap = true;
        char[]  out = source.toCharArray();
        int i, len = source.length();
        for(i=0; i<len; i++){
            if(Character.isWhitespace(out[i])){
                cap = true;
                continue;
            }
            if(cap){
                out[i] = Character.toUpperCase(out[i]);
                cap = false;
            }
        }
        return new String(out);
    }
	
	
}