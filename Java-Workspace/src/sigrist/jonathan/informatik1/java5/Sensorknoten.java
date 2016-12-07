package sigrist.jonathan.informatik1.java5;

public class Sensorknoten {
	
	private String	name;
	private int		anzahlSensoren;
	private double	restEnergie	= 1.0;
	
	public Sensorknoten(String name, int anzahlSensoren) {
		this.name = name;
		this.anzahlSensoren = anzahlSensoren;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAnzahlSensoren() {
		return anzahlSensoren;
	}
	
	public double getRestEnergie() {
		return restEnergie;
	}
	
}
