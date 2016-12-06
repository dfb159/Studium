package sigrist.jonathan.informatik1.nr17;

/**
 * Die Klasse erweitert die Klasse Auto um eine maximale Traglast.
 * 
 * @author Carolin Wortmann, Leonhard Segger, Jonathan Sigrist
 *
 */
public class LKW extends Auto {

	int maximaleLast;

	/**
	 * Der Konstruktor setzt alle Klassenattribute
	 * 
	 * @param geschwindigkeit
	 *            Die Geschwindigkeit des LKWs
	 * @param anzahlSitze
	 *            Die Anzahl an Sitzen
	 * @param anzahlRaeder
	 *            Die Anzahl an Raedern
	 * @param maximaleLast
	 *            Die maximale Traglast des LKWs
	 */
	public LKW(int geschwindigkeit, int anzahlSitze, int anzahlRaeder,
			int maximaleLast) {
		super(geschwindigkeit, anzahlSitze, anzahlRaeder);
		this.maximaleLast = maximaleLast;
	}

	/**
	 * Getter fuer die maximale Traglast
	 * 
	 * @return Die momentane maximale Traglast
	 */
	public int getMaximaleLast() {
		return maximaleLast;
	}

	/**
	 * Setter fuer die maximale Traglast
	 * 
	 * @param maximaleLast
	 *            Die neue maximale Traglast
	 */
	public void setMaximaleLast(int maximaleLast) {
		this.maximaleLast = maximaleLast;
	}
}
