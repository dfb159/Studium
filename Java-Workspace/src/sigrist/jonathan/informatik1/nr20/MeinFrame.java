package sigrist.jonathan.informatik1.nr20;

import java.awt.*;

/**
 * Stellt Basisfunktionalitaeten für alle Frames zur Verfuegung.
 */
public abstract class MeinFrame extends Frame {
	
	public MeinFrame(String title) {
		super(title);
	}
	
	/**
	 * Ermoeglicht das Setzen einer Komponente auf dem Frame
	 * 
	 * @param comp
	 *            Komponente die gesetzt werden soll.
	 * @param x
	 *            x-Koordinaten auf dem GridBagLayout.
	 * @param y
	 *            y-Koordinaten auf dem GridBagLayout.
	 * @param w
	 *            Breite der komponente auf dem GridBagLayout.
	 */
	protected void put(Component comp, int x, int y, int w) {
		GridBagLayout g = (GridBagLayout) this.getLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = w;
		c.gridheight = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		this.add(comp);
		g.setConstraints(comp, c);
	}
	
}
