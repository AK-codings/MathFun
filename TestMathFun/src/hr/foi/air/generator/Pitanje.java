package hr.foi.air.generator;

public class Pitanje {
	private int[] brojevi;
	private char[] simboli;
	public Pitanje(int[] brojevi, char[] simboli) {
		this.brojevi = brojevi;
		this.simboli = simboli;
	}
	public Pitanje() {
		// TODO Auto-generated constructor stub
	}
	public int[] getBrojevi() {
		return brojevi;
	}
	public void setBrojevi(int[] brojevi) {
		this.brojevi = brojevi;
	}
	public char[] getSimboli() {
		return simboli;
	}
	public void setSimboli(char[] simboli) {
		this.simboli = simboli;
	}
	
	
}
