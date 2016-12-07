package sigrist.jonathan.informatik1.nr16;

/**
 * Representiert eine einfache Zahl.
 */
public class Zahl implements Berechenbar {
	
	/** Wert der Zahl */
	private int wert;
	
	/**
	 * Konstruktor
	 * 
	 * @param wert
	 *            Wert der Zahl.
	 */
	public Zahl(int wert) {
		this.wert = wert;
	}
	
	/**
	 * Gibt den Wert der Zahl zurück.
	 * 
	 * @return wert.
	 */
	@Override
	public int berechnen() {
		return wert;
	}
}
