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
	 * @param event
	 *            uebergebenes ActionEvent
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
			} catch (IOException e) {
				warnung.setText("Die Datei konnte nicht gelesen werden!");
			} catch (FalscherNameException e) {
				warnung.setText("Ein Kind hat keinen Namen!");
			} catch (GeschenkOhneTitelException e) {
				warnung.setText("Ein Geschenk hat keinen Namen!");
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
	 * @throws GeschenkOhneTitelException
	 *             Wird geworfen, falls ein Geschenkeintrag keinen Namen enthaelt.
	 * @throws FalscherNameException
	 *             Wird geworfen, falls ein Kindeintrag keinen Namen enthaelt.
	 * @throws IOException
	 *             Wird geworfen, falls die Datei nicht gelesen werden kann.
	 * @throws ParseFileException
	 *             Wird geworfen, falls kein gueltiger Geschenktyp angegeben wurde.
	 */
	public void dateiHochladen(String datei) throws IOException, FalscherNameException,
			GeschenkOhneTitelException, ParseFileException {
		
		FileReader fr = new FileReader(new File(datei));
		@SuppressWarnings("resource") BufferedReader in = new BufferedReader(fr);
		// Wird nicht geschlossen, wenn beim lesen ein Fehler auftritt
		
		String line = "";
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
					throw new ParseFileException("Kein gueltiger Geschenktyp");
				}
				kind.addGeschenk(new Geschenk(geschenkRaw[1], typ));
			}
		}
		in.close();
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
