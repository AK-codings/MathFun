package hr.foi.air.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Difficulties")
public class Difficulties extends Model{
	@Column(name = "Name")
	private String name;
	
	public Difficulties(){
		super();
	}
	//konstruktor
	public Difficulties(String name){
		super();
		this.name=name;
	}
}
