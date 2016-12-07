package sigrist.jonathan.informatik1.nr20;

import java.util.ArrayList;
import java.util.List;

/**
 * Ein Kind mit Geschenkwuenschen.
 */
public class Kind {
	private String			name;
	private List<Geschenk>	geschenke;
	
	/**
	 * Legt ein neues Kind mit einem Namen an
	 * 
	 * @param name
	 *            Name des Kindes
	 */
	public Kind(String name) {
		this.name = name;
		this.geschenke = new ArrayList<>();
	}
	
	/**
	 * Weist dem Kind einen Geschenkwunsch zu.
	 * 
	 * @param geschenk
	 *            Geschenk das sich das Kind wuenscht.
	 */
	public void addGeschenk(Geschenk geschenk) {
		geschenke.add(geschenk);
	}
	
	/**
	 * @return Gibt den Namen des Kindes zurueck.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return Gibt die Liste aller Geschenkwuensche des Kindes zurueck.
	 */
	public List<Geschenk> getGeschenke() {
		return geschenke;
	}
}
