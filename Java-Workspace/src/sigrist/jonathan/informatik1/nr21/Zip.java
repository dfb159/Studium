package sigrist.jonathan.informatik1.nr21;

/**
 * Die Klasse beinhaltet eine Methode, um zwei int-Arrays mit beliebiger lambda-Funktion zu
 * verknüpfen.
 * 
 * @author Carolin Wortmann, Leonhard Segger, Jonathan Sigrist
 *
 */
public class Zip {
	
	/**
	 * Dieses funktionale Interface dient als Schnittstelle zwischen lambda-Funktion und
	 * zip-Methode.
	 * 
	 * @author Carolin Wortmann, Leonhard Segger, Jonathan Sigrist
	 *
	 */
	@FunctionalInterface
	public interface MergeFunc {
		int merge(int a, int b);
	}
	
	/**
	 * Verknuepft zwei beliebig lange int-Arrays mit beliebig angegebener Funktion und gibt das
	 * Ergebnis zurueck.
	 * 
	 * @param list1
	 *            erstes int-Array
	 * @param list2
	 *            zweites int-Array
	 * @param mergeFunc
	 *            Funktion zum verknuepfen von beiden Arrays
	 * @return Das Ergebnis der Funktion als Array mit Laenge des kleineren der beiden Arrays.
	 */
	public static int[] zip(int[] list1, int[] list2, MergeFunc mergeFunc) {
		int[] result = new int[Math.min(list1.length, list2.length)];
		for (int i = 0; i < result.length; i++) {
			result[i] = mergeFunc.merge(list1[i], list2[i]);
		}
		return result;
	}
}
