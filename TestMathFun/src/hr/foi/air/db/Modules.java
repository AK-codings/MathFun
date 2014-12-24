package hr.foi.air.db;

import java.util.ArrayList;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

@Table(name = "Modules")
public class Modules extends Model{
	@Column(name = "Name")
	private String name;
	@Column(name="active")
	private int active;
	
	public Modules(){
		super();
	}
	//konstruktor
	public Modules(String name){
		super();
		this.name=name;
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
	
	public static List<Modules> createModulesList(){
		List<Modules> listaModula=new ArrayList<Modules>();
		listaModula.add(new Modules("Modul_1"));
		listaModula.add(new Modules("Modul_2"));
		listaModula.add(new Modules("Modul_3"));
		return listaModula;
	}
	public static void deleteModules() {
		new Delete().from(Modules.class).execute();
	}
	
	public static void setToActive(String name){
		new Update(Modules.class).set("active=?",1).where("name=?",name).execute();
	}
	
}
