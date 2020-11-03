package figuren;

import enums.Farbe;
import exceptions.SchlageFigurException;
import exceptions.UngueltigerZugException;
import interfaces.Figur;
import spiellogik.Schachbrett;

public abstract class AbstrakteFigur implements Figur {
	private String name;
	boolean hasMoved = false;
	protected char x;
	protected int y;
	protected Farbe farbe;
//	protected final int DEFAULT_FELD = -1;
	public static Schachbrett schachbrett = new Schachbrett();

	/**
	 * Konstruktor zur Erzeugung einer Figur
	 * 
	 * @param name  Name der Figur
	 * @param farbe Farbe der Figur
	 */
	public AbstrakteFigur(String name, Farbe farbe) {
//		x = DEFAULT_FELD;
//		y = DEFAULT_FELD;
		this.name = name;
		this.farbe = farbe;
	}

	/**
	 * @return Farbe der Figur
	 */
	public Farbe getFarbe() {
		return farbe;
	}
	
	@Override
	public boolean hasMoved() {
		return hasMoved;
	}
	
	@Override
	public void setFeld(char x, int y) {
		schachbrett.removeFigur(this.x, this.y);
		this.x = x;
		this.y = y;
		if (x >= 'a' && x <= 'h' && y >= 1 && y <= 8) {
			schachbrett.addFigur(this, x, y);
		}
	}

	/**
	 * @return Reihe zwischen 1 und 8
	 */
	@Override
	public int getReihe() {
		return y;
	}

	/**
	 * @return Spalte zwischen A und H
	 */
	@Override
	public char getSpalte() {
		return x;
	}

	public boolean erlaubtesZugfeld(char x, int y) {
		return getZugfelder()[y-1][x-'a'];
	}
	
	
	
	public boolean move(char x, int y) {
		if (erlaubtesZugfeld(x, y)) {
			setFeld(x, y);
			hasMoved = true;
			return true;
		}
		return false;
	}

//	private void pruefeSchach() {
//		Koenig koenig = (Koenig) schachbrett.figurenAufBrett().stream()
//				.filter(figur -> figur instanceof Koenig && figur.getFarbe() != this.farbe).findFirst().get();
//		if(this.getZugfelder()[koenig.y-1][koenig.x-'a']) {
//			koenig.imSchach = true;
//			System.out.println("Schach");
//		}else {
//			koenig.imSchach = false;
//		}
//	}

	public String printZugfelder() {
		boolean[][] res = getZugfelder();
		StringBuilder builder = new StringBuilder();
		for (int i = res.length - 1; i >= 0; i--) {
			builder.append(i + 1);
			for (int j = 0; j < res.length; j++) {
				builder.append("[");
				if (res[i][j]) {
					builder.append("x");
				} else if (i == y - 1 && j == x - 'a') {
					builder.append(toChar());
				} else {
					builder.append(" ");
				}
				builder.append("]");
			}
			builder.append("\n");
		}
		for (char i = 'a'; i <= 'h'; i++) {
			builder.append("  " + i);
		}
		builder.append("\n  mögliche Zugfelder vom " + name + "\n");
		return builder.toString();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String figurBeschreibung() {
		return name + " " + farbe + " " + getReihe() + " " + getSpalte();
	}

	@Override
	public String toString() {
		return farbe == Farbe.Weiß ? String.valueOf(toChar()) : String.valueOf(toChar()).toLowerCase();
	}

	protected void pruefeFeld(char x, int y, boolean [][] feld) throws UngueltigerZugException, SchlageFigurException {
				if(schachbrett.freiesFeld1(x, y)) {
					feld[y-1][x-'a'] = true;
				}else if(schachbrett.getFarbe(x, y)!= this.farbe) {
					feld[y-1][x-'a'] = true;
					throw new SchlageFigurException();
				}else {
					throw new UngueltigerZugException();
				}
			}

}
