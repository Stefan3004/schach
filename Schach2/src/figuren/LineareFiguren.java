package figuren;

import java.util.Arrays;
import java.util.List;
import enums.Farbe;
import enums.Richtung;

public abstract class LineareFiguren extends AbstrakteFigur {
	int reichweite;
	List<Richtung> richtung;
	
	public LineareFiguren(String name, Farbe farbe, int reichweite, Richtung ... richtungen ) {
		super(name, farbe);
		richtung = Arrays.asList(richtungen);
		this.reichweite = reichweite;
	}
	
	@Override
	public boolean[][] getZugfelder() {
		boolean [][] res = new boolean[8][8];
		for (Richtung r : richtung) {
			for (int i = 1; i <= reichweite; i++) {
				try {
					pruefeFeld((char) (this.x+r.x*i), this.y+r.y*i, res);
				} catch (Exception e) {
					break;
				}
			}
		}
		return res;
	}
	
	
	
	

}
