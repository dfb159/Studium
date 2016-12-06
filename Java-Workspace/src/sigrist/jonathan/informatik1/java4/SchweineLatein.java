package sigrist.jonathan.informatik1.java4;

import javax.swing.JOptionPane;

public class SchweineLatein {

	public static void main(String[] args) {
		System.out.println(nachSchweineLatein(
				JOptionPane.showInputDialog("Text zu Schweine-Latein")));
	}

	public static String nachSchweineLatein(String wort) {
		if (isKonsonant(wort.toLowerCase().charAt(0))) {
			wort = wort.substring(1, wort.length())
					+ wort.substring(0, 1).toLowerCase();
		}
		wort = wort.substring(0, 1).toUpperCase()
				+ wort.substring(1, wort.length()) + "ay";
		return wort;
	}

	public static final char[] listKonsonantLowerCase = { 'b', 'c', 'd', 'f',
			'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'r', 's', 't', 'v', 'w',
			'x', 'y', 'z' };

	public static boolean isKonsonant(char c) {
		for (int i = 0; i < listKonsonantLowerCase.length; i++) {
			if (c == listKonsonantLowerCase[i])
				return true;
		}
		return false;
	}

}
