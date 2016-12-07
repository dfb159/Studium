package sigrist.jonathan.informatik1.nr20;

import java.util.ArrayList;

/**
 * Verwaltet das Set aller bekannten Kinder.
 */
public class KinderListe {
	
	/**
	 * Interne Liste aller Kinder.
	 */
	private static ArrayList<Kind> kinder = new ArrayList<>();
	
	/**
	 * Gibt ein Kind zurueck. Falls es nicht gefunden wird, wird ein neues angelegt.
	 * 
	 * @param nameDesKindes
	 *            Name des Kindes.
	 * @return Kind
	 * @throws FalscherNameException
	 *             falls der Name leer ist.
	 */
	public static Kind getKind(String nameDesKindes) throws FalscherNameException {
		if (nameDesKindes == null || nameDesKindes.equals("")) {
			throw new FalscherNameException("Der Name ist ungueltig!");
		} else {
			for (Kind kind : kinder) {
				if (nameDesKindes.equals(kind.getName())) {
					return kind;
				}
			}
		}
		Kind neuesKind = new Kind(nameDesKindes);
		kinder.add(neuesKind);
		return neuesKind;
	}
	
	/**
	 * Sucht ein Kind mit dem entsprechenden Namen
	 * 
	 * @param nameDesKindes
	 *            Name des Kindes
	 * @return Kind
	 * @throws FalscherNameException
	 *             falls der Name leer ist.
	 * @throws KindNotFoundException
	 *             falls kein Kind mit dem Namen gefunden wurde.
	 */
	public static Kind sucheKind(String nameDesKindes)
			throws FalscherNameException, KindNotFoundException {
		if (nameDesKindes == null || nameDesKindes.equals("")) {
			throw new FalscherNameException("Der Name ist ungueltig!");
		}
		for (Kind kind : kinder) {
			if (nameDesKindes.equals(kind.getName())) {
				return kind;
			}
		}
		throw new KindNotFoundException("Kind nicht gefunden!");
	}
}
