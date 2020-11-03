package figuren;

import java.util.Arrays;

import enums.Farbe;
import enums.Richtung;

public class Koenig extends LineareFiguren {
	boolean langeRochade = true;
	boolean kurzeRochade = true;
	boolean imSchach = false;

	public Koenig(Farbe farbe) {
		super("König", farbe, 1, Richtung.values());
		setFeld('e', farbe.grundreihe);
	}

	@Override
	public boolean move(char x, int y) {
		if(x=='g' && y==farbe.grundreihe && kurzeRochade) {
			macheRochade(true);
			return true;
		}
		
		if(x=='c' && y==farbe.grundreihe && langeRochade) {
			macheRochade(false);
			return true;
		}
		boolean legalerZug = super.move(x, y);
		return legalerZug;
	}

	private void macheRochade(boolean kurzeRochade) {
		if(kurzeRochade) {
			this.setFeld('g', farbe.grundreihe);
			schachbrett.getFigur('h', farbe.grundreihe).setFeld('f', farbe.grundreihe);
		} else {
			this.setFeld('c', farbe.grundreihe);
			schachbrett.getFigur('a', farbe.grundreihe).setFeld('d', farbe.grundreihe);
		}
	}
	

	/**
	 * @param kurz Dieser Parameter gibt an, ob die kurze oder lange Rochade überprüft wird. 
	 * @return liefert true zurück, wenn die Rochade ausführbar ist
	 */
	private boolean pruefeRochade(boolean kurz) {
		char turmlinie = kurz?'h':'a';
		boolean b1 = schachbrett.freiesFeld(turmlinie, farbe.grundreihe)||schachbrett.getFigur(turmlinie, farbe.grundreihe).hasMoved(); // true, wenn Turm bewegt
		boolean b2 = hasMoved(); // true, wenn König bewegt
		boolean b3lang = schachbrett.freiesFeld('b', farbe.grundreihe) && schachbrett.freiesFeld('c', farbe.grundreihe)&&schachbrett.freiesFeld('d', farbe.grundreihe);
		boolean b3kurz = schachbrett.freiesFeld('f', farbe.grundreihe) && schachbrett.freiesFeld('g', farbe.grundreihe); //true, wenn alle Felder zwischen König und Turm frei
		boolean b3 = kurz?b3kurz:b3lang;
		boolean b4lang = schachbrett.bedrohtesFeld('e', farbe.grundreihe, Farbe.andereFarbe(farbe)) || 
						 schachbrett.bedrohtesFeld('d', farbe.grundreihe, Farbe.andereFarbe(farbe)) ||
						 schachbrett.bedrohtesFeld('f', farbe.grundreihe, Farbe.andereFarbe(farbe));
		boolean b4kurz = schachbrett.bedrohtesFeld('e', farbe.grundreihe, Farbe.andereFarbe(farbe)) || 
		     	 		 schachbrett.bedrohtesFeld('f', farbe.grundreihe, Farbe.andereFarbe(farbe)) ||
		     	 		 schachbrett.bedrohtesFeld('g', farbe.grundreihe, Farbe.andereFarbe(farbe)); 
		boolean b4 = kurz?b4kurz:b4lang;					  
		return !b1 && !b2 && b3 && !b4;
	}

	@Override
	public boolean[][] getZugfelder() {
		boolean[][] zugfelder = super.getZugfelder();
		if (pruefeRochade(true)) {
			zugfelder[farbe.grundreihe - 1][6] = true;
		}
		if (pruefeRochade(false)) {
			zugfelder[farbe.grundreihe - 1][2] = true;
		}
		return zugfelder;
	}

//	private boolean imSchach() {
//		//Liste aller andersfarbigen Figuren
//		List<Figur> andereFarbe = 
//		return true;
//	}

	@Override
	public char toChar() {
		return 'K';
	}

}
