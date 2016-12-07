package sigrist.jonathan.informatik1.nr20;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DateiHochladenFrame extends MeinFrame implements ActionListener {
	
	/** Set aller Komponenten der Anwendung */
	protected Label		warnung		= new Label("");
	protected Label		info		= new Label("Bitte geben Sie einen Dateinamen ein!");
	protected TextField	dateiName	= new TextField("Wunschzettel.txt");
	protected Button	upload		= new Button("Upload starten!");
	protected Button	zurueck		= new Button("Zurueck");
	
	/**
	 * Erstellt das Frame und setzt die Startwerte der Komponenten
	 */
	public DateiHochladenFrame() {
		super("Datei hochladen");
		setBounds(400, 400, 250, 300);
		setLayout(new GridBagLayout());
		setBackground(Color.white);
		setFont(Font.getFont(Font.SANS_SERIF));
		
		warnung.setForeground(Color.red);
		
		put(warnung, 1, 0, 1);
		put(info, 1, 1, 1);
		put(dateiName, 1, 2, 1);
		put(upload, 1, 3, 1);
		put(zurueck, 1, 4, 1);
		upload.addActionListener(this);
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
		} else if (event.getSource() == upload) {
			
			try {
				dateiHochladen(dateiName.getText());
			} catch (ParseFileException e) {
				warnung.setText(e.getMessage());
			}
		}
		
	}
	
	/**
	 * Laed alle Wuensche und Kinder aus einer Datei in die Anwendung. Falls das File nach der
	 * Benutzung nicht geschlossen werden kann, wird das Programm beendet, da ein fataler Fehler
	 * aufgetreten ist.
	 * 
	 * @param datei
	 *            Name der Datei
	 * @throws ParseFileException
	 *             Fehler beim Parsen der Datei. Wird geworfen, wenn: 1. die Datei nicht geoeffnet
	 *             werden kann, 2. die Datei nicht mehr weiter gelesen werden kann oder 3. Ein
	 *             Eintrag falsch ist(Kind oder Geschenk hat keinen Namen hat.
	 */
	public void dateiHochladen(String datei) throws ParseFileException {
		
		FileReader fr = null;
		try {
			fr = new FileReader(new File(datei));
		} catch (FileNotFoundException e) {
			throw new ParseFileException("Die Datei kann nicht geoeffnet werden!");
		}
		BufferedReader in = new BufferedReader(fr);
		
		String line = "";
		try {
			while ((line = in.readLine()) != null) { // so lange line einlesen,
														// bis EOF erreicht ist
				String[] geschenkRaw = line.split(";");
				
				if (geschenkRaw.length == 3) {
					Kind kind = KinderListe.getKind(geschenkRaw[0]);
					GeschenkTyp typ = null;
					if (geschenkRaw[2].equals("Klein")) {
						typ = GeschenkTyp.Klein;
					} else if (geschenkRaw[2].equals("Mittel")) {
						typ = GeschenkTyp.Mittel;
					} else if (geschenkRaw[2].equals("Gross")) {
						typ = GeschenkTyp.Gross;
					} else {
						
					}
					kind.addGeschenk(new Geschenk(geschenkRaw[1], typ));
				}
			}
		} catch (IOException e1) {
			throw new ParseFileException("Die Datei konnte nicht vollstaendig gelesen werden!");
		} catch (FalscherNameException e1) {
			throw new ParseFileException("Ein Kind hat keinen Namen!");
		} catch (GeschenkOhneTitelException e1) {
			throw new ParseFileException("Ein Geschenk hat keinen Namen!");
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.err.println("Fatal: Datei kann nicht geschlossen werden!");
				System.exit(-1);
			}
		}
	}
	
	/**
	 * Ermoeglicht die Verwaltung einer einzigen Instanz fuer das Frame.
	 */
	public static DateiHochladenFrame instanz;
	
	public static DateiHochladenFrame getInstanz() {
		if (instanz == null) {
			instanz = new DateiHochladenFrame();
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
