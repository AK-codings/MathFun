package hr.foi.air.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Highscore")
public class Highscore extends Model{
	@Column(name = "id")
	public long id;
	@Column(name = "highscore")
	public int highscore;
	@Column(name = "id")
	public Users user_id;
	@Column(name = "id")
	public Modules module_id;
	@Column(name = "id")
	public Difficulties difficulty_id;
	
	public Highscore(){
		super();
	}	
	//konstruktor
	public Highscore(long id, int highscore,Users user_id, Modules module_id, Difficulties difficulty_id){
		super();
		this.id=id;
		this.highscore=highscore;
		this.user_id=user_id;
		this.module_id=module_id; 
		this.difficulty_id=difficulty_id;
	}
}
