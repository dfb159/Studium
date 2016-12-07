package sigrist.jonathan.informatik1.nr16;

/**
 * Stellt eine mathematische Aufgabe dar.
 */
public class Aufgabe implements Berechenbar {
	
	/** Oberstes Element der Aufgabe */
	protected Berechenbar element;
	
	/**
	 * Konstruktor
	 * 
	 * @param element
	 *            oberstes berechenbares Element.
	 */
	public Aufgabe(Berechenbar element) {
		this.element = element;
	}
	
	public int berechnen() {
		return element.berechnen();
	}
	
	/**
	 * Beispiel der Ausführung. Dabei wird das Ergebnis des mathematischen Ausdrucks "(-5 + 2 ) + 3
	 * + 5 + 8" auf der Konsole ausgegeben.
	 * 
	 * @param args
	 *            Werden nicht verwendet.
	 */
	public static void main(String[] args) {
		Berechenbar zahl1 = new Zahl(-5);
		Berechenbar zahl2 = new Zahl(2);
		Berechenbar addition = new Addition(zahl1, zahl2);
		Berechenbar summe = new Summe(new Zahl(3), new Zahl(5), new Zahl(8), addition);
		Aufgabe aufgabe = new Aufgabe(summe);
		System.out.println(aufgabe.berechnen());
	}
}
