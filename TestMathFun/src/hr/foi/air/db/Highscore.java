package hr.foi.air.db;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
/**
 * Klasa za kreiranje i upravljanje tablicom "Highscore"
 * @author FunFactory
 *
 */
@Table(name = "Highscore")
public class Highscore extends Model{
	@Column(name = "highscore")
	private int highscore;
	@Column(name = "user_id")
	private Users user_id;
	@Column(name = "module_id")
	private Modules module_id;
	@Column(name = "difficulty_id")
	private Difficulties difficulty_id;
	
	public Highscore(){
		super();
	}	
	/**
	 * Konstruktor
	 * @param highscore - bodovno stanje
	 * @param user_id - oznaka korisnika
	 * @param module_id - oznaka modula
	 * @param difficulty_id - oznaka tezine
	 */
	public Highscore(int highscore,Users user_id, Modules module_id, Difficulties difficulty_id){
		super();
		this.highscore=highscore;
		this.user_id=user_id;
		this.module_id=module_id; 
		this.difficulty_id=difficulty_id;
	}
	
	
	
	public int getHighscore() {
		return highscore;
	}
	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}
	public Users getUser_id() {
		return user_id;
	}
	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}
	public Modules getModule_id() {
		return module_id;
	}
	public void setModule_id(Modules module_id) {
		this.module_id = module_id;
	}
	public Difficulties getDifficulty_id() {
		return difficulty_id;
	}
	public void setDifficulty_id(Difficulties difficulty_id) {
		this.difficulty_id = difficulty_id;
	}
	/**
	 * Metoda za ispit najboljih 5 rezultata 
	 * @param difficulties
	 * @param modul
	 * @return List<Highscore>
	 */
	public static List<Highscore> getBestFiveResults(Difficulties difficulties, Modules modul){
		return new Select().from(Highscore.class).orderBy("highscore DESC").where("difficulty_id=? AND module_id=?",difficulties.getId(), modul.getId()).limit(5).execute();
	}
	/**
	 * Metoda za ispit najboljih ukupnih 5 rezultata 
	 * @param difficulties
	 * @param modul
	 * @return List<Highscore>
	 */
	public static List<Highscore> getBestFiveResults(){
		return new Select().from(Highscore.class).orderBy("highscore DESC").limit(5).execute();
	}
	/**
	 * Metoda za ažuriranje najboljih rezultata
	 * @param modules
	 * @param difficulties
	 * @param users
	 * @param highscore
	 */
	public static void updateHightlist(Modules modules, Difficulties difficulties, Users users, int highscore){
		new Update(Highscore.class).set("user_id=?,module_id=?,difficulty_id=?, highscore=?", users.getId(), modules.getId(), difficulties.getId(), highscore).where("highscore=?", getLowestHighscore().getHighscore()).execute();
	}
	/**
	 * Metoda za dohvaæanje najlošijeg rezultata
	 * @return Highscore
	 */
	private static Highscore getLowestHighscore() {
		return new Select().from(Highscore.class).orderBy("highscore ASC").limit(1).executeSingle();
	}
}
