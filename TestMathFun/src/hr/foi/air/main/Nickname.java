package hr.foi.air.main;
import java.util.ArrayList;

import com.gc.materialdesign.views.ButtonRectangle;

import air.testmathfun.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Nickname extends BaseActivity implements OnClickListener {
	private EditText et;
	private ButtonRectangle bt;
	private ListView lvIgraci;
	private AlertDialog.Builder dialog;
	
	@Override
	public int getLayout() {
		return R.layout.nickname;
	}

	@Override
	public void initView() {

		bt = (ButtonRectangle) findViewById(R.id.btKreni);
		et = (EditText) findViewById(R.id.etUnesiIgraca);
		lvIgraci=(ListView) findViewById(R.id.lvListIgraca);
		populateList();
		bt.setOnClickListener(this);
}

	private void populateList() {
		ArrayList<String> alImenaIgraca=new ArrayList<String>();
		alImenaIgraca.add("Prvi igrac");
		alImenaIgraca.add("Drugi igrac");
		alImenaIgraca.add("Treci igrac");
		ArrayAdapter<String> aaImenaIgraca=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alImenaIgraca);
		lvIgraci.setAdapter(aaImenaIgraca);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
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
				Intent intent = new Intent(getBaseContext(), Menu.class);
				intent.putExtra("ime", et.getText().toString());
				startActivity(intent);
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
}