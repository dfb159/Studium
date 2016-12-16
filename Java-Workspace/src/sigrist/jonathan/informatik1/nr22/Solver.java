package sigrist.jonathan.informatik1.nr22;

/**
 * Abstrakte Klasse des Solver. Gibt an welche Methoden implementiert werden muessen und uebernimmt
 * die Initialisierung der ausgehenden Variablen.
 */
public abstract class Solver {
	
	/**
	 * Temperatur der oberen Kante der Metallplatte
	 */
	protected final int						oben;
	
	/**
	 * Temperatur der unteren Kante der Metallplatte
	 */
	protected final int						unten;
	
	/**
	 * Temperatur der linken Kante der Metallplatte
	 */
	protected final int						links;
	
	/**
	 * Temperatur der rechten Kante der Metallplatte
	 */
	protected final int						rechts;
	
	/**
	 * Datenspeicher der Messpunkte
	 */
	protected final HitzePanel.DatenMatrix	matrix;
	
	private final SolverObserver			toBeRefreshed;
	
	/**
	 * Abbruchkriterium
	 */
	protected final double					epsilon;
	
	/**
	 * Initialisiert einen Solver.
	 * 
	 * @param oben
	 *            Temperatur der unteren Kante der Metallplatte
	 * @param unten
	 *            Temperatur der oberen Kante der Metallplatte
	 * @param links
	 *            Temperatur der linken Kante der Metallplatte
	 * @param rechts
	 *            Temperatur der rechten Kante der Metallplatte
	 * @param epsilon
	 *            Abbruchkriterium
	 * @param matrix
	 *            Datenspeicher der Messpunkte
	 * @param toBeRefreshed
	 *            HitzeFrame, welcher nach der Beendigung aktualisiert werden soll.
	 */
	public Solver(int oben, int unten, int links, int rechts, double epsilon,
			HitzePanel.DatenMatrix matrix, SolverObserver toBeRefreshed) {
		this.oben = oben;
		this.unten = unten;
		this.links = links;
		this.rechts = rechts;
		this.epsilon = epsilon;
		this.matrix = matrix;
		this.toBeRefreshed = toBeRefreshed;
	}
	
	/**
	 * Zu implementieren. Loese das Problem anhand der Variablen oben, unten, links, rechts und
	 * epsilon. Dabei wird die matrix verwendet, um die Temperaturentwicklung der Messpunkte der
	 * Metallplatte nachzuhalten.
	 */
	abstract void solve();
	
	/**
	 * Muss aufgerufen werden, nachdem der Solver terminiert.
	 */
	protected void finish() {
		if (toBeRefreshed != null) {
			toBeRefreshed.refresh();
		}
	}
}
