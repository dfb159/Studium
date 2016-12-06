package sigrist.jonathan.informatik1.nr17;

/**
 * Die Klasse PKW macht eine Instanzierung von Auto moeglich, erweitert diese
 * jedoch nicht.
 * 
 * @author Carolin Wortmann, Leonhard Segger, Jonathan Sigrist
 * 
 */
public class PKW extends Auto {

	/**
	 * Der Konstruktor ruft den Konstruktor der Klasse Auto auf, um dort alle
	 * Klassenattribute zu setzen.
	 * 
	 * @param geschwindigkeit
	 *            Die Geschwindigkeit des PKWs
	 * @param anzahlSitze
	 *            Die Anzahl an Sitzen
	 * @param anzahlRaeder
	 *            Die Anzahl an Raedern
	 */
	public PKW(int geschwindigkeit, int anzahlSitze, int anzahlRaeder) {
		super(geschwindigkeit, anzahlSitze, anzahlRaeder);
	}
}
