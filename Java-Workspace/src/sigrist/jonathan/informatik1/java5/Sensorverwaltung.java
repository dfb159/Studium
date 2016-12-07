package sigrist.jonathan.informatik1.java5;

import java.util.ArrayList;
import java.util.List;

public class Sensorverwaltung {
	
	List<Sensorknoten> sensoren = new ArrayList<Sensorknoten>();
	
	public Sensorverwaltung() {
		sensoren.add(new Sensorknoten("Vorne", 4));
		sensoren.add(new Sensorknoten("Steckdose", 54));
		sensoren.add(new Sensorknoten("Alexander Neuwirth", 0));
	}
	
	public void printSensordata() {
		for (Sensorknoten k : sensoren) {
			System.out.println(k.getName() + ": " + k.getRestEnergie());
		}
	}
	
}
