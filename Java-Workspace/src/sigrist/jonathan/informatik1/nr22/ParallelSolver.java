package sigrist.jonathan.informatik1.nr22;

public class ParallelSolver extends Solver {
	
	ParallelThread	thread1, thread2, thread3, thread4;
	
	/**
	 * true, falls Abbruchbedingung erfuellt.
	 */
	private boolean	abbruch	= true;
	
	public ParallelSolver(int oben, int unten, int links, int rechts, double epsilon,
			HitzePanel.DatenMatrix matrix, SolverObserver toBeRefreshed) {
		super(oben, unten, links, rechts, epsilon, matrix, toBeRefreshed);
	}
	
	/**
	 * Erstellt 4 Subthreads, welche aufeinander warten und jeweils einen Teil des Bildes errechnen.
	 */
	public void solve() {
		int halbe = matrix.getSize() / 2;
		thread1 = new ParallelThread(0, 0, halbe, halbe);
		thread2 = new ParallelThread(halbe, 0, matrix.getSize(), halbe);
		thread3 = new ParallelThread(0, halbe, halbe, matrix.getSize());
		thread4 = new ParallelThread(halbe, halbe, matrix.getSize(), matrix.getSize());
		
		abbruch = false;
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}
	
	/**
	 * Innere Klasse von ParallelSolver stellt einen Thread zur Verfuegung, um einen Teil des Bildes
	 * zu berechnen.
	 * 
	 * @author Carolin Wortmann, Leonhard Segger, Jonathan Sigrist
	 *
	 */
	private class ParallelThread extends Thread {
		/**
		 * Bereich der Berechnung
		 */
		private int x1, y1, x2, y2;
		
		/**
		 * Erstellt einen neuen Thread, welcher sich um ein rechteckigen Ausschnitt des Bildes
		 * kuemmert.
		 * 
		 * @param x1
		 *            Anfangspunkt in X-Richtung
		 * @param y1
		 *            Anfangspunkt in Y-Richtung
		 * @param x2
		 *            Endpunkt in X-Richtung (nicht mehr berechnet)
		 * @param y2
		 *            Endpunkt in Y-Richtung (nicht mehr berechnet)
		 */
		public ParallelThread(int x1, int y1, int x2, int y2) {
			super();
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		
		/**
		 * Berechnet einen Teil des Bildes und wartet auf andere Threads.
		 */
		public void run() {
			while (!abbruch) {
				boolean beenden = true;
				for (int x = x1; x < x2; x++) {
					for (int y = y1; y < y2; y++) {
						double oben = getValue(x, y - 1);
						double unten = getValue(x, y + 1);
						double links = getValue(x - 1, y);
						double rechts = getValue(x + 1, y);
						
						double value = 0.25 * (oben + unten + links + rechts);
						double delta = Math.abs(value - getValue(x, y));
						if (delta > epsilon) {
							beenden = false;
						}
						matrix.setValue(value, x, y);
					}
				}
				warteAufThreads(beenden);
			}
		}
	}
	
	/**
	 * Gibt den Wert der Temperatur der Platte an der Stelle (x, y) zurueck.
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
	
	private int warteThreads = 0;
	private boolean tmpAbbruch = false;
	
	private synchronized void warteAufThreads(boolean beenden) {
		warteThreads++;
		if (beenden) {
			tmpAbbruch = true;
		}
		if (warteThreads != 4) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted: " + Thread.currentThread().toString());
			}
		} else {
			warteThreads = 0;
			matrix.nextIteration();
			if (tmpAbbruch) {
				abbruch = true;
				finish();
			}
			notifyAll();
		}
	}
}
