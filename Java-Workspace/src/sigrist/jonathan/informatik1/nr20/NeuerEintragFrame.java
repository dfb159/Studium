package sigrist.jonathan.informatik1.nr20;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NeuerEintragFrame extends MeinFrame implements ActionListener {
	
	/** Set aller Komponenten der Anwendung */
	protected Label		warnung					= new Label("");
	protected Label		infoKind				= new Label("Bitte geben Sie einen Namen ein!");
	protected TextField	kindEingabe				= new TextField("");
	protected Label		infoGeschenk			= new Label("Bitte geben Sie ein Geschenk ein!");
	protected TextField	geschenkEingabe			= new TextField("");
	protected Button	eintragen				= new Button("Eintragen");
	protected Choice	geschenkTypenAuswahl	= new Choice();
	protected Button	zurueck					= new Button("Zurueck");
	
	/**
	 * Erstellt das Frame und setzt die Startwerte der Komponenten
	 */
	public NeuerEintragFrame() {
		super("Eintrag hinzufuegen");
		setBounds(400, 400, 250, 300);
		setLayout(new GridBagLayout());
		setBackground(Color.white);
		setFont(Font.getFont(Font.SANS_SERIF));
		
		warnung.setForeground(Color.red);
		
		for (GeschenkTyp typ : GeschenkTyp.values()) {
			geschenkTypenAuswahl.addItem(typ.toString());
		}
		
		put(warnung, 1, 0, 1);
		put(infoKind, 1, 1, 1);
		put(kindEingabe, 1, 2, 1);
		put(infoGeschenk, 1, 3, 1);
		put(geschenkEingabe, 1, 4, 1);
		put(geschenkTypenAuswahl, 1, 5, 1);
		put(eintragen, 1, 6, 1);
		put(zurueck, 1, 7, 1);
		eintragen.addActionListener(this);
		zurueck.addActionListener(this);
		pack();
		setVisible(true);
	}
	
	/**
	 * Behandelt die Aktionen beim Klick der verschiedenen Buttons
	 * 
	 * @param event uebergebenes ActionEvent
	 */
	public void actionPerformed(ActionEvent event) {
		// Setzt die Warnungsmeldung zurueck!
		warnung.setText("");
		if (event.getSource() == zurueck) {
			Wuenschedatenbank.getInstanz().setVisible(true);
			setVisible(false);
		} else if (event.getSource() == eintragen) {
			try {
				Kind kind = KinderListe.getKind(kindEingabe.getText());
				GeschenkTyp typ = getGeschenkTyp();
				kind.addGeschenk(new Geschenk(geschenkEingabe.getText(), typ));
				geschenkEingabe.setText("");
			} catch (FalscherNameException | GeschenkOhneTitelException e) {
				warnung.setText(e.getMessage());
			}
		}
		
	}
	
	/**
	 * Gibt den ausgewaehlten GeschenkTyp zurueck.
	 * 
	 * @return GeschenkTyp
	 */
	private GeschenkTyp getGeschenkTyp() {
		String typAlsString = geschenkTypenAuswahl.getSelectedItem();
		for (GeschenkTyp typ : GeschenkTyp.values()) {
			if (typ.toString().equals(typAlsString)) {
				return typ;
			}
		}
		throw new IllegalStateException("Diese Stelle sollte nie erreicht werden!");
	}
	
	/**
	 * Ermöglicht die Verwaltung einer einzigen Instanz fuer das Frame.
	 */
	public static NeuerEintragFrame instanz;
	
	public static NeuerEintragFrame getInstanz() {
		if (instanz == null) {
			instanz = new NeuerEintragFrame();
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
