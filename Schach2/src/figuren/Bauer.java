package figuren;

import enums.Farbe;

public class Bauer extends AbstrakteFigur {
	public Bauer(char x, Farbe farbe) {
		super("Bauer", farbe);
		setFeld(x, farbe.bauernreihe);
	}

	@Override
	public char toChar() {
		return 'B';
	}
	
	private boolean schlageFigur(char x, int y) {
		return this.farbe != schachbrett.getFarbe(x, y) && schachbrett.getFarbe(x, y)!=null;
	}
	
	@Override
	public boolean[][] getZugfelder() {
		boolean [][] res = new boolean[8][8];
		if (farbe == Farbe.Weiß) {
			if (schachbrett.freiesFeld(this.x, this.y+1)) {									// prüft, ob 1 Schritt möglich ist
				res[y][x-'a'] = true;
				if (schachbrett.freiesFeld(this.x, this.y + 2) && this.y == farbe.bauernreihe) {		// prüft, ob Doppelschritt möglich ist
					res[y + 1][x-'a'] = true;
				}
			}
			if (schlageFigur((char)(x+1), y+1)) {
				res[y][x + 1-'a'] = true;
			}
			if (schlageFigur((char)(x-1), y+1)) {
				res[y][x - 1-'a'] = true;
			} 
		} else {
			if (schachbrett.freiesFeld(this.x, this.y-1)) {									// prüft, ob 1 Schritt möglich ist
				res[y - 2][x-'a'] = true;
				if (schachbrett.freiesFeld(this.x, this.y - 2) && this.y == farbe.bauernreihe) {		// prüft, ob Doppelschritt möglich ist
					res[y - 3][x-'a'] = true;
				}
			}
			if (schlageFigur((char)(x+1), y-1)) {
				res[y - 2][x + 1-'a'] = true;
			}
			if (schlageFigur((char)(x-1), y-1)) {
				res[y - 2][x - 1-'a'] = true;
			}
		}
		return res;
	}
}
