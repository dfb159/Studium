package randomTest;

public class Wertebereiche {
	
	public static void main(String[] args) {
		
		final int i = 1_000_000_000;
		final long l = 1_000_000_000L;
		
		try {
			System.out.println("a) 7/2: " + (7 / 2));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			System.out.println("b) 4/2.0: " + (4 / 2.0));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			System.out.println("c) 0/0.0: " + (0 / 0.0));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			System.out.println("d) 42/0: " + (42 / 0));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			System.out.println("e) 42/0.0: " + (42 / 0.0));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			System.out.println("f) i*i " + (i * i));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			System.out.println("g) i*l: " + (i * l));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			System.out.println("h) l*l*l " + (l * l * l));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
