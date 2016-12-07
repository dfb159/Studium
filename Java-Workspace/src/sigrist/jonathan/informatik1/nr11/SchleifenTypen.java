package sigrist.jonathan.informatik1.nr11;

/**
 * @author Leonhard Segger, Carolin Wortmann, Jonathan Sigrist
 */
public class SchleifenTypen {
	
	/**
	 * Berechnet Aufgabe 11 a mit Hilfe einer Do-While-Schleife
	 * 
	 * @param x
	 *            Basis der Berechnung
	 * @return Ergebnis der Berechnung
	 */
	public static Integer a_doWhile(int x) {
		int i = 0;
		Integer result;
		do {
			result = ++i * i;
		} while (result < x);
		return result;
	}
	
	/**
	 * Berechnet Aufgabe 11 a mit Hilfe einer While-Schleife
	 * 
	 * @param x
	 *            Basis der Berechnung
	 * @return Ergebnis der Berechnung
	 */
	public static Integer a_while(int x) {
		int i = 1;
		Integer result = 1;
		while (result < x) {
			result = ++i * i;
		}
		return result;
	}
	
	/**
	 * Berechnet Aufgabe 11 a mit Hilfe einer For-Schleife
	 * 
	 * @param x
	 *            Basis der Berechnung
	 * @return Ergebnis der Berechnung
	 */
	public static Integer a_for(int x) {
		Integer result = 1;
		for (int i = 0; result < x; i++) {
			result = i * i;
		}
		return result;
	}
	
	/**
	 * Berechnet Aufgabe 11 b mit Hilfe einer While-Schleife
	 * 
	 * @param x
	 *            Basis der Berechnung
	 * @return Ergebnis der Berechnung
	 */
	public static String b_while(int x) {
		
		String result = "";
		while (result.length() < x) {
			result += 0.0 / 0.0;
		}
		result += "Batman";
		return result;
	}
	
	/**
	 * Berechnet Aufgabe 11 b mit Hilfe einer For-Schleife
	 * 
	 * @param x
	 *            Basis der Berechnung
	 * @return Ergebnis der Berechnung
	 */
	public static String b_for(int x) {
		String result = "";
		for (; result.length() < x; result += 0.0 / 0.0)
			;
		result += "Batman";
		return result;
	}
	
	/**
	 * Berechnet Aufgabe 11 b mit Hilfe einer Do-While-Schleife
	 * 
	 * @param x
	 *            Basis der Berechnung
	 * @return Ergebnis der Berechnung
	 */
	public static String b_doWhile(int x) {
		String result = "";
		if (x <= 0)
			return "Batman";
		do {
			result += 0.0 / 0.0;
		} while (result.length() < x);
		result += "Batman";
		return result;
		
	}
	
	public static void main(String[] args) {
		System.out.println(b_doWhile(0));
		System.out.println(b_while(0));
		System.out.println(b_for(0));
	}
}
