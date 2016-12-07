package sigrist.jonathan.informatik1.nr18;

/**
 * Die Klasse stellt eine erweiterte Exception zur Verfuegung, welche in der Klasse Stapel geworfen
 * werden kann.
 * 
 * @author Carolin Wortmann, Leonhard Segger, Jonathan Sigrist
 *
 */
public class StapelLeerException extends Exception {
	
	/**
	 * Der Konstruktor erstellt eine neue Exception und fuegt eine leserliche Beschreibung hinzu.
	 */
	public StapelLeerException() {
		super("Der Stapel enthaelt kein weiteres Element!");
	}
	
}
