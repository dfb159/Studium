package sigrist.jonathan.informatik1.nr6;

/**
 * Klasse Gegenstand mit Parametern fuer die Erkennung durch das Orakel
 * @author jonathan
 *
 */
public class Gegenstand {

    private int gewicht;
    private String material;
    private boolean elektrisch;

    /**
     * Erstellt einen neuen Gegenstand, welcher bestimmte Eigenschaften besitzt
     * @param gewicht Das Gewicht des Gegenstandes
     * @param material Das Material des Gegenstandes
     * @param elektrisch Ist der Gegenstand elektrisch leitfaehig
     */
    public Gegenstand(int gewicht, String material, boolean elektrisch) {
        this.gewicht = gewicht;
        this.material = material;
        this.elektrisch = elektrisch;
    }

    /**
     * Gibt das Gewicht des Gegenstandes zurueck
     * @return Das Gewicht
     */
    public int getGewicht() {
        return gewicht;
    }

    /**
     * Gibt das Material des Gegenstandes zurueck
     * @return Das Material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Gibt zurueck, ob der Gegenstand elektrisch leitfaehig ist
     * @return Leitfaehigkeit
     */
    public boolean isElektrisch() {
        return elektrisch;
    }
}