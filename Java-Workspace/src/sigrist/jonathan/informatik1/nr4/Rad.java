package sigrist.jonathan.informatik1.nr4;

/**
 * Die Klasse beinhaltet alle Teile eines Rades.
 * 
 * @author jonathan
 *
 */
public class Rad {
	
	private Felge	felge;
	private Reifen	reifen;
	
	/**
	 * Erstellt ein neues Rad.
	 * 
	 * @param felge
	 *            Die Felge des Rades.
	 * @param reifen
	 *            Der Reifen des Rades.
	 */
	public Rad(Felge felge, Reifen reifen) {
		setFelge(felge);
		setReifen(reifen);
	}
	
	/**
	 * Ersetzt die Felge des Rades.
	 * 
	 * @param felge
	 *            Die neue Felge.
	 */
	public void setFelge(Felge felge) {
		this.felge = felge;
	}
	
	/**
	 * Gibt die momentane Felge des Rades zurueck.
	 * 
	 * @return Die Felge
	 */
	public Felge getFelge() {
		return felge;
	}
	
	/**
	 * Ersetzt den Reifen des Rades.
	 * 
	 * @param reifen
	 *            Der neue Reifen.
	 */
	public void setReifen(Reifen reifen) {
		this.reifen = reifen;
	}
	
	/**
	 * Gibt den momentanen Reifen zurueck.
	 * 
	 * @return Der Reifen
	 */
	public Reifen getReifen() {
		return reifen;
	}
	
	/**
	 * Berechnet aus den Teilkosten des Rades die Gesammtkosten.
	 * 
	 * @return Die Kosten des Rades.
	 */
	public int getKosten() {
		
		/*
		 * Methode gibt die Summe aller Teilkosten der, soweit vorhandenen Radteile aus. Falls eine
		 * Komponente null sein sollte, dann wird sie uebersprungen.
		 */
		
		return (felge != null ? felge.getKosten() : 0) + (reifen != null ? reifen.getKosten() : 0);
	}
	
}
