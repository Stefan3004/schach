package enums;

public enum Farbe {
	Wei�(1,2), Schwarz(8,7);
	public int grundreihe;
	public int bauernreihe;
	
	private Farbe(int grundreihe, int bauernreihe) {
		this.grundreihe = grundreihe;
		this.bauernreihe = bauernreihe;
	}
	
	public static Farbe getFarbe(boolean weiss) {
		return weiss?Farbe.Wei�:Farbe.Schwarz;	
	}
	
	public static boolean farbeToBoolean(Farbe farbe) {
		return farbe==Farbe.Wei�;
	}
	
	public static Farbe andereFarbe(Farbe farbe) {
		return farbe==farbe.Wei�?Farbe.Schwarz:farbe.Wei�;
	}
	
}
