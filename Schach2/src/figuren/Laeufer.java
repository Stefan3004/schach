package figuren;

import java.util.Arrays;

import enums.Farbe;
import enums.Richtung;

public class Laeufer extends LineareFiguren {
	public Laeufer(boolean linkerLaeufer, Farbe farbe) {
		super("Läufer", farbe, 7, Richtung.LINKSOBEN, Richtung.RECHTSOBEN, Richtung.LINKSUNTEN, Richtung.RECHTSUNTEN);
		char x = linkerLaeufer?'c':'f';
		setFeld(x, farbe.grundreihe);
	}

	@Override
	public char toChar() {
		return 'L';
	}

}
