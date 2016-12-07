package sigrist.jonathan.informatik1.nr17;

/**
 * Das Interface stellt Methoden fuer Getter und Setter von der Geschwindigkeit und der Anzahl an
 * Sitzen bereit.
 * 
 * @author Carolin Wortmann, Leonhard Segger, Jonathan Sigrist
 *
 */
public interface Fahrzeug {
	/**
	 * Getter fuer Geschwindigkeit
	 * 
	 * @return Die momentane Geschwindigkeit des Fahrzeugs
	 */
	public int getGeschwindigkeit();
	
	/**
	 * Setter fuer Geschwindigkeit
	 * 
	 * @param gesch
	 *            Die neue Geschwindigkeit
	 */
	public void setGeschwindigkeit(int gesch);
	
	/**
	 * Getter fuer die Anzahl an Sitzen
	 * 
	 * @return Die momentane Anzahl an Sitzen
	 */
	public int getAnzahlSitze();
	
	/**
	 * Setter fuer die Anzahl an Sitzen
	 * 
	 * @param anzahl
	 *            Die neue Anzahl an Sitzen
	 */
	public void setAnzahlSitze(int anzahl);
}
