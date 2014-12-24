package hr.foi.air.db;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

@Table(name = "Users")
public class Users extends Model{
	@Column(name = "Name")
	private String name;
	@Column(name="last_played")
	private long last_played;
	@Column(name="active")
	private int active;
		
	public String getName() {
		return name;
	}
	public long getLast_played() {
		return last_played;
	}
	public Users(){
		super();
	}
	//konstruktor
	public Users(String name, long last_played){
		super();
		this.name=name;
		this.last_played=last_played;
		this.active=0;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public void setLastPlayed(long last_played){
		this.last_played=last_played;
	}
	
	
	
	
	public static List<Users> getLastPlayers(){
		//return new Select().from(Users.class).orderBy("last_played DESC").limit(4).execute();  -> za limit
		return new Select().from(Users.class).orderBy("last_played DESC").execute();
	}
	public static int getNumberOfPlayers(){
		return new Select().from(Users.class).count();
	}
	public static void setAllToInactive(){
		new Update(Users.class).set("active=?",0).execute();
	}
	public static Users getUser(String name){
		return new Select().from(Users.class).where("name=?",name).executeSingle();
	}	
}
