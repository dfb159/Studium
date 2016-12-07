package sigrist.jonathan.informatik1.nr20;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SuchenFrame extends MeinFrame implements ActionListener {
	
	/** Set aller Komponenten der Anwendung */
	protected Label		warnung		= new Label("");
	protected Label		info		= new Label("Bitte geben Sie einen Namen ein!");
	protected Button	suchen		= new Button("Suchen");
	protected TextField	kindEingabe	= new TextField("");
	protected TextArea	wuensche	= new TextArea("", 8, 40, TextArea.SCROLLBARS_NONE);
	protected Button	zurueck		= new Button("Zurueck");
	
	/**
	 * Erstellt das Frame und setzt die Startwerte der Komponenten
	 */
	public SuchenFrame() {
		super("Eintrag Suchen");
		setBounds(400, 400, 250, 300);
		setLayout(new GridBagLayout());
		setBackground(Color.white);
		setFont(Font.getFont(Font.SANS_SERIF));
		
		warnung.setForeground(Color.red);
		put(warnung, 1, 0, 1);
		put(info, 1, 1, 1);
		put(kindEingabe, 1, 2, 1);
		put(suchen, 1, 3, 1);
		put(wuensche, 1, 4, 1);
		put(zurueck, 1, 5, 1);
		suchen.addActionListener(this);
		zurueck.addActionListener(this);
		pack();
		setVisible(true);
	}
	
	/**
	 * Behandelt die Aktionen beim Klick der 5 Buttons
	 * 
	 * @param event uebergebenes ActionEvent
	 */
	public void actionPerformed(ActionEvent event) {
		// Setzt die Warnungsmeldung zurueck!
		warnung.setText("");
		if (event.getSource() == zurueck) {
			Wuenschedatenbank.getInstanz().setVisible(true);
			setVisible(false);
		} else if (event.getSource() == suchen) {
			try {
				// Setzt die Suchausgabe zurueck!
				wuensche.setText("");
				// Versucht das neue Suchergebnis zu setzen!
				sucheAktualisieren(kindEingabe.getText());
			} catch (FalscherNameException e) {
				warnung.setText(e.getMessage());
			} catch (KindNotFoundException e) {
				warnung.setText(e.getMessage());
			}
		}
	}
	
	/**
	 * Liste alle Wuensche des Kindes in der Textarea
	 * 
	 * @param nameDesKindes
	 *            Name des Kindes fuer das die Wuensche gesucht werden.
	 * @throws FalscherNameException
	 *             falls der Name leer ist.
	 * @throws KindNotFoundException
	 *             falls kein Kind mit dem Namen gefunden wurde.
	 */
	private void sucheAktualisieren(String nameDesKindes)
			throws FalscherNameException, KindNotFoundException {
		Kind kind = KinderListe.sucheKind(nameDesKindes);
		List<Geschenk> geschenke = kind.getGeschenke();
		String ausgabe = kind.getName() + " wuenscht sich:\n";
		if (geschenke.size() == 0) {
			ausgabe += "Nichts";
		}
		for (Geschenk geschenk : geschenke) {
			ausgabe += geschenk + "\n";
		}
		wuensche.setText(ausgabe);
	}
	
	/**
	 * Ermöglicht die Verwaltung einer einzigen Instanz fuer das Frame.
	 */
	public static SuchenFrame instanz;
	
	public static SuchenFrame getInstanz() {
		if (instanz == null) {
			instanz = new SuchenFrame();
		}
		return instanz;
	}
	
	/**
	 * Schliesst die eine Instanz des Frames.
	 */
	public static void disposeInstanz() {
		if (instanz != null) {
			instanz.dispose();
		}
	}
	
}
