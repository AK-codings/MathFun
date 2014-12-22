package hr.foi.air.db;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "Users")
public class Users extends Model{
	@Column(name = "Name")
	private String name;
	@Column(name="last_played")
	private long last_played;
	
	public Users(){
		super();
	}
	//konstruktor
	public Users(String name, long last_played){
		super();
		this.name=name;
		this.last_played=last_played;
	}
	public static List<Users> getLastThreePlayers(){
		return new Select().from(Users.class).orderBy("last_played ASC").limit(3).execute();
	}
}
