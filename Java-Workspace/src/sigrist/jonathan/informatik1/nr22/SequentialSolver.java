package sigrist.jonathan.informatik1.nr22;

public class SequentialSolver extends Solver {
	
	private boolean abbruch = false;
	
	public SequentialSolver(int oben, int unten, int links, int rechts, double epsilon,
			HitzePanel.DatenMatrix matrix, SolverObserver toBeRefreshed) {
		super(oben, unten, links, rechts, epsilon, matrix, toBeRefreshed);
	}
	
	/**
	 * Startet einen Subthread, welcher die Berechnung durchfuehrt. Das Programm kann dann weiter
	 * das Bild darstellen. Alternative fuer solve().
	 */
	public void solveSmooth() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				solve();
			}
		}).start();
	}
	
	/**
	 * Berechnet die Hitzeverteilung der Metallplatte bis zu einer Genauigkeit von epsilon. Dabei
	 * kann das Bild nicht weiter aktualisiert werden.
	 */
	public void solve() {
		abbruch = false;
		while (!abbruch) {
			abbruch = true;
			// Alle Punkte ueber Flaeche der Platte
			for (int x = 0; x < matrix.getSize(); x++) {
				for (int y = 0; y < matrix.getSize(); y++) {
					// Nachbartemperaturen
					double oben = getValue(x, y - 1);
					double unten = getValue(x, y + 1);
					double links = getValue(x - 1, y);
					double rechts = getValue(x + 1, y);
					
					// Mittelwert
					double value = 0.25 * (oben + unten + links + rechts);
					// Abbruchbedingung
					double delta = Math.abs(value - matrix.getValue(x, y));
					if (delta > epsilon) {
						abbruch = false;
					}
					matrix.setValue(value, x, y);
				}
			}
			matrix.nextIteration();
		}
		finish();
	}
	
	/**
	 * Gibt den Wert der Hitze der Platte an der Stelle (x, y) zurueck.
	 * 
	 * @param x
	 *            X-Position des Punktes
	 * @param y
	 *            Y-Position des Punktes
	 * @return Die Hitze im Punkt (x, y). Falls dieser ausserhalb der Platte liegt, wird der
	 *         Grenzwert der jeweiligen Seite zurueckgegeben.
	 */
	public double getValue(int x, int y) {
		if (x < 0) {
			return links;
		} else if (x >= matrix.getSize()) {
			return rechts;
		} else if (y < 0) {
			return oben;
		} else if (y >= matrix.getSize()) {
			return unten;
		} else {
			return matrix.getValue(x, y);
		}
	}
}
