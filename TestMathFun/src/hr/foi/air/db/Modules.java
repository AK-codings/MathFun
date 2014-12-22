package hr.foi.air.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Modules")
public class Modules extends Model{
	@Column(name = "Name")
	private String name;
	
	public Modules(){
		super();
	}
	//konstruktor
	public Modules(String name){
		super();
		this.name=name;
	}
}
