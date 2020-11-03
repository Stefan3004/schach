package figuren;

import java.util.Arrays;

import enums.Farbe;
import enums.Richtung;
import interfaces.Figur;

public class Turm extends LineareFiguren  {
	boolean hasMoved = false;
	public Turm(boolean linkerTurm, Farbe farbe) {
		super("Turm", farbe, 7, Richtung.VOR, Richtung.ZURUECK, Richtung.LINKS, Richtung.RECHTS);
		char x = linkerTurm? 'a':'h';
		setFeld(x, farbe.grundreihe);
		reichweite = 7;
		richtung = Arrays.asList();
		//this.linkerTurm = linkerTurm;
	}
	@Override
	public boolean move(char x, int y) {
		boolean legalerZug = super.move(x, y);
//		if(legalerZug && schachbrett.getFigur('e', farbe.grundreihe) instanceof Koenig) {
//			Koenig koenig = (Koenig) schachbrett.getFigur('e', farbe.grundreihe);
//			if(linkerTurm) {
//				koenig.langeRochade = false;
//			}else {
//				koenig.kurzeRochade = false;
//			}
//		}
		return legalerZug;
	}
	@Override
	public char toChar() {
		return 'T';
	}



}
