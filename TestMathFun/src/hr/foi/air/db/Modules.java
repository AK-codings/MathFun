package hr.foi.air.db;

import java.util.ArrayList;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
/**
 * Klasa za kreiranje i upravljanje tablicom "Modules"
 * @author FunFactory
 *
 */
@Table(name = "Modules")
public class Modules extends Model{
	@Column(name = "Name")
	private String name;
	@Column(name="active")
	private int active;
	@Column(name="opis")
	private String opis;
	
	public Modules(){
		super();
	}
	/**
	 * Konstruktor
	 * @param name - ime modula
	 * @param opis - opis modula
	 */
	public Modules(String name, String opis){
		super();
		this.name=name;
		this.opis=opis;
	}
		
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public static void setAllToInactive(){
		new Update(Modules.class).set("active=?",0).execute();
	}
	public static List<Modules> getAllModules(){
		return new Select().from(Modules.class).execute();
	}
	/**
	 * Dodavanje liste modula u bazu
	 * @return listaModula
	 */
	public static List<Modules> createModulesList(){
		List<Modules> listaModula=new ArrayList<Modules>();
		listaModula.add(new Modules("Modul_1", "Toèno/Netoèno zadatci"));
		listaModula.add(new Modules("Modul_2", "Upiši rezultat zadatci"));
		listaModula.add(new Modules("Modul_3", "Drag and Drop"));
		return listaModula;
	}
	public static void deleteModules() {
		new Delete().from(Modules.class).execute();
	}
	
	public static void setToActive(String name){
		new Update(Modules.class).set("active=?",1).where("name=?",name).execute();
	}
	
	public static Modules getActiveModules(){
		return new Select().from(Modules.class).where("active=?",1).executeSingle();
	}
}
