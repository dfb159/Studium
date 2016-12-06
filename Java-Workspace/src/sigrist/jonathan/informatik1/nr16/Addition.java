package sigrist.jonathan.informatik1.nr16;
/**
 * Stellt die Addition zweier berechenbarer Objekte dar.
 */
public class Addition extends Summe{

    /**
     * Konstruktor
     * @param summand1 erster Summand
     * @param summand2 zweiter Summand
     */
    public Addition(Berechenbar summand1, Berechenbar summand2){
        super(summand1, summand2);
    }
}