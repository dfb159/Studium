package sigrist.jonathan.informatik1.nr4;

/**
 * Die Klasse enthaelt alle Teile eines Fahrrades (rahmen und vorder-/hinterreifen). Mit getKosten()
 * wird die Summe aller Teile zuruekgegeben.
 * 
 * @author jonathan
 *
 */
public class Fahrrad {
	
	private Rahmen	rahmen;
	private Rad		vRad, hRad;
	
	/**
	 * Der Konstruktor zum Erstellen eines neuen Fahrrades.
	 * 
	 * @param vorderrad
	 *            Das Vorderrad des Fahrrades.
	 * @param hinterrad
	 *            Das Hinterrad des Fahrrades.
	 * @param rahmen
	 *            Der Rahmen des Fahrrades.
	 */
	public Fahrrad(Rad vorderrad, Rad hinterrad, Rahmen rahmen) {
		vRad = vorderrad;
		hRad = hinterrad;
		this.rahmen = rahmen;
	}
	
	/**
	 * Setzt das Vorderrad des Fahrrades.
	 * 
	 * @param rad
	 *            Das neue Vorderrad
	 */
	public void setVorderrad(Rad rad) {
		vRad = rad;
	}
	
	/**
	 * Gibt das momentane Vorderrad zurueck.
	 * 
	 * @return Das Vorderrad
	 */
	public Rad getVorderrad() {
		return vRad;
	}
	
	/**
	 * Setzt das Hinterrad des Fahrrades.
	 * 
	 * @param rad
	 *            Das neue Hinterrad
	 */
	public void setHinterrad(Rad rad) {
		hRad = rad;
	}
	
	/**
	 * Gibt das momentane Hinterrad zurueck.
	 * 
	 * @return Das Hinterrad.
	 */
	public Rad getHinterrad() {
		return hRad;
	}
	
	/**
	 * Setzt den Rahmen fuer das Fahrrad.
	 * 
	 * @param rahmen
	 *            Der neue Rahmen
	 */
	public void setRahmen(Rahmen rahmen) {
		this.rahmen = rahmen;
	}
	
	/**
	 * Gibt den momentanen Rahmen des Fahrrades zurueck.
	 * 
	 * @return Der Rahmen
	 */
	public Rahmen getRahmen() {
		return this.rahmen;
	}
	
	/**
	 * Errechnet die Kosten des gesammten Fahrrades, aus denen der Teilkosten.
	 * 
	 * @return Die Kosten des Fahrrades
	 */
	public int getKosten() {
		return (rahmen != null ? rahmen.getKosten() : 0) + (vRad != null ? vRad.getKosten() : 0)
				+ (hRad != null ? hRad.getKosten() : 0);
	}
}
