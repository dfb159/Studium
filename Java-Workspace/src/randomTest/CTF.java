package randomTest;

public class CTF {
	  public static void main(String[] paramArrayOfString)
	  {
	    int i = 0;
	    for (int j = 0; j < 1337; j++) {
	      i += j;
	    }

	    String str = "EKO{" + i + "}";
	    System.out.println("flag: " + str);
	  }
}
