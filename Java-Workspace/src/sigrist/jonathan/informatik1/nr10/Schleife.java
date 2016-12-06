package sigrist.jonathan.informatik1.nr10;

/**
 * Die Klasse stellt die Loesungen fuer Nr. 10 bereit.
 * 
 * @author jonathan
 *
 */
public class Schleife {
	
	/**
	 * Vergleicht das bis jetzt höchste Element im Array mit anderen. Wenn dieses größer ist, wird dessen Index in maxIndex gespeichert.
	 * Am Anfang wird das erste Element als größtes gesetzt, damit alle anderen noch verglichen werden können.
	 * Bei (array.length == 0) wird 0 zurueckgegeben.
	 * 
	 * @param array Das Array, fuer welches der maximale Wert berechnet werden soll.
	 * 
	 * @return Den maximalen Wert vom gesamten Array.
	 */
	public int max(int[] array) {
		if(array.length == 0) return 0;
		
		int maxIndex = 0;
		
		for(int i = 1; i < array.length; i++) {
			if(array[i] > array[maxIndex]) maxIndex = i;
		}
		
		return array[maxIndex];
	}
	
	/**
	 * Gibt das Skalarprodukt der gemeinsamen Elemente zurueck. Falls ein Array weniger Elemente als das andere haben sollte, wird diese Anzahl genommen.
	 * 
	 * @param a1 erstes Array
	 * @param a2 zweites Array
	 * @return Skalarprodukt aus den beiden uebergebenen Arrays.
	 */
	public int skalarprodukt(int[] a1, int[] a2) {
		int iMax = (a1.length < a2.length ? a1.length : a2.length);
		int solution = 0;
		
		for(int i = 0; i < iMax; i++) {
			solution += a1[i] * a2[i];
		}
		
		return solution;
	}
	
	/**
	 * Vergleicht ein Element mt den darauf Folgenden. Dabei sollen vorherige oder gleiche nicht verglichen werden.
	 * Falls ein Treffer gefunden wurde, bricht die Schleife ab und gibt true zurueck. Falls die Schleife zum Ende kommt, gibt sie false zurueck.
	 * 
	 * @param array Das Array, fuer welches geprueft werden soll, ob es doppelte Werte enthaelt.
	 * @return true, falls das Array doppelte Werte beinhaltet, false, falls nicht.
	 */
	public boolean hasDoppelte(int[] array) {
		for(int i = 0; i < array.length-1; i++) {
			for(int j = i+1; j < array.length; j++) {
				if(array[i] == array[j]) return true;
			}
		}
		return false;
	}
}
