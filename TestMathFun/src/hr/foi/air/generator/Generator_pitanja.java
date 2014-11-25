package hr.foi.air.generator;

import java.util.ArrayList;
import java.util.Random;

public class Generator_pitanja {

	private final static int velicina_polja = 5;
	static int a, b, t, s;
	static int brojevi[] = new int[velicina_polja];
	static char simboli[] = new char[velicina_polja];
	static Random rn = new Random();
	static Pitanje pitanje = new Pitanje();

	private Generator_pitanja() {
	}

	// metoda koja vraæa odreðen broj pitanja te razinu pitanja
	public static ArrayList<Pitanje> generiraj(int brojPitanja, int razina) {
		ArrayList<Pitanje> listaPitanja = new ArrayList<Pitanje>();
		if (razina == 1)
			for (int i = 0; i < brojPitanja; i++)
				listaPitanja.add(razina1());
		if (razina == 2)
			for (int i = 0; i < brojPitanja; i++)
				listaPitanja.add(razina2());
		if (razina == 3)
			for (int i = 0; i < brojPitanja; i++)
				listaPitanja.add(razina3());
		if (razina == 4)
			for (int i = 0; i < brojPitanja; i++)
				listaPitanja.add(razina4());
		if (razina == 5)
			for (int i = 0; i < brojPitanja; i++)
				listaPitanja.add(razina5());
		if (razina == 6)
			for (int i = 0; i < brojPitanja; i++)
				listaPitanja.add(razina6());
		if (razina == 7)
			for (int i = 0; i < brojPitanja; i++)
				listaPitanja.add(razina7());
		return listaPitanja;
	}

	// metoda koja stvara pitanja razine jedan
	private static Pitanje razina1() {
		a = rn.nextInt(10) + 1;
		b = rn.nextInt(10) + 1;
		brojevi[0] = a;
		brojevi[1] = b;
		brojevi[2] = a + b;
		simboli[0] = '+';
		simboli[1] = '=';
		pitanje.setBrojevi(brojevi);
		pitanje.setSimboli(simboli);
		return pitanje;
	}

	// metoda koja stvara pitanja razine dva
	private static Pitanje razina2() {
		a = rn.nextInt(10) + 1;
		b = rn.nextInt(10) + 1;
		if (b > a) {
			t = b;
			b = a;
			a = t;
		}
		brojevi[0] = a;
		brojevi[1] = b;
		brojevi[2] = a - b;
		simboli[0] = '-';
		simboli[1] = '=';
		pitanje.setBrojevi(brojevi);
		pitanje.setSimboli(simboli);
		return pitanje;
	}

	// metoda koja stvara pitanja razine tri
	private static Pitanje razina3() {
		a = rn.nextInt(10) + 1;
		b = rn.nextInt(10) + 1;
		s = rn.nextInt(2);
		if (s == 1) {
			simboli[0] = '-';
			brojevi[2] = a - b;
			if (b > a) {
				t = b;
				b = a;
				a = t;
			}
		} else {
			simboli[0] = '+';
			brojevi[2] = a + b;
		}
		brojevi[0] = a;
		brojevi[1] = b;
		simboli[1] = '=';
		pitanje.setBrojevi(brojevi);
		pitanje.setSimboli(simboli);
		return pitanje;
	}

	// metoda koja stvara pitanja razine èetiri
	private static Pitanje razina4() {
		a = rn.nextInt(30) + 1;
		b = rn.nextInt(30) + 1;
		s = rn.nextInt(2);
		if (s == 1) {
			simboli[0] = '-';
			brojevi[2] = a - b;
			if (b > a) {
				t = b;
				b = a;
				a = t;
			}
		} else {
			simboli[0] = '+';
			brojevi[2] = a + b;
		}
		brojevi[0] = a;
		brojevi[1] = b;
		simboli[1] = '=';
		pitanje.setBrojevi(brojevi);
		pitanje.setSimboli(simboli);
		return pitanje;
	}

	// metoda koja stvara pitanja razine pet
	private static Pitanje razina5() {
		a = rn.nextInt(50) + 1;
		b = rn.nextInt(50) + 1;
		s = rn.nextInt(2);
		if (s == 1) {
			simboli[0] = '-';
			brojevi[2] = a - b;
			if (b > a) {
				t = b;
				b = a;
				a = t;
			}
		} else {
			simboli[0] = '+';
			brojevi[2] = a + b;
		}
		brojevi[0] = a;
		brojevi[1] = b;
		simboli[1] = '=';
		pitanje.setBrojevi(brojevi);
		pitanje.setSimboli(simboli);
		return pitanje;
	}

	// metoda koja stvara pitanja razine šest
	private static Pitanje razina6() {
		a = rn.nextInt(50) + 1;
		b = rn.nextInt(50) + 1;
		s = rn.nextInt(4);
		if (s == 1) {
			simboli[0] = '-';
			brojevi[2] = a - b;
			if (b > a) {
				t = b;
				b = a;
				a = t;
			}
			brojevi[0] = a;
			brojevi[1] = b;
		}
		if (s == 0) {
			simboli[0] = '+';
			brojevi[2] = a + b;
			brojevi[0] = a;
			brojevi[1] = b;
		}
		if (s == 2) {
			a = rn.nextInt(7) + 1;
			b = rn.nextInt(7) + 1;
			simboli[0] = '*';
			brojevi[2] = a * b;
			brojevi[0] = a;
			brojevi[1] = b;
		}
		if (s == 3) {
			a = rn.nextInt(7) + 1;
			b = rn.nextInt(7) + 1;
			simboli[0] = '/';
			brojevi[2] = b;
			brojevi[0] = a * b;
			brojevi[1] = a;
		}

		simboli[1] = '=';
		pitanje.setBrojevi(brojevi);
		pitanje.setSimboli(simboli);
		return pitanje;
	}

	// metoda koja stvara pitanja razine sedam
	private static Pitanje razina7() {
		a = rn.nextInt(100) + 1;
		b = rn.nextInt(100) + 1;
		s = rn.nextInt(4);
		if (s == 1) {
			if (b > a) {
				t = b;
				b = a;
				a = t;
			}
			simboli[0] = '-';
			brojevi[2] = a - b;
			brojevi[0] = a;
			brojevi[1] = b;
		}
		if (s == 0) {
			simboli[0] = '+';
			brojevi[2] = a + b;
			brojevi[0] = a;
			brojevi[1] = b;
		}
		if (s == 2) {
			a = rn.nextInt(10) + 1;
			b = rn.nextInt(10) + 1;
			simboli[0] = '*';
			brojevi[2] = a * b;
			brojevi[0] = a;
			brojevi[1] = b;
		}
		if (s == 3) {
			a = rn.nextInt(10) + 1;
			b = rn.nextInt(10) + 1;
			simboli[0] = '/';
			brojevi[2] = b;
			brojevi[0] = a * b;
			brojevi[1] = a;
		}

		simboli[1] = '=';
		pitanje.setBrojevi(brojevi);
		pitanje.setSimboli(simboli);
		return pitanje;
	}

}
