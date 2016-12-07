package sigrist.jonathan.informatik1.nr17;

/**
 * Klasse enthaelt die geforderten Klassenattribute und Methoden fuer ein Auto. Dabei werden die
 * Methoden des implementierten Interfaces genauer definiert.
 * 
 * @author Carolin Wortmann, Leonhard Segger, Jonathan Sigrist
 *
 */
public abstract class Auto implements Fahrzeug {
	
	protected int	geschwindigkeit;
	protected int	anzahlSitze;
	protected int	anzahlRaeder;
	
	/**
	 * Der Konstruktor setzt alle Klassenparameter
	 * 
	 * @param geschwindigkeit
	 *            Die Geschwindigkeit des Autos
	 * @param anzahlSitze
	 *            Die Anzahl an Sitzen vom Auto
	 * @param anzahlRaeder
	 *            Die Anzahl an Raedern
	 */
	public Auto(int geschwindigkeit, int anzahlSitze, int anzahlRaeder) {
		this.geschwindigkeit = geschwindigkeit;
		this.anzahlSitze = anzahlSitze;
		this.anzahlRaeder = anzahlRaeder;
	}
	
	/**
	 * Getter fuer die Anzahl an Raedern
	 * 
	 * @return Die momentane Anzahl an Raedern
	 */
	public int getAnzahlRaeder() {
		return anzahlRaeder;
	}
	
	/**
	 * Setter fuer die Anzahl an Raedern
	 * 
	 * @param anzahlRaeder
	 *            Die neue Anzahl an Raedern
	 */
	public void setAnzahlRaeder(int anzahlRaeder) {
		this.anzahlRaeder = anzahlRaeder;
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
	
}
