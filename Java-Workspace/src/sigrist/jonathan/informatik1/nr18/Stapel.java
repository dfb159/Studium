package sigrist.jonathan.informatik1.nr18;

/**
 * Die Klasse stellt ein Datenkonstrukt nach FILO-Prinzip zur Verfuegung. Es koennen Daten oben auf
 * den Stapel draufgelegt oder oben vom Stapel herunter genommen werden.
 * 
 * @author Carolin Wortmann, Leonhard Segger, Jonathan Sigrist
 * 
 * @param <T>
 *            Der generische Datentyp der zu speichernden Daten
 */
public class Stapel<T> {
	
	/**
	 * Die innere Klasse stellt ein Element des Stapels dar. Dabei enthaelt sie das naechste Element
	 * und das jeweilig repraesentative Objekt der Klasse T.
	 * 
	 * @author jonathan
	 *
	 */
	class Item {
		
		Item	next;
		T		object;
		
		/**
		 * Konstruktor der inneren Klasse setzt die Klassenattribute der inneren Klasse. Diese sind
		 * nicht protected oder private, da ohnehin nur die Klasse Stapel auf sie zugreifen kann.
		 * 
		 * @param next
		 *            Das naechste Stapelelement
		 * @param obj
		 *            Das repraesentierende Objekt
		 */
		Item(Item next, T obj) {
			this.next = next;
			this.object = obj;
		}
		
		/**
		 * Fuer bessere Leserlichkeit kann die Methode rekursiv eine Kette von allen
		 * toString-Methoden von Folgeelementen erzeugen.
		 */
		public String toString() {
			return object.toString() + (next == null ? "" : ", " + next.toString());
		}
	}
	
	protected Item head = null;
	
	/**
	 * Legt ein neues Objekt der Klasse T auf den Stapel und ist somit das oberste Objekt.
	 * 
	 * @param item
	 *            Neues Stapelobjekt
	 */
	public void legeDrauf(T item) {
		if (istLeer()) {
			head = new Item(null, item);
		} else {
			head = new Item(head, item);
		}
	}
	
	/**
	 * Gibt das oberste Objekt zurueck und loescht es vom Stapel.
	 * 
	 * @return Das oberste Objekt des Stapels
	 * @throws StapelLeerException
	 *             wird geworfen, wenn der Stapel keine Objekte mehr enthaelt
	 */
	public T nehmeHerunter() throws StapelLeerException {
		if (istLeer()) {
			throw new StapelLeerException();
		} else {
			T obj = head.object;
			head = head.next;
			return obj;
		}
	}
	
	/**
	 * Gibt zurueck, ob der Stapel leer ist.
	 * 
	 * @return true, falls der Stapel leer ist, sonst false
	 */
	public boolean istLeer() {
		return head == null;
	}
	
	/**
	 * Erstellt einen repraesentativen String fuer den Stapel. Dabei faengt die Liste mit dem
	 * obersten Stapelobjekt an.
	 */
	public String toString() {
		return "[" + (head == null ? "" : head.toString()) + "]";
	}
}
