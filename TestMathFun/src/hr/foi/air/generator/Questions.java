package hr.foi.air.generator;
	/**
	 * Klasa za upravljanje i generiranje objekta pitanje
	 * @author FunFactory
	 *
	 */
public class Questions {
	private int[] numbers;
	private char[] simbols;
	/**
	 * Konstruktor
	 * @param numbers - polje brojeva koje koristimo u pitanju
	 * @param simbols - polje simbola koje korisitmo u pitanju
	 */
	public Questions(int[] numbers, char[] simbols) {
		this.numbers = numbers;
		this.simbols = simbols;
	}
	public Questions() {
		// TODO Auto-generated constructor stub
	}
	public int[] getNumbers() {
		return numbers;
	}
	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}
	public char[] getSimbols() {
		return simbols;
	}
	public void setSimbols(char[] simbols) {
		this.simbols = simbols;
	}
	
	
}
