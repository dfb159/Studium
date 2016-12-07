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
	int[]	loesungAddition, loesungMultiplikation, loesungModulo;
	
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
		
		loesungAddition = new int[Math.min(array1.length, array2.length)];
		for (int i = 0; i < loesungAddition.length; i++) {
			loesungAddition[i] = array1[i] + array2[i];
		}
		
		loesungMultiplikation = new int[Math.min(array1.length, array2.length)];
		for (int i = 0; i < loesungMultiplikation.length; i++) {
			loesungMultiplikation[i] = array1[i] + array2[i];
		}
		
		loesungModulo = new int[Math.min(array1.length, array2.length)];
		for (int i = 0; i < loesungModulo.length; i++) {
			loesungModulo[i] = array1[i] + array2[i];
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
	public void testAddition() {
		int[] result = Zip.zip(array1, array2, (a, b) -> a + b);
		
		assertEquals(isIdentisch(result, loesungAddition), true);
	}
	
	/**
	 * Testet die Zip.zip-Methode anhand von zwei Arrays und dem Modulo-Operator.
	 */
	@org.junit.Test
	public void testModulo() {
		int[] result = Zip.zip(array1, array2, (a, b) -> a % b);
		
		assertEquals(isIdentisch(result, loesungModulo), true);
	}
	
	/**
	 * Testet die Zip.zip-Methode anhand der Multiplikation zwei Arrays.
	 */
	@org.junit.Test
	public void testMultiplikation() {
		int[] result = Zip.zip(array1, array2, (a, b) -> a * b);
		
		assertEquals(isIdentisch(result, loesungMultiplikation), true);
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
	private boolean isIdentisch(int[] array1, int[] array2) {
		if (array1.length != array2.length)
			return false;
		for (int i = 0; i < array1.length; i++) {
			if (array1[i] != array2[i])
				return false;
		}
		return true;
	}
}
