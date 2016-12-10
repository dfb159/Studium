package sigrist.jonathan.informatik1.nr19;

public enum Muenze {
	ZweiEuro(200), EinEuro(100), FuenfzigCent(50), ZwanzigCent(20), ZehnCent(10), FuenfCent(
			5), ZweiCent(2), EinCent(1);
	
	private int value = 0;
	
	private Muenze(int value) {
		this.value = value;
	}
}
