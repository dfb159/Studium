package sigrist.jonathan.informatik1.nr5;

/**
 * Klasse stellt die geforderte Methode erzeugeStammbaum zur Verfuegung
 * 
 * @author jonathan
 *
 */
public class Standesamt {
	
	/**
	 * Main Methode zum testen der Methode
	 * 
	 * @param args
	 *            Arguments
	 */
	public static void main(String[] args) {
		Standesamt s = new Standesamt();
		s.printStammbaum(s.erzeugeStammbaum());
	}
	
	/**
	 * Gibt den Stammbaum zu einer spezifischen Person in der Konsole aus
	 * 
	 * @param p
	 *            Die Person fuer den der Stammbaum ausgegeben werden soll
	 */
	public void printStammbaum(Person p) {
		if (p != null) {
			if (p.getMutter() != null) {
				System.out.printf("%1$10s", (p.getMutter().getMutter() != null
						? p.getMutter().getMutter().getName() : " -------- "));
				System.out.printf("%1$10s", (p.getMutter().getVater() != null
						? p.getMutter().getVater().getName() : " -------- "));
			}
			if (p.getVater() != null) {
				System.out.printf("%1$10s", (p.getVater().getMutter() != null
						? p.getVater().getMutter().getName() : " -------- "));
				System.out.printf("%1$10s", (p.getVater().getVater() != null
						? p.getVater().getVater().getName() : " -------- "));
			}
			
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.printf("%1$15s",
					(p.getMutter() != null ? p.getMutter().getName() : " --------- "));
			System.out.printf("%1$20s",
					(p.getVater() != null ? p.getVater().getName() : " --------- "));
			
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.printf("%1$25s", p.getName());
		} else {
			System.out.println("No person!");
		}
	}
	
	/**
	 * Erstellt einen HardCode Stammbaum fuer die Person Magdalena
	 * 
	 * @return Person Magdalena mit gespeicherten Verwanten
	 */
	public Person erzeugeStammbaum() {
		Person martina = new Person("Martina");
		Person achim = new Person("Achim");
		Person paul = new Person("Paul");
		Person johanna = new Person("Johanna");
		
		Person lena = new Person("Lena", martina, achim);
		Person bernd = new Person("Bernd", johanna, paul);
		martina.setKind(lena);
		achim.setKind(lena);
		paul.setKind(bernd);
		johanna.setKind(bernd);
		
		Person magdalena = new Person("Magdalena", lena, bernd);
		lena.setKind(magdalena);
		bernd.setKind(magdalena);
		
		return magdalena;
	}
	
}
