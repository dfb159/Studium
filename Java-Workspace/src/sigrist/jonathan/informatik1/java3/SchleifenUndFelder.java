package sigrist.jonathan.informatik1.java3;

public class SchleifenUndFelder {

	public static void main(String[] args) {
		SchleifenUndFelder s = new SchleifenUndFelder();
		System.out.println(s.hundertzwanzig());
		s.pengPuff();
	}

	public int hundertzwanzig() {
		int i = 1;
		int summe = 0;
		while (i <= 15) {
			summe += i++;
			if (summe % 5 != 0)
				System.out.print(summe + " ");
		}
		return summe;
	}

	public void pengPuff() {
		for (int i = 1; i <= 100; i++) {
			boolean fuenf = i % 5 == 0 || Integer.toString(i).contains("5");
			boolean sieben = i % 7 == 0 || Integer.toString(i).contains("7");
			if (fuenf || sieben) {
				if (fuenf) {
					System.out.print("peng");
				}
				if (sieben) {
					System.out.print("puff");
				}
			} else {
				System.out.print(i);
			}
			if(i < 100) System.out.print(", ");
		}
	}

}
