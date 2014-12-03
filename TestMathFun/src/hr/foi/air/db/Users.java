package hr.foi.air.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Users")
public class Users extends Model{
	@Column(name = "id")
	public long id;
	@Column(name = "Name")
	public String name;
	
	public Users(){
		super();
	}
	//konstruktor
	public Users(long id, String name){
		super();
		this.id=id;
		this.name=name;
	}
}
