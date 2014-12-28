package hr.foi.air.main;

import hr.foi.air.db.Difficulties;
import hr.foi.air.db.Modules;
import hr.foi.air.db.Users;

import java.util.Date;
import java.util.List;

import com.gc.materialdesign.views.ButtonRectangle;

import air.testmathfun.R;
import android.app.Dialog;
import android.content.Intent;
import android.media.ExifInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements OnClickListener {
	private ButtonRectangle btIgraj;
	private ButtonRectangle btPravila;
	private List<Difficulties> listaRazina;
	private List<Modules> listaModula;
	
	@Override
	public int getLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void initView() {
		btIgraj = (ButtonRectangle) findViewById(R.id.btIgraj);
		btPravila = (ButtonRectangle) findViewById(R.id.btPravila);
		btIgraj.setOnClickListener(this);			
		btPravila.setOnClickListener(this);
		
		prepareDatabase();
	}
	
	//Postavi database ako je prazan
	private void prepareDatabase() {
		if (Users.getNumberOfPlayers() == 0) {
			Users user1=new Users("Antonio Markoc", 19);
			user1.save();
			Users user2=new Users("Matija Nedjeral", 12);
			user2.save();
			Users user3=new Users("Borna Farkas", 17);
			user3.save();		
			Users user4=new Users("Mislav Santek", 15);
			user4.save();		
		}
		
		if(Difficulties.getAllDifficulties().size() != Difficulties.createDifficultiesList().size()){
			Difficulties.deleteDifficulties();
			listaRazina=Difficulties.createDifficultiesList();
			for (int i = 0; i < listaRazina.size(); i++) {
				listaRazina.get(i).save();
			}
		}
		
		if(Modules.getAllModules().size() != Modules.createModulesList().size()){
			Modules.deleteModules();
			listaModula=Modules.createModulesList();
			for (int i = 0; i < listaModula.size(); i++) {
				listaModula.get(i).save();
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btIgraj:
			startActivity(new Intent(getBaseContext(), Nickname.class));
			break;
		case R.id.btPravila:
			startActivity(new Intent(getBaseContext(), Pravila.class));
			break;
		default:
			break;
		}
		
	}

	@Override
	public void onBackPressed() {
		finish();
		return;
	}
}
