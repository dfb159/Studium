package sigrist.jonathan.informatik1.nr20;

public class Geschenk {
	
	private String		title;
	
	private GeschenkTyp	typ;
	
	/**
	 * Erstellt ein neues Geschenk
	 * 
	 * @param title
	 *            Titel des Geschenks
	 * @param typ
	 *            Groesse des Geschenks
	 * @throws GeschenkOhneTitelException
	 *             falls der Titel leer ist.
	 */
	public Geschenk(String title, GeschenkTyp typ) throws GeschenkOhneTitelException {
		if (title == null || title.equals("")) {
			throw new GeschenkOhneTitelException("Das Geschenk hat keinen Titel!");
		}
		this.title = title;
		this.typ = typ;
	}
	
	public String getTitle() {
		return title;
	}
	
	public GeschenkTyp getTyp() {
		return typ;
	}
	
	public String toString() {
		return this.title + " (" + typ + ")";
	}
	
}
