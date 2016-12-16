package sigrist.jonathan.informatik1.nr22;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Stellt ein Panel mit einem blau rotes Buffered Imange zur Verfuegung, welches die Metallplatte
 * visialisiert.
 */
public class HitzePanel extends JPanel {
	
	// Bild der Metallplatte
	private BufferedImage	canvas;
	
	// Interner Speicher der Temperaturen aller Messpunkte.
	private DatenMatrix		matrix;
	
	// Groesse der Metallplatte
	private int				size;
	
	// Gibt an, ob nach jeder Iteration neugezeichnet wird.
	private boolean			refresh;
	
	/**
	 * Erstellt ein Hitzepanel. Wird nur zum Initialen Erstellen verwendet.
	 * 
	 * @param size
	 *            Greosse des Hitzepanels
	 */
	public HitzePanel(int size) {
		reset(size, false);
	}
	
	/**
	 * Setzt das Hitzepanel zuerueck. Ermoeglicht das Aendern der Groesse und stellt einen firschen
	 * Zustand der Metallplatte wieder her.
	 * 
	 * @param size
	 *            Groesse der Metalplatte
	 * @param refresh
	 *            Gibt an, ob nach jeder Iteration neugezeichnet wird.
	 */
	public void reset(int size, boolean refresh) {
		this.size = size;
		this.refresh = refresh;
		matrix = new DatenMatrix(size);
		canvas = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		drawDaten();
	}
	
	/**
	 * Gibt den Datenspeicher aller Messpunkte aus. Wird verwendet, um diesen dem Solver zu
	 * uebergeben.
	 * 
	 * @return DatenMatrix Datenspeicher aller Messpunkte
	 */
	public DatenMatrix getMatrix() {
		return this.matrix;
	}
	
	/**
	 * Zeichnet das Hitzepanel. Wird automatisch beim repaint und validate aufgerufen.
	 * 
	 * @param g
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(canvas, null, null);
	}
	
	/**
	 * Definiert die gewuenschte groesse des HitzePanels
	 * 
	 * @return
	 */
	public Dimension getPreferredSize() {
		return new Dimension(size, size);
	}
	
	/**
	 * Definiert die minimale groesse des HitzePanels
	 * 
	 * @return
	 */
	public Dimension getMinimumSize() {
		return new Dimension(size, size);
	}
	
	/**
	 * Zeichnet die Metallplatte anhand der Temperaturen des Internen Speichers. Temperatur 0
	 * entspricht der RGB Farbe (0,0,255) Temperatur 100 entspricht der RGB Farbe (255,0,0) Die
	 * Uebergaenge sind fliessend.
	 */
	public synchronized void drawDaten() {
		for (int x = 0; x < matrix.getSize(); x++) {
			for (int y = 0; y < matrix.getSize(); y++) {
				int red = (int) Math.round(matrix.getValue(x, y) * 255 / 100);
				int blue = (int) Math.round(255 * (100 - matrix.getValue(x, y)) / 100);
				Color c = new Color(red, 0, blue);
				canvas.setRGB(x, y, c.getRGB());
			}
		}
		this.validate();
		this.repaint();
		this.validate();
	}
	
	/**
	 * Der interne Datenspeicher, welcher von den Solvern verwendet wird. Dabei besitzt die
	 * Datenmatrix intern zwei Matrizen. Die eine wird zum Lesen der letzten Iteration verwendet.
	 * Die zweite wird zum Schreiben der neuen Iteration verwendet.
	 */
	public class DatenMatrix {
		
		// Groesse des Datenspeichers.
		// Metallplatte hat die Groesse size x size.
		private final int	size;
		
		private double[][]	oldMatrix;
		private double[][]	newMatrix;
		
		// Um Performance zu sparen wird nur nach jeder 100sten Iteration neugezeichnet.
		private int			redrawCounter	= 0;
		
		/**
		 * Wenn alle Solver fertig gerechnet haben und jede Temperatur der neuen Iteration gesetzt
		 * ist, kann ueber diese Methode die aktuelle Iteration abgeschlossen werden. Damit werden
		 * alle Temperaturen der neuen Iteration in die alte uebernommen. Alle folgenden
		 * Lese-Operationen finden dann auf den neuen Werten statt.
		 */
		public void nextIteration() {
			oldMatrix = newMatrix;
			newMatrix = new double[size][size];
			if (refresh) {
				redrawCounter++;
				if (redrawCounter > 100) {
					drawDaten();
					redrawCounter = 0;
				}
			}
		}
		
		/**
		 * Erstellt eine neue DatenMatrix der Groesse size
		 * 
		 * @param size
		 *            Groesse der Datenmatrix
		 */
		private DatenMatrix(int size) {
			this.size = size;
			oldMatrix = new double[size][size];
			newMatrix = new double[size][size];
		}
		
		/**
		 * Gibt die Groesse des Datenspeichers zurueck. Die Metallplatte hat die Groesse size x
		 * size.
		 * 
		 * @return
		 */
		public int getSize() {
			return this.size;
		}
		
		/**
		 * Gibt den Wert der letzten Iteration an der Stelle (x, y) zueueck.
		 * 
		 * @param x
		 *            X-Koordinaten
		 * @param y
		 *            Y-Koordinaten
		 * @return Temperatur der Koordinaten (x, y)
		 */
		public double getValue(int x, int y) {
			return oldMatrix[x][y];
		}
		
		/**
		 * Setzt den Wert der naechsten Iteration an der Stelle (x, y).
		 * 
		 * @param value
		 *            neue Temperatur
		 * @param x
		 *            X-Koordinaten
		 * @param y
		 *            Y-Koordinaten
		 */
		public void setValue(double value, int x, int y) {
			newMatrix[x][y] = value;
		}
		
	}
}
