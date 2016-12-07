package sigrist.jonathan.informatik1.nr4;

/**
 * Klasse stellt eine Felge dar und beinhaltet die Kosten dieser.
 * 
 * @author jonathan
 *
 */
public class Felge {
	
	private int kosten;
	
	/**
	 * Erstellt eine neue Felge.
	 * 
	 * @param kosten
	 *            Die Kosten der Felge.
	 */
	public Felge(int kosten) {
		this.kosten = kosten;
	}
	
	/**
	 * Gibt die Kosten der Felge zurueck.
	 * 
	 * @return Die Kosten der Felge.
	 */
	public int getKosten() {
		return kosten;
	}
	
}
