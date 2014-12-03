package hr.foi.air.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Difficulties")
public class Difficulties extends Model{
	@Column(name = "id")
	public long id;
	@Column(name = "Name")
	public String name;
	
	public Difficulties(){
		super();
	}
	//konstruktor
	public Difficulties(long id, String name){
		super();
		this.id=id;
		this.name=name;
	}
}
