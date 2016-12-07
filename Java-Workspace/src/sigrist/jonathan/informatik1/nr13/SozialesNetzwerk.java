package sigrist.jonathan.informatik1.nr13;

/**
 * 
 * @author jonathan
 *
 */
public class SozialesNetzwerk {
	
	public static void main(String[] args) {
		SozialesNetzwerk facebook = new SozialesNetzwerk();
		Person[] p = new Person[] {new Person("Theresa", new Person[] {}),
				new Person("Thomas", new Person[] {}), new Person("Kerstin", new Person[] {}),
				new Person("Adam", new Person[] {}), new Person("Michael", new Person[] {}),};
		p[1].setFreunde(new Person[] {p[0]});
		p[2].setFreunde(new Person[] {p[1], p[3], p[4]});
		p[3].setFreunde(new Person[] {p[4]});
		p[4].setFreunde(new Person[] {p[2]});
		
		Person[] kette = facebook.getFreundschaftskette(p[3], p[0]);
		System.out.println(kette + "Fertig");
		
	}
	
	/**
	 * Variable Groesse ueber die maximalen sozialen Verbindungspunkte
	 */
	private final int maxIterations = 6;
	
	/**
	 * Die Methode sucht fuer jeden Freund eine Kette zu der geforderten Person. Schritt fuer
	 * Schritt wird solange die maximale Laenge der Kette heruntergesetzt, bis keine Kette mehr
	 * gefunden wird. Dies garantiert die kuerzeste Freundschaftskette.
	 * 
	 * @param start
	 *            Die Startperson der Kette
	 * @param ende
	 *            Die Endperson der Kette
	 * @return Die vollstaendige Kette der beiden Personen mit einer maximalen Laenge von 6
	 */
	public Person[] getFreundschaftskette(Person start, Person ende) {
		int minLength = maxIterations;
		Person[] shortestKette = new Person[maxIterations];
		Person[] kette = new Person[maxIterations];
		
		boolean flagStillExist = true;
		for (int l = maxIterations; flagStillExist; l--) {
			flagStillExist = false;
			kette = new Person[maxIterations];
			if (recursiveFreundschaftskette(kette, 0, start, ende, l)) {
				flagStillExist = true;
				int length = getKetteLength(kette);
				if (length < minLength) {
					shortestKette = kette;
					minLength = length;
				}
			}
		}
		return shortestKette;
		
	}
	
	/**
	 * Diese Untermethode sucht rekursiv fuer die angegebene Startperson einen Weg zur Zielperson.
	 * Falls die maximale Kettenlaenge oder die Zielperson erreicht ist, wird rueckwirkend eine
	 * Kette der Personen aufgebaut.
	 * 
	 * @param p
	 *            Die Zielkette
	 * @param iteration
	 *            Die momentane Iteration der Rekursion.
	 * @param current
	 *            Die momentane Person, welche getestet werden soll, ob sie eine Verbindungskette
	 *            aufweist.
	 * @param ende
	 *            Die Zielperson
	 * @param maxIterations
	 *            Die maximale Anzahl an Rekursionen.
	 * @return true, falls eine Kette gefunden wurde; sonst false
	 */
	private boolean recursiveFreundschaftskette(Person[] p, int iteration, Person current,
			Person ende, int maxIterations) {
		if (iteration >= maxIterations)
			return false;
		if (current.equals(ende))
			return true;
		for (Person freund : current.getFreunde()) {
			if (recursiveFreundschaftskette(p, iteration + 1, freund, ende, maxIterations)) {
				p[iteration] = freund;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Die Methode gibt die tatsaechliche Laenge der Kette wieder. Dabei werden alle null Werte am
	 * Ende der Kette ignoriert.
	 * 
	 * @param kette
	 *            Die zu untersuchende Kette.
	 * @return Die Laenge der Kette ohne null
	 */
	private int getKetteLength(Person[] kette) {
		int i = kette.length - 1;
		while (kette[i--] == null)
			;
		return i;
	}
	
	private Person[]	freundeskette;
	private int			maxLength;
	
	public Person[] solutionGetFreundeskette(Person start, Person ende) {
		maxLength = 6;
		freundeskette = new Person[maxLength];
		solutionFindPerson(start, ende, 0);
		return freundeskette;
	}
	
	public boolean solutionFindPerson(Person current, Person ende, int depth) {
		boolean found = false;
		if (depth < maxLength) {
			for (Person freund : current.getFreunde()) {
				if (ende.equals(freund) && depth < maxLength) {
					maxLength = depth;
					freundeskette = new Person[maxLength];
					freundeskette[depth] = freund;
					return true;
				}
				if (solutionFindPerson(freund, ende, depth + 1)) {
					freundeskette[depth] = freund;
					found = true;
				}
			}
		}
		return found;
	}
	
}
