package hr.foi.air.generator;

import java.util.ArrayList;
import java.util.Random;
/**
 * Predstavlja skupinu statièkih metoda koje nam služe za generiranje pitanja
 * po razinama
 * @author FunFactory
 *
 */

public class Question_generator {

	private final static int size = 5;
	private static int a, b, t, s;
	private static int numbers[];
	private static char simbols[];
	private static Random rn = new Random();
	private static Questions question;

	public Question_generator() {
	}

	/** 
	 * metoda koja vraæa odreðen broj pitanja(brojPitanja) te razinu pitanja(razina)
	 * @param numberOfQuestions - odredjuje broj pitanja
	 * @param level - odredjue razinu pitanja
	 * @return listaPitanja - vraca listu pitanja 
	 */
	public static ArrayList<Questions> generate(int numberOfQuestions, int level) {
		ArrayList<Questions> questionList = new ArrayList<Questions>();
		switch (level) {
		case 1:
			for (int i = 0; i < numberOfQuestions; i++)
				questionList.add(levelOne());
			break;
		case 2:
			for (int i = 0; i < numberOfQuestions; i++)
				questionList.add(levelTwo());
			break;
		case 3:
			for (int i = 0; i < numberOfQuestions; i++)
				questionList.add(leverThree());	
			break;
		case 4:
			for (int i = 0; i < numberOfQuestions; i++)
				questionList.add(levelFour());
			break;
		case 5:
			for (int i = 0; i < numberOfQuestions; i++)
				questionList.add(levelFive());
			break;
		case 6:
			for (int i = 0; i < numberOfQuestions; i++)
				questionList.add(levelSix());
			break;
		case 7:
			for (int i = 0; i < numberOfQuestions; i++)
				questionList.add(levelSeven());
			break;
			
		default:
			break;
		}	
		return questionList;
	}

	/**
	 * Metoda koja stvara pitanja razine jedan
	 * @return
	 */
	private static Questions levelOne() {
		question=new Questions();
		numbers=new int[size];
		simbols= new char[size];
		
		a = rn.nextInt(10) + 1;
		b = rn.nextInt(10) + 1;
		numbers[0] = a;
		numbers[1] = b;
		numbers[2] = a + b;
		simbols[0] = '+';
		simbols[1] = '=';
		question.setNumbers(numbers);
		question.setSimbols(simbols);
		return question;
	}

	/**
	 * Metoda koja stvara pitanja razine dva
	 * @return
	 */
	private static Questions levelTwo() {
		question=new Questions();
		numbers=new int[size];
		simbols= new char[size];
		
		a = rn.nextInt(10) + 1;
		b = rn.nextInt(10) + 1;
		if (b > a) {
			t = b;
			b = a;
			a = t;
		}
		numbers[0] = a;
		numbers[1] = b;
		numbers[2] = a - b;
		simbols[0] = '-';
		simbols[1] = '=';
		question.setNumbers(numbers);
		question.setSimbols(simbols);
		return question;
	}

	/**
	 * Metoda koja stvara pitanja razine tri
	 * @return
	 */
	private static Questions leverThree() {
		question=new Questions();
		numbers=new int[size];
		simbols= new char[size];
		
		a = rn.nextInt(10) + 1;
		b = rn.nextInt(10) + 1;
		s = rn.nextInt(2);
		if (s==1) {
			if (b > a) {
				t = b;
				b = a;
				a = t;
			}
			simbols[0] = '-';
			numbers[2] = a-b;
		} else {
			simbols[0] = '+';
			numbers[2] = a + b;
		}
		numbers[0] = a;
		numbers[1] = b;
		simbols[1] = '=';
		question.setNumbers(numbers);
		question.setSimbols(simbols);
				
		return question;
	}

	/**
	 * Metoda koja stvara pitanja razine èetiri
	 * @return
	 */
	private static Questions levelFour() {
		question=new Questions();
		numbers=new int[size];
		simbols= new char[size];
		
		a = rn.nextInt(30) + 1;
		b = rn.nextInt(30) + 1;
		s = rn.nextInt(2);
		if (s == 1) {
			if (b > a) {
				t = b;
				b = a;
				a = t;
			}
			simbols[0] = '-';
			numbers[2] = a - b;
			
		} else {
			simbols[0] = '+';
			a = rn.nextInt(15) + 1;
			b = rn.nextInt(15) + 1;
			numbers[2] = a + b;
			
		}
		numbers[0] = a;
		numbers[1] = b;
		simbols[1] = '=';
		question.setNumbers(numbers);
		question.setSimbols(simbols);
		return question;
	}

	/**
	 * Metoda koja stvara pitanja razine pet
	 * @return
	 */
	private static Questions levelFive() {
		question=new Questions();
		numbers=new int[size];
		simbols= new char[size];
		
		a = rn.nextInt(50) + 1;
		b = rn.nextInt(50) + 1;
		s = rn.nextInt(2);
		if (s == 1) {
			if (b > a) {
				t = b;
				b = a;
				a = t;
			}
			simbols[0] = '-';
			numbers[2] = a - b;
		} else {
			simbols[0] = '+';
			a = rn.nextInt(25) + 1;
			b = rn.nextInt(25) + 1;
			numbers[2] = a + b;
			
		}
		numbers[0] = a;
		numbers[1] = b;
		simbols[1] = '=';
		question.setNumbers(numbers);
		question.setSimbols(simbols);
		return question;
	}

	/**
	 * Metoda koja stvara pitanja razine šest
	 * @return
	 */
	private static Questions levelSix() {
		question=new Questions();
		numbers=new int[size];
		simbols= new char[size];
		
		a = rn.nextInt(50) + 1;
		b = rn.nextInt(50) + 1;
		s = rn.nextInt(4);
		if (s == 1) {
			if (b > a) {
				t = b;
				b = a;
				a = t;
			}
			simbols[0] = '-';
			numbers[2] = a - b;
			numbers[0] = a;
			numbers[1] = b;
		}
		if (s == 0) {
			simbols[0] = '+';
			a = rn.nextInt(25) + 1;
			b = rn.nextInt(25) + 1;
			numbers[2] = a + b;
			numbers[0] = a;
			numbers[1] = b;
		}
		if (s == 2) {
			a = rn.nextInt(7) + 1;
			b = rn.nextInt(7) + 1;
			simbols[0] = '*';
			numbers[2] = a * b;
			numbers[0] = a;
			numbers[1] = b;
		}
		if (s == 3) {
			a = rn.nextInt(7) + 1;
			b = rn.nextInt(7) + 1;
			simbols[0] = '/';
			numbers[2] = b;
			numbers[0] = a * b;
			numbers[1] = a;
		}

		simbols[1] = '=';
		question.setNumbers(numbers);
		question.setSimbols(simbols);
		return question;
	}

	/**
	 * Metoda koja stvara pitanja razine sedam
	 * @return
	 */
	private static Questions levelSeven() {
		question=new Questions();
		numbers=new int[size];
		simbols= new char[size];
		
		a = rn.nextInt(100) + 1;
		b = rn.nextInt(100) + 1;
		s = rn.nextInt(4);
		if (s == 1) {
			if (b > a) {
				t = b;
				b = a;
				a = t;
			}
			simbols[0] = '-';
			numbers[2] = a - b;
			numbers[0] = a;
			numbers[1] = b;
		}
		if (s == 0) {
			a = rn.nextInt(50) + 1;
			b = rn.nextInt(50) + 1;
			simbols[0] = '+';
			numbers[2] = a + b;
			numbers[0] = a;
			numbers[1] = b;
		}
		if (s == 2) {
			a = rn.nextInt(10) + 1;
			b = rn.nextInt(10) + 1;
			simbols[0] = '*';
			numbers[2] = a * b;
			numbers[0] = a;
			numbers[1] = b;
		}
		if (s == 3) {
			a = rn.nextInt(10) + 1;
			b = rn.nextInt(10) + 1;
			simbols[0] = '/';
			numbers[2] = b;
			numbers[0] = a * b;
			numbers[1] = a;
		}

		simbols[1] = '=';
		question.setNumbers(numbers);
		question.setSimbols(simbols);
		return question;
	}

}
