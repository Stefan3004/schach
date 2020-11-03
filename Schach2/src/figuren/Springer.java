package figuren;

import enums.Farbe;
import exceptions.SchlageFigurException;
import exceptions.UngueltigerZugException;

public class Springer extends AbstrakteFigur {

	public Springer(boolean linkerSpringer, Farbe farbe) {
		super("Springer", farbe);
		char x = linkerSpringer? 'b':'g';
		setFeld(x, farbe.grundreihe);
	}

	@Override
	public char toChar() {
		return 'S';
	}

	@Override
	public boolean[][] getZugfelder() {
		boolean [][] res = new boolean[8][8];
			pruefeFeld((char)(this.x+2), this.y+1, res);
			pruefeFeld((char)(this.x+2), this.y-1, res);
			pruefeFeld((char)(this.x-2), this.y+1, res);
			pruefeFeld((char)(this.x-2), this.y-1, res);
			pruefeFeld((char)(this.x+1), this.y+2, res);
			pruefeFeld((char)(this.x+1), this.y-2, res);
			pruefeFeld((char)(this.x-1), this.y+2, res);
			pruefeFeld((char)(this.x-1), this.y-2, res);
		return res;
	}
	
	@Override
	protected void pruefeFeld(char x, int y, boolean[][] feld) {
		if(schachbrett.freiesFeld1(x, y)==null) {
			return;
		}
		if(schachbrett.freiesFeld(x, y)|| this.farbe!=schachbrett.getFarbe(x, y)) {
			feld[y-1][x-'a'] = true;
		}
	}


}
