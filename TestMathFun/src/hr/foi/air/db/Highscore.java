package hr.foi.air.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Highscore")
public class Highscore extends Model{
	@Column(name = "highscore")
	private int highscore;
	@Column(name = "id")
	private Users user_id;
	@Column(name = "id")
	private Modules module_id;
	@Column(name = "id")
	private Difficulties difficulty_id;
	
	public Highscore(){
		super();
	}	
	//konstruktor
	public Highscore(int highscore,Users user_id, Modules module_id, Difficulties difficulty_id){
		super();
		this.highscore=highscore;
		this.user_id=user_id;
		this.module_id=module_id; 
		this.difficulty_id=difficulty_id;
	}
}
