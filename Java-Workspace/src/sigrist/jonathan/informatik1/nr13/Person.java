package sigrist.jonathan.informatik1.nr13;

public class Person {

    /** Liste aller Freunde*/
    private Person[] freundesliste;
    /** Name der Person*/
    private String name;

    /**
     * Konstruktor
     * @param name Name der Person.
     * @param freundesliste Freundesliste der Person.
     */
    public Person(String name, Person[] freundesliste) {
        this.freundesliste = freundesliste;
        this.name = name;
    }

    /**
     * Gibt die Freundesliste einer Person zurueck.
     * @return Freundesliste als Array.
     */
    public Person[] getFreunde() {
        return freundesliste;
    }

    /**
     * Ermoeglicht das nachtraegliche veraendern der Freundesliste.
     * @param freundesliste neue Freundesliste.
     */
    public void setFreunde(Person[] freundesliste) {
        this.freundesliste = freundesliste;
    }

    /**
     * Gibt den Namen der Person zurueck.
     * @return Name der Person.
     */
    public String getName() {
        return name;
    }

    /**
     * Stellt fest, ob eine andere Person ein Freund dieser Person ist.
     * @param person Person nach der in der Freundesliste gesucht wird.
     * @return true wenn die Person ein Freund ist; andernfalls false.
     */
    public boolean isFriendWith(Person person) {
        for (Person p : freundesliste) {
            if (p != null && person != null && p.getName().equals(person.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gibt an, ob zwei Personen gleich sind.
     * @param person Person mit der verglichen wird.
     * @return true, wenn die Namen der beiden Personen identisch sind; andernfalls false.
     */
    public boolean equals(Person person){
        return name.equals(person.getName());
    }

    /**
     * Gibt die Person als String zurueck.
     * @return Name der Person.
     */
    public String toString(){
        return getName();
    }
}