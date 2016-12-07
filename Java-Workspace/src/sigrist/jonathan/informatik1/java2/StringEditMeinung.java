package sigrist.jonathan.informatik1.java2;

public class StringEditMeinung {
	
	public static void main(String[] args) {
		aendereMeinung();
	}
	
	public static void aendereMeinung() {
		String str = "ich hasse montage";
		System.out.println(str);
		int index = str.indexOf("ich hasse");
		String first = str.substring(0, index);
		String last = str.substring(index + 9, str.length());
		str = first + "ich liebe" + last;
		System.out.println(str);
	}
	
}
