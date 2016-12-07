package sigrist.jonathan.informatik1.nr20;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Wuenschedatenbank extends MeinFrame implements ActionListener {
	
	/** Set aller Komponenten der Anwendung */
	protected Button	suchen			= new Button("Suchen");
	protected Button	neu				= new Button("Neuer Eintrag");
	protected Button	dateiHochladen	= new Button("Datei hochladen");
	protected Button	ende			= new Button("Beenden");
	
	/**
	 * Erstellt das Frame und setzt die Startwerte der Komponenten
	 */
	private Wuenschedatenbank() {
		super("Wuenschedatenbank");
		setBounds(400, 400, 250, 300);
		setLayout(new GridBagLayout());
		setBackground(Color.white);
		setFont(Font.getFont(Font.SANS_SERIF));
		put(suchen, 1, 1, 1);
		put(neu, 1, 2, 1);
		put(dateiHochladen, 1, 3, 1);
		put(ende, 1, 4, 1);
		suchen.addActionListener(this);
		neu.addActionListener(this);
		dateiHochladen.addActionListener(this);
		ende.addActionListener(this);
		pack();
		setVisible(true);
	}
	
	/**
	 * Behandelt die Aktionen beim Klick der 5 Buttons
	 * 
	 * @param event uebergebenes ActionEvent
	 */
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == suchen) {
			SuchenFrame.getInstanz().setVisible(true);
			setVisible(false);
		} else if (event.getSource() == neu) {
			NeuerEintragFrame.getInstanz().setVisible(true);
			setVisible(false);
		} else if (event.getSource() == dateiHochladen) {
			DateiHochladenFrame.getInstanz().setVisible(true);
			setVisible(false);
		} else if (event.getSource() == ende) {
			SuchenFrame.disposeInstanz();
			NeuerEintragFrame.disposeInstanz();
			DateiHochladenFrame.disposeInstanz();
			dispose();
		}
	}
	
	/**
	 * Ermöglicht die Verwaltung einer einzigen Instanz fuer das Frame.
	 */
	public static Wuenschedatenbank instanz;
	
	public static Wuenschedatenbank getInstanz() {
		if (instanz == null) {
			instanz = new Wuenschedatenbank();
		}
		return instanz;
	}
	
	public static void main(String[] args)
			throws FalscherNameException, GeschenkOhneTitelException {
		Kind santa = KinderListe.getKind("Santa");
		santa.addGeschenk(new Geschenk("Einen Fileupload!", GeschenkTyp.Gross));
		Wuenschedatenbank.getInstanz();
	}
}
