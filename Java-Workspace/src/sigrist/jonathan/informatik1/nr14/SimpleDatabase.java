package sigrist.jonathan.informatik1.nr14;

/*
 * g = new GridBagLayout
 * c = ne wGridBagConstraint
 * c.fill = c.BOTH
 * c.{gridx, gridy, gridwidth, gridheight, weightx, weighty}
 * add(comp
 * g.setConstrains(c)
 */
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasse beinhaltet das geforderte Frame, welches mit der main-Methode
 * ausgefuehrt werden kann.
 * 
 * @author Leonhard Segger, Carolin Wortmann, Jonathan Sigrist
 *
 */
public class SimpleDatabase extends Frame implements ActionListener {

	protected String[]	data			= new String[10];
	protected int		index			= 0;

	protected TextField	tFTextEdit		= new TextField("", 22);
	protected Button	btnWeiter		= new Button("Weiter");
	protected Button	btnZurueck		= new Button("Zurueck");
	protected Button	btnSpeichern	= new Button("Speichern");
	protected Button	btnLaden		= new Button("Laden");
	protected Button	btnBeenden		= new Button("Beenden");
	protected Label		lIndex			= new Label("1", 2);

	/**
	 * Startmethode zum kreieren eines neuen Fensters.
	 * 
	 * @param args
	 *            Konsolen-Argumente
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		SimpleDatabase inst = new SimpleDatabase();
	}

	/**
	 * Konstruktor der Klasse. Erstellt ein neues Fenster in der man ein
	 * String-Array bearbeiten kann.
	 */
	public SimpleDatabase() {
		super();
		setBounds(400, 400, 250, 300);
		setTitle("SimpleDatabase");
		setLayout(new GridBagLayout());
		setBackground(Color.LIGHT_GRAY);

		put(this, btnZurueck, 1, 1, 1);
		put(this, lIndex, 2, 1, 1);
		put(this, btnWeiter, 3, 1, 1);
		put(this, tFTextEdit, 1, 2, 3);
		put(this, btnSpeichern, 1, 3, 1);
		put(this, btnLaden, 2, 3, 1);
		put(this, btnBeenden, 3, 3, 1);

		btnWeiter.addActionListener(this);
		btnZurueck.addActionListener(this);
		btnSpeichern.addActionListener(this);
		btnLaden.addActionListener(this);
		btnBeenden.addActionListener(this);

		pack();
		validate();
		setVisible(true);
	}

	/**
	 * Eine aus der Vorlesung bekannte Methode zum vereinfachen der Anordnung
	 * von den Komponenten
	 * 
	 * @param ctr
	 *            Container in welche die Komponente plaziert werden soll
	 * @param comp
	 *            Die zu plazierende Komponente
	 * @param x
	 *            x-Wert im GridBagLayout
	 * @param y
	 *            y-Wert im GridBagLayout
	 * @param w
	 *            laenge des Feldes im GridBagLayout
	 */
	public static void put(Container ctr, Component comp, int x, int y, int w) {
		GridBagLayout g = (GridBagLayout) ctr.getLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = w;
		c.gridheight = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		ctr.add(comp);
		g.setConstraints(comp, c);
	}

	/**
	 * Methode vergleicht das uebergebene ActionEvent auf seine Ursache. Je
	 * nachdem welcher Knopf gedrueckt wurde, wird eine Operation durchgefuehrt.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnWeiter) {
			index = ++index % data.length;
			lIndex.setText(Integer.toString(index + 1));
		} else if (e.getSource() == btnZurueck) {
			index = (--index + data.length) % data.length;
			lIndex.setText(Integer.toString(index + 1));
		} else if (e.getSource() == btnBeenden) {
			dispose();
		} else if (e.getSource() == btnSpeichern) {
			data[index] = tFTextEdit.getText();
		} else if (e.getSource() == btnLaden) {
			tFTextEdit.setText(data[index]);
		}
	}
}
