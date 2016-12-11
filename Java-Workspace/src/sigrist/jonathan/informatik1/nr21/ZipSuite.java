package sigrist.jonathan.informatik1.nr21;

import java.util.Random;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * TestKlasse fuer Klasse Zip
 * 
 * @author Carolin Wortmann, Leonhard Segger, Jonathan Sigrist
 *
 */
public class ZipSuite extends TestCase {
	
	/**
	 * Konstruiert einen Test mit gegebenem Namen.
	 * 
	 * @param name
	 *            Name des Tests
	 */
	public ZipSuite(String name) {
		super(name);
	}
	
	/**
	 * Stellt ein Testszenario dieser Klasse zur Verfuegung
	 * 
	 * @return Testszenario von ZipSuite
	 */
	public static Test suite() {
		return new TestSuite(ZipSuite.class);
	}
	
	int[]	array1, array2;
	int[]	loesungMultiplikation, loesungXOR, loesungRechnung;
	
	/**
	 * Bereitet den Test vor. Dabei werden zwei zufaellig lange Arrays mit zufaelligen Zahlen
	 * gefuellt.
	 */
	@org.junit.Before
	public void setUp() {
		Random r = new Random();
		array1 = new int[r.nextInt(100)];
		for (int i = 0; i < array1.length; i++) {
			array1[i] = r.nextInt(1000) - 500;
		}
		array2 = new int[r.nextInt(100)];
		for (int i = 0; i < array2.length; i++) {
			array2[i] = r.nextInt(1000) - 500;
		}
		
		loesungMultiplikation = new int[Math.min(array1.length, array2.length)];
		for (int i = 0; i < loesungMultiplikation.length; i++) {
			loesungMultiplikation[i] = array1[i] * array2[i];
		}
		
		loesungXOR = new int[Math.min(array1.length, array2.length)];
		for (int i = 0; i < loesungXOR.length; i++) {
			loesungXOR[i] = array1[i] ^ array2[i];
		}
		
		loesungRechnung = new int[Math.min(array1.length, array2.length)];
		for (int i = 0; i < loesungRechnung.length; i++) {
			int summe = array1[i] + array2[i];
			int divergenz = array1[i] - array2[i];
			loesungRechnung[i] = (summe * divergenz) % 13;
		}
	}
	
	/**
	 * Die Methode tut nichts, da weder Streams noch Files geschlossen werden muessen.
	 */
	@org.junit.After
	public void tearDown() {
		
	}
	
	/**
	 * Testet die Zip.zip-Methode anhand der Addition zwei Arrays.
	 */
	@org.junit.Test
	public void testRechnung() {
		int[] result = Zip.zip(array1, array2, (a, b) -> ((a + b) * (a - b)) % 13);
		
		checkIdentisch(result, loesungRechnung);
	}
	
	/**
	 * Testet die Zip.zip-Methode anhand von zwei Arrays und dem Modulo-Operator.
	 */
	@org.junit.Test
	public void testXOR() {
		int[] result = Zip.zip(array1, array2, (a, b) -> a ^ b);
		
		checkIdentisch(result, loesungXOR);
	}
	
	/**
	 * Testet die Zip.zip-Methode anhand der Multiplikation zwei Arrays.
	 */
	@org.junit.Test
	public void testMultiplikation() {
		int[] result = Zip.zip(array1, array2, (a, b) -> a * b);
		
		checkIdentisch(result, loesungMultiplikation);
	}
	
	/**
	 * Vergleicht zwei int-Arrays miteinander.
	 * 
	 * @param array1
	 *            erstes Array
	 * @param array2
	 *            zweites Array
	 * @return true, falls beide gleich lang sind und die Elemente beider Arrays gleiche Werte
	 *         haben, sonst false
	 */
	private void checkIdentisch(int[] array1, int[] array2) {
		if (array1.length != array2.length)
			assertFalse(true);
		for (int i = 0; i < array1.length; i++) {
			assertEquals(array1[i], array2[i]);
		}
	}
}
