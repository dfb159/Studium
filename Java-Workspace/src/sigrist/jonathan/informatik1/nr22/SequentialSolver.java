package sigrist.jonathan.informatik1.nr22;

public class SequentialSolver extends Solver {
	
	private boolean abbruch = false;
	
	public SequentialSolver(int oben, int unten, int links, int rechts, double epsilon,
			HitzePanel.DatenMatrix matrix, SolverObserver toBeRefreshed) {
		super(oben, unten, links, rechts, epsilon, matrix, toBeRefreshed);
	}
	
	/**
	 * Startet einen Subthread, welcher die Berechnung bis zu einer Genauigkeit von epsilon
	 * durchfuehrt. Das Programm kann dann weiter ausgefuehrt werden, was eine Betrachtung des
	 * Bildes weiterhin ermoeglicht.
	 */
	public void solve() {
		new SequencialThread().start();
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
	
	/**
	 * Subthread um eine Visualisierung der Rechnung zu ermoeglichen.
	 * 
	 * @author Carolin Wortmann, Leonhard Segger, Jonathan Sigrist
	 *
	 */
	private class SequencialThread extends Thread {
		
		/**
		 * Startet die Berechnung des Bildes
		 */
		public void run() {
			abbruch = false;
			while (!abbruch) {
				abbruch = true;
				for (int x = 0; x < matrix.getSize(); x++) {
					for (int y = 0; y < matrix.getSize(); y++) {
						double oben = getValue(x, y - 1);
						double unten = getValue(x, y + 1);
						double links = getValue(x - 1, y);
						double rechts = getValue(x + 1, y);
						
						double value = 0.25 * (oben + unten + links + rechts);
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
	}
}
