package sigrist.jonathan.informatik1.nr12;

/**
 * Klasse beinhaltet die Methoden loeseRekursiv und loeseSchleife, welche in der
 * Aufgabe verlangt wurden.
 * 
 * @author jonathan
 *
 */
public class MathFormel {

	/**
	 * epsilon ist die Konstante, durch welche die Genauigkeit der Berechnung
	 * festgelegt ist. PI ist einfach eine Abkuerzung fuer die Zahl Pi, um sie
	 * nachher besser (und ohne spaeteren Aufruf von java.lang.Math) nutzen zu
	 * koennen.
	 */
	private final double	epsilon	= 1e-7;
	private final double	PI		= Math.PI;

	/**
	 * Da sin(x) periodisch ist, wird x erst auf einen Wert im Intervall [0,
	 * Pi/2] gebracht. Dies beschleunigt die Berechnung
	 * 
	 * @param x
	 *            Abzisse, fuer welche der sinuswert berechnet werden soll
	 * @return Angenaeherter Funktionswert fuer x
	 */
	public double loeseRekursiv(double x) {
		if (x > 2 * PI)
			return loeseRekursiv(x - 2 * PI);
		else if (x < 0)
			return loeseRekursiv(x + 2 * PI);
		else if (x > PI)
			return -loeseRekursiv(x - PI);
		else if (x > PI / 2)
			return loeseRekursiv(PI - x);
		return recursiveSin(x, 0);
	}

	/**
	 * 
	 * @param x
	 *            Abzisse, fuer welche der sinuswert berechnet werden soll
	 * @param iteration
	 *            Rekursive Variable fuer die Berechnung
	 * @param value
	 *            Uebergebener derzeitiger Funktionswert
	 * @return Wert der Sinusfunktion an der Abzisse
	 */
	private double recursiveSin(double x, int iteration) {
		double newValue = vZ(iteration) * pow(x, 2 * iteration + 1)
				/ fakultaet(2 * iteration + 1);
		if (newValue > -epsilon && newValue < epsilon)
			return newValue;
		else
			return newValue + recursiveSin(x, iteration + 1);
	}

	/**
	 * Die Methode entscheidet, welches Vorzeichen die Iteration haben soll. Sie
	 * entspricht (-1)^exp
	 * 
	 * @param exp
	 *            Exponent der Funktion (-1)^exp
	 * @return 1 falls exp gerade ist; -1 falls sxp ungerade ist
	 */
	private int vZ(int exp) {
		if (exp % 2 == 0)
			return 1;
		else
			return -1;
	}

	/**
	 * @param base
	 *            Basis der Funktion
	 * @param exp
	 *            Exponent der Funktion
	 * @return Wert von base^exp, wobei fuer exp <= 0, 1 zurueckgegeben wird
	 */
	private double pow(double base, int exp) {
		double retVar = 1;
		for (int i = 0; i < exp; i++) {
			retVar *= base;
		}
		return retVar;
	}

	/**
	 * @param n
	 *            Zahl, fuer welche die Fakultaet berechnet werden soll
	 * @return Fakultaet der Zahl n
	 */
	private double fakultaet(double n) {
		if (n > 170)
			return Double.NaN;
		if (n > 1)
			return n * fakultaet(n - 1);
		else
			return 1;
	}

	/**
	 * Methode berechner den Sinus an der Stelle x rekursiv
	 * 
	 * @param x
	 *            Die Abzisse
	 * @return Der Wert vom Sinus auf 10^-7 genau.
	 */
	public double solutionloaseRekursiv(double x) {
		return solutionRecursiveSin(x, x, 1, 1, 1, epsilon);
	}

	/**
	 * Die Methode berechnet einen Teil des Sinus rekursiv
	 * 
	 * @param x
	 *            Abzisse
	 * @param zaehler
	 *            momentaner Wert fuer x^(2i+1)
	 * @param nenner
	 *            momentaner Wert fuer (2i+1)!
	 * @param fakcounter
	 *            parallel laufender Counter fuer 2i+1
	 * @param vorzeichen
	 *            vorzeichen der derzeitigen Berechnung
	 * @param epsilon
	 *            Genauigkeit der Berechnung (Abbruchbedingung)
	 * @return Die Teilberechnung des Sinus
	 */
	private double solutionRecursiveSin(double x, double zaehler, double nenner,
			double fakcounter, double vorzeichen, double epsilon) {
		double inkrement = vorzeichen * zaehler / nenner;
		if (inkrement > epsilon || inkrement < epsilon) {
			return inkrement + solutionRecursiveSin(x, zaehler * x * x,
					nenner * ++fakcounter * ++fakcounter, fakcounter,
					-vorzeichen, epsilon);
		}
		return inkrement;
	}

	/**
	 * Da die Sinusfunktion periodisch und symmetrisch ist, kann x als Wert im
	 * Intervall [0;Pi/2] berechnet werden
	 * 
	 * @param x
	 *            Abzisse, fuer welche der sinuswert berechnet werden soll
	 * @return Annaeherung des sinus bei x
	 */
	public double loeseSchleife(double x) {
		while (x > 2 * PI)
			x -= 2 * PI;
		while (x < 0)
			x += 2 * PI;
		boolean flag = false;
		if (x > PI) {
			x -= PI;
			flag = true;
		}
		if (x > PI / 2)
			x = PI - x;

		double fakult = 1.0, potenz = x;
		double result = x;
		double summand = x;
		for (int i = 1; !(summand > -epsilon && summand < epsilon); i++) {
			fakult *= i * (4 * i + 2);
			potenz *= x * x;
			summand = (i % 2 == 0 ? 1 : -1) * potenz / fakult;
			result += summand;
		}
		return (flag ? -result : result);
	}
}
