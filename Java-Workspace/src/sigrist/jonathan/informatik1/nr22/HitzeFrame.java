package sigrist.jonathan.informatik1.nr22;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ein Frame welches aus zwei Komponenten besteht. Das CanvasPanel beinhaltet die Visualisierung der
 * erhitzten Metallplatte. Dabei enthaelt es unter anderem Textfelder fuer die Temperaturen der vier
 * Seiten. Das ControllPanel stellt Funktionen zur Verfuegung, mit welchen der Zustand der
 * Metallplatte berechnet werden kann.
 */
public class HitzeFrame extends JFrame implements ActionListener, SolverObserver {
	
	private JTextField	temperaturOben		= new JTextField("0", 5);
	private JTextField	temperaturLinks		= new JTextField("100", 5);
	private JTextField	temperaturRechts	= new JTextField("100", 5);
	private JTextField	temperaturUnten		= new JTextField("0", 5);
	private JButton		solveSeq			= new JButton("Solve Sequential");
	private JButton		solvePar			= new JButton("Solve Parallel");
	private JLabel		time				= new JLabel("Zeit: -");
	private JLabel		sizeLabel			= new JLabel("Groesse:");
	private JTextField	sizeInput			= new JTextField("200", 8);
	private JLabel		refreshLabel		= new JLabel("Neuzeichnen:");
	private JCheckBox	refreshCanvas		= new JCheckBox();
	private JLabel		epsilonLabel		= new JLabel("Epsilon:");
	private JTextField	epsilonInput		= new JTextField(0.001 + "", 8);
	private HitzePanel	canvas;
	private JPanel		canvasPanel;
	
	/**
	 * Initialisiert das Frame.
	 */
	public HitzeFrame() {
		
		this.setSize(500, 400);
		this.setLayout(new GridBagLayout());
		
		JPanel controlPanel = drawControlPanel();
		canvasPanel = drawCanvas();
		
		GridBagConstraints gbc;
		
		gbc = new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0);
		this.add(canvasPanel, gbc);
		gbc = new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0);
		this.add(controlPanel, gbc);
		
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Initialisiert das ControlPanel, mit welchen der Zustand der Metallplatte berechnet werden
	 * kann. Enthalten sind ... zwei Buttons zum Starten des sequentiellen und des parallelen
	 * Solvers. ein Label, das die Zeit ausgibt, die der letzte Solver benoetigt hat. ein Textfeld
	 * fuer die Groesse der Metallplatte. eine Checkbox, welche bestimmt, ob die Metallplatte
	 * waehrend einer Berechnung aktualisiert werden soll. ein Textfeld fuer das Epsilon, das in der
	 * Berechnung verwendet wird.
	 * 
	 * @return ControlPanel
	 */
	public JPanel drawControlPanel() {
		// Controll Panel
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridBagLayout());
		controlPanel.setBorder(new LineBorder(Color.BLACK));
		GridBagConstraints gbc;
		
		gbc = new GridBagConstraints(1, 1, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0);
		controlPanel.add(solveSeq, gbc);
		solveSeq.addActionListener(this);
		
		gbc = new GridBagConstraints(1, 2, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0);
		controlPanel.add(solvePar, gbc);
		solvePar.addActionListener(this);
		
		gbc = new GridBagConstraints(1, 3, 4, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0);
		time.setSize(new Dimension(40, 200));
		controlPanel.add(time, gbc);
		gbc = new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0);
		sizeLabel.setSize(new Dimension(40, 200));
		controlPanel.add(sizeLabel, gbc);
		gbc = new GridBagConstraints(3, 4, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0);
		sizeInput.setMinimumSize(new Dimension(80, 20));
		controlPanel.add(sizeInput, gbc);
		gbc = new GridBagConstraints(1, 5, 3, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0);
		refreshLabel.setSize(new Dimension(40, 200));
		controlPanel.add(refreshLabel, gbc);
		gbc = new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0);
		controlPanel.add(refreshCanvas, gbc);
		gbc = new GridBagConstraints(1, 6, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0);
		epsilonLabel.setSize(new Dimension(40, 200));
		controlPanel.add(epsilonLabel, gbc);
		gbc = new GridBagConstraints(3, 6, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0);
		epsilonInput.setMinimumSize(new Dimension(80, 20));
		controlPanel.add(epsilonInput, gbc);
		return controlPanel;
	}
	
	/**
	 * Initialisiert das DrawPanel, welches die Visualisierung der Metallplatte uebernimmt. In der
	 * Mitte besitzt es eine Instanz des HitzePanel. An den vier Seiten von diesem sind vier
	 * Textfelder, welche eine Temperatur von 0-100 entgegen nehmen.
	 * 
	 * @return DrawPanel
	 */
	public JPanel drawCanvas() {
		JPanel canvasPanel = new JPanel();
		
		canvas = new HitzePanel(200);
		
		canvasPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc;
		gbc = new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
		canvasPanel.add(canvas, gbc);
		canvas.setVisible(true);
		canvas.repaint();
		gbc = new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0);
		temperaturLinks.setMinimumSize(new Dimension(40, 20));
		canvasPanel.add(temperaturLinks, gbc);
		gbc = new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0);
		temperaturOben.setMinimumSize(new Dimension(40, 20));
		canvasPanel.add(temperaturOben, gbc);
		gbc = new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0);
		temperaturRechts.setMinimumSize(new Dimension(40, 20));
		canvasPanel.add(temperaturRechts, gbc);
		gbc = new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0);
		temperaturUnten.setMinimumSize(new Dimension(40, 20));
		canvasPanel.add(temperaturUnten, gbc);
		return canvasPanel;
	}
	
	/**
	 * Wird verwenden zur Berechnung der Laufzeit eiens Solvers.
	 */
	private long startZeit;
	
	/**
	 * Startet einen Solver, wenn ein Button gedrueckt wurde.
	 * 
	 * @param actionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// Die Groesse muss ganzzahlig sein!
		int size;
		try {
			size = Integer.parseInt(sizeInput.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie eine ganzzahlige Groesse an!");
			return;
		}
		// Die Temperaturen muessen ganzzahlig sein!
		int temp_oben, temp_unten, temp_links, temp_rechts;
		try {
			temp_oben = Integer.parseInt(temperaturOben.getText());
			temp_unten = Integer.parseInt(temperaturUnten.getText());
			temp_links = Integer.parseInt(temperaturLinks.getText());
			temp_rechts = Integer.parseInt(temperaturRechts.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie nur ganzzahlige Temperaturen an!");
			return;
		}
		// Die Temperaturen muessen im Bereich von 0-100 liegen.
		if (temp_links < 0 || temp_links > 100 || temp_oben < 0 || temp_oben > 100
				|| temp_rechts < 0 || temp_rechts > 100 || temp_unten < 0 || temp_unten > 100) {
			JOptionPane.showMessageDialog(null,
					"Bitte geben Sie nur Temperaturen zwischen 0 und 100 an!");
			return;
		}
		// Die Epsilon muss ein parsbarer Double sein!
		double epsilon;
		try {
			epsilon = Double.parseDouble(epsilonInput.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
					"Bitte geben Sie das Epsilon mit dem korrekten Trennzeichen an!");
			return;
		}
		// Sperre die Oberfläche vor weiteren Eingaben.
		enableFields(false);
		// Setze das Canvas zurueck.
		canvas.reset(size, refreshCanvas.isSelected());
		refreshCanvas();
		
		// Initialisiere Solver je nach gedruecktem Button.
		Solver solver;
		if (actionEvent.getSource() == solvePar) {
			solver = new ParallelSolver(temp_oben, temp_unten, temp_links, temp_rechts, epsilon,
					canvas.getMatrix(), this);
			
		} else if (actionEvent.getSource() == solveSeq) {
			solver = new SequentialSolver(temp_oben, temp_unten, temp_links, temp_rechts, epsilon,
					canvas.getMatrix(), this);
		} else {
			enableFields(true);
			return;
		}
		// Starte den Solver.
		startZeit = System.currentTimeMillis();
		solver.solve();
	}
	
	/**
	 * Sperrt oder entsperrt alle GUI Elemente
	 * 
	 * @param enable
	 *            Gibt an, ob die Elemente gesperrt oder entsperrt werden sollen.
	 */
	public void enableFields(boolean enable) {
		temperaturLinks.setEnabled(enable);
		temperaturOben.setEnabled(enable);
		temperaturRechts.setEnabled(enable);
		temperaturUnten.setEnabled(enable);
		solveSeq.setEnabled(enable);
		solvePar.setEnabled(enable);
		sizeInput.setEnabled(enable);
		refreshCanvas.setEnabled(enable);
		epsilonInput.setEnabled(enable);
	}
	
	/**
	 * Zeichnet das Canvas neu.
	 */
	private void refreshCanvas() {
		canvas.drawDaten();
		this.canvasPanel.revalidate();
		this.canvasPanel.repaint();
		this.canvasPanel.validate();
		this.getContentPane().validate();
		this.getContentPane().repaint();
		this.getContentPane().validate();
	}
	
	/**
	 * Wird aufgerufen, nachdem der Solver fertig ist. Aktualisiert das Zeit Label und das Canvas.
	 * Entsperrt ausserdem die GUI Elemente.
	 */
	public void refresh() {
		long gesamtDauer = System.currentTimeMillis() - startZeit;
		time.setText("Zeit: " + gesamtDauer + " ms");
		enableFields(true);
		refreshCanvas();
	}
	
	/**
	 * Just used for testcases!!
	 */
	public JTextField methodForTesting() {
		return sizeInput;
	}
	
}
