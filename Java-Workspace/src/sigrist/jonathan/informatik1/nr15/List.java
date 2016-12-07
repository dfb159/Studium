package sigrist.jonathan.informatik1.nr15;

/**
 * Liste zum verwalten von mehreren Objekten
 */
public class List {
	
	/**
	 * Repraesentiert ein Element der Liste. Kapselt den Inhalt der gespeichert werden soll mit
	 * zusaetzlichen Informationen, welche notwendig sind fuer die Listenstruktur
	 */
	private static class ListElem {
		/** naechstes Listenelement */
		ListElem	succ;
		/** Inhalt des Listenelements */
		Object		content;
		
		/**
		 * Konstruktor
		 * 
		 * @param cont
		 *            Inhalt des Elements
		 * @param next
		 *            naechstes Element der Liste
		 */
		ListElem(Object cont, ListElem next) {
			succ = next;
			content = cont;
		}
	}
	
	/**
	 * Der ListIterator stellt eine Moeglichkeit zur Verfuegung auf der Liste zu navigieren. Der
	 * Iterator startet dabei beim ersten Element der Liste und kann nur in eine Richtung
	 * navigieren.
	 */
	public class ListIterator {
		/**
		 * Ein Zeiger, welcher das aktuelle Element vorhaelt.
		 */
		protected ListElem current;
		
		/**
		 * Konstruktor Dieser zeigt beim Erstellen auf das erste Element (head) der Liste.
		 */
		public ListIterator() {
			current = head;
		}
		
		/**
		 * Gibt an, ob in der Liste ein weiteres Element vorhanden ist.
		 * 
		 * @return true, falls noch ein weiteres Element vorhanden ist; andernfalls false.
		 */
		public boolean hasNext() {
			return (current != null);
		}
		
		/**
		 * Gibt das naechste Element der Liste zurueck. Kann zum Fehler fuehren, falls kein weiteres
		 * Element existiert. Mit {@link #hasNext()} kann geprueft werden, ob ein weiteres Element
		 * verfuegbar ist.
		 * 
		 * @return Wert des naechsten Elements.
		 */
		public Object next() {
			Object val = current.content;
			current = current.succ;
			return val;
		}
	}
	
	/** Erstes Element der Liste */
	protected ListElem head = null;
	
	/**
	 * Fuegt ein Element an der ersten Position der Liste ein. Die bereits vorhandene Liste wird an
	 * dieses neue Element angeknuepft.
	 * 
	 * @param item
	 *            Wert des neuen Elements.
	 */
	public void insertFirst(Object item) {
		head = new ListElem(item, head);
	}
	
	/**
	 * Fuegt ein Element an einer bestimmten Position der Liste ein. Ein index von 0 referenziert
	 * dabei auf das erste Element und ist damit aequivalent zu {@link #insertFirst}. Die Liste bis
	 * zu Position index bleibt unveraendert. Die restliche Liste wird an das neue Element
	 * angeknuepft.
	 * 
	 * @param index
	 *            Position des neuen Elements
	 * @param item
	 *            Wert des neuen Elements
	 * @return false, falls der index ausserhalb der Grenzen der Liste liegt; andernfalls true.
	 */
	public boolean insert(int index, Object item) {
		if (index < 0)
			return false;
		if (index == 0) {
			insertFirst(item);
			return true;
		}
		ListIterator lI = iterator();
		for (int i = 0; i < index - 1; i++) {
			if (lI.hasNext())
				lI.next();
			else
				return false;
		}
		lI.current.succ = new ListElem(item, lI.current.succ);
		return true;
	}
	
	/**
	 * Entfernt ein Element aus der Liste. Die ueberbleibenden Elemente bleiben dabei erhalten und
	 * vom Iterator navigierbar.
	 * 
	 * @param index
	 *            Position des Elements das geloescht werden soll.
	 * @return null, falls der index ausserhalb der Grenzen der Liste liegt; andernfalls den Wert
	 *         des geloeschten Elements.
	 */
	public Object remove(int index) {
		if (index < 0)
			return null;
		if (index == 0) {
			Object headObj = head.content;
			head = head.succ;
			return headObj;
		}
		ListIterator lI = iterator();
		for (int i = 0; i < index - 1; i++) {
			if (lI.hasNext())
				lI.next();
			else
				return null;
		}
		Object obj = lI.current.succ.content;
		lI.current.succ = lI.current.succ.succ;
		return obj;
		
	}
	
	/**
	 * Gibt eine Instanz des ListIterators fuer diese Liste zurueck.
	 * 
	 * @return ListIterator
	 */
	public ListIterator iterator() {
		return new ListIterator();
	}
}
