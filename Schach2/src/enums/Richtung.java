package enums;

public enum Richtung {
	VOR(0,1), ZURUECK(0,-1), LINKS(-1,0), RECHTS(1,0), LINKSOBEN(-1,1), RECHTSOBEN(1,1), LINKSUNTEN(-1,-1), RECHTSUNTEN(1,-1);
	public int x,y;
	
	Richtung(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
