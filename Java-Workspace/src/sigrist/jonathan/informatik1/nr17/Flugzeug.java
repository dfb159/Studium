package sigrist.jonathan.informatik1.nr17;

/**
 * Klasse enthaelt die geforderten Klassenattribute und Methoden fuer ein Flugzeug. Dabei werden die
 * Methoden des implementierten Interfaces genauer definiert.
 * 
 * @author Carolin Wortmann, Leonhard Segger, Jonathan Sigrist
 *
 */
public class Flugzeug implements Fahrzeug {
	
	protected int	geschwindigkeit;
	protected int	anzahlSitze;
	protected int	maximaleFlueghoehe;
	
	/**
	 * Der Konstruktor setzt alle Klassenparameter
	 * 
	 * @param geschwindigkeit
	 *            Die Geschwindigkeit des Flugzeugs
	 * @param anzahlSitze
	 *            Die Anzahl an Sitzen
	 * @param maximaleFlueghoehe
	 *            Die maximale Flughoehe des Flugzeugs
	 */
	public Flugzeug(int geschwindigkeit, int anzahlSitze, int maximaleFlueghoehe) {
		super();
		this.geschwindigkeit = geschwindigkeit;
		this.anzahlSitze = anzahlSitze;
		this.maximaleFlueghoehe = maximaleFlueghoehe;
	}
	
	@Override
	public int getGeschwindigkeit() {
		return geschwindigkeit;
	}
	
	@Override
	public void setGeschwindigkeit(int gesch) {
		this.geschwindigkeit = gesch;
	}
	
	@Override
	public int getAnzahlSitze() {
		return anzahlSitze;
	}
	
	@Override
	public void setAnzahlSitze(int anzahl) {
		this.anzahlSitze = anzahl;
	}
	
	/**
	 * Getter fuer die maximale Flueghoehe des Flugzeugs
	 * 
	 * @return Die momentane maximale Flueghoehe
	 */
	public int getMaximaleFlueghoehe() {
		return maximaleFlueghoehe;
	}
	
	/**
	 * Setter fuer die maximale Flueghoehe
	 * 
	 * @param maximaleFlueghoehe
	 *            Die neue maximale Flueghoehe
	 */
	public void setMaximaleFlueghoehe(int maximaleFlueghoehe) {
		this.maximaleFlueghoehe = maximaleFlueghoehe;
	}
	
}
