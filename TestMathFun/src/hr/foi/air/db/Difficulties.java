package hr.foi.air.db;

import java.util.ArrayList;
import java.util.List;

import air.testmathfun.R;
import android.content.Loader.ForceLoadContentObserver;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

@Table(name = "Difficulties")
public class Difficulties extends Model{
	@Column(name = "Name")
	private String name;
	@Column(name = "opis")
	private String opis;
	@Column(name = "slika")
	private int slika;
	@Column(name="active")
	private int active;
	
	public Difficulties(){
		super();
	}
	//konstruktor
	public Difficulties(String name, String opis, int slika){
		super();
		this.name=name;
		this.opis=opis;
		this.slika=slika;
	}
	
	
	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public int getSlika() {
		return slika;
	}
	public void setSlika(int slika) {
		this.slika = slika;
	}
	public static List<Difficulties> getAllDifficulties(){
		return new Select().from(Difficulties.class).execute();
	}
	
	public static List<Difficulties> createDifficultiesList(){
		List<Difficulties> razine=new ArrayList<Difficulties>();
		razine.add(new Difficulties("Razina 1", "Brojevi do 10 sa matematièkom operacijom zbrajanje", R.drawable.jedan));
		razine.add(new Difficulties("Razina 2", "Brojevi do 10 sa matematièkom operacijom oduzimanja",  R.drawable.dva));
		razine.add(new Difficulties("Razina 3", "Brojevi do 10 sa matematièkim operacijama zbrajanja, oduzimanja",  R.drawable.tri));
		razine.add(new Difficulties("Razina 4", "Brojevi do 30 sa matematièkim operacijama zbrajanja i oduzimanja",  R.drawable.cetri));
		razine.add(new Difficulties("Razina 5", "Brojevi do 50 sa matematièkim operacijama zbrajanja i oduzimanja",  R.drawable.pet));
		razine.add(new Difficulties("Razina 6", "Brojevi do 50 sa matematièkim operacijama zbrajanja, oduzimanja, djeljenja i množenja",  R.drawable.sest));
		razine.add(new Difficulties("Razina 7", "Brojevi do 100 sa matematièkim operacijama zbrajanja, oduzimanja, djeljenja i množenja",  R.drawable.sedam));
		return razine;
	}
	
	public static void deleteDifficulties(){
		new Delete().from(Difficulties.class).execute();
	}
	
	public static void setAllToInactive(){
		new Update(Difficulties.class).set("active=?",0).execute();
	}
}
