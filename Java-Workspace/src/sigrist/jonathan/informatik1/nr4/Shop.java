package sigrist.jonathan.informatik1.nr4;

/**
 * Test Klasse fuer Fahrrad
 * @author jonathan
 *
 */
public class Shop {
	
	
	public static void main(String[] args) {
		Fahrrad fahrrad = new Fahrrad(
				new Rad(new Felge(15),
						new Reifen(24)),
				new Rad(
						new Felge(15),
						new Reifen(24)),
				new Rahmen(149)
				);
		System.out.println("Kosten des Fahrrades: " + fahrrad.getKosten()+ "EUR");
	}
	
}
