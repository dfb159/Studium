package sigrist.jonathan.informatik1.nr6;

/**
 * Klasse stellt die geforderte Methode wasIstEs zur Verfuegung
 * 
 * @author jonathan
 *
 */
public class Orakel {
	
	//@formatter:off
	private final Gegenstand[] obj = new Gegenstand[] {
			new Gegenstand(150, "Gummi", false),
			new Gegenstand(150, "Gummi", true),
			new Gegenstand(150, "Plastik", true),
			new Gegenstand(3500, "Metal", true),
			new Gegenstand(90000, "Fleisch", false),
			new Gegenstand(2500000, "Holz", false),
			new Gegenstand(19200000, "Metal", true),
			new Gegenstand(42530000, "Wasser", true),
			new Gegenstand(15000, "Holz", false),
			new Gegenstand(300, "Fleisch", false),
			new Gegenstand(1500, "Metall", true)
	};
	private final String[] name = new String[] {
			"Gummiente",
			"Stromkabel",
			"Smartphone", 
			"Computer",
			"Student",
			"Baum",
			"Supercomputer",
			"Wolken",
			"Tisch",
			"Steak",
			"Laptop"
	};
	//@formatter:on
	
	/**
	 * Die Methode gibt den Namen des zu ueberpruefenden Gegenstandes zurueck
	 * 
	 * @param gegenstand
	 *            Der zu ueberpruefende Gegenstand
	 * @return Der Name des Gegenstandes
	 */
	public String wasIstEs(Gegenstand gegenstand) {
		
		if (gegenstand == null) {
			return "kein Gegenstand angegeben!";
		}
		
		for (int i = 0; i < obj.length; i++) {
			if (vergleicheGegenstand(gegenstand, obj[i])) {
				return name[i];
			}
		}
		
		return getString(gegenstand);
	}
	
	/**
	 * Alternative Hardcode-Methode fuer wasIstEs
	 * 
	 * @param g
	 *            Der zu ueberpruefende Gegenstand
	 * @return Der Name des Gegenstandes
	 */
	public String wasIstEs2(Gegenstand g) {
		if (g.getGewicht() > 4000) {
			if (g.isElektrisch()) {
				if (g.getMaterial().equals("Wasser"))
					return "Wolken";
				else
					return "Supercomputer";
			} else {
				if (g.getMaterial().equals("Fleisch"))
					return "Student";
				else
					return "Baum";
			}
		} else {
			if (g.getMaterial().equals("Gummi")) {
				if (g.isElektrisch())
					return "Stromkabel";
				else
					return "Gummiente";
			} else {
				if (g.getGewicht() == 150)
					return "Smartphone";
				else
					return "Computer";
			}
		}
	}
	
	/**
	 * Gibt einen den Gegenstand umschreibenden String zurueck
	 * 
	 * @param gegenstand
	 *            Der zu beschreibender Gegenstand
	 * @return Die Beschreibung
	 */
	private String getString(Gegenstand gegenstand) {
		return "(" + gegenstand.getGewicht() + ", " + gegenstand.getMaterial() + ", "
				+ gegenstand.isElektrisch() + ")";
	}
	
	/**
	 * Vergleicht zwei Gegenstaende auf gleiche Attribute
	 * 
	 * @param g1
	 *            Der erste Gegenstand
	 * @param g2
	 *            Der zweite Gegenstand
	 * @return true, falls alle Attribute identisch sind, sonst false
	 */
	private boolean vergleicheGegenstand(Gegenstand g1, Gegenstand g2) {
		
		/*
		 * Vergleiche ein Gegenstand mit einem anderen. Wenn beide die gleichen Attribute haben,
		 * dann gebe true zur�ck, sonst gebe false zur�ck.
		 */
		if (g1.getMaterial() == null || g2.getMaterial() == null) {
			return false;
		}
		if (g1.getGewicht() == g2.getGewicht() && g1.getMaterial().equals(g2.getMaterial())
				&& g1.isElektrisch() == g2.isElektrisch()) {
			return true;
		} else {
			return false;
		}
	}
}
