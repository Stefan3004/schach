package spiel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.UngueltigerZugException;
import spiellogik.Spiel;

public class Zugnotation {	
	public static Character[] getZugfeld(String zug, Spiel spiel) throws UngueltigerZugException {
		boolean b1 = Pattern.matches("[a-h][1-8]", zug);
		boolean b2 = Pattern.matches("[TSLDK][a-h][1-8]", zug);
		boolean b3 = Pattern.matches("0-0", zug);
		boolean b4 = Pattern.matches("[a-h]x[a-h][1-8]", zug);
		boolean b5 = Pattern.matches("[TSLDK]x[a-h][1-8]", zug);
		boolean b6 = Pattern.matches("[TSLD][a-h][a-h][1-8]", zug);
		boolean b7 = Pattern.matches("[TSLD][a-h]x[a-h][1-8]", zug);
		boolean b8 = Pattern.matches("0-0-0", zug);
		
		if(b1) {
			return new Character[] {'B', null, null, zug.charAt(0), zug.charAt(1)};
		}
		if(b2) {
			return new Character[] {zug.charAt(0), null, null, zug.charAt(1), zug.charAt(2)};
		}
		if(b3) {
			return new Character[] {'K', null, null, 'g', Character.forDigit(spiel.getSpielerAmZug().getFarbe().grundreihe, 10)};
		}
		if(b4) {
			return new Character[] {'B', zug.charAt(0), null, zug.charAt(2), zug.charAt(3)};
		}
		if(b5) {
			return new Character[] {zug.charAt(0), null, null, zug.charAt(2), zug.charAt(3)};
		}
		if(b6) {
			return new Character[] {zug.charAt(0), zug.charAt(1), null, zug.charAt(2), zug.charAt(3)};
		}
		if(b7) {
			return new Character[] {zug.charAt(0), zug.charAt(1), null, zug.charAt(3), zug.charAt(4)};
		}
		if(b8) {
			return new Character[] {'K', null, null, 'c', Character.forDigit(spiel.getSpielerAmZug().getFarbe().grundreihe, 10)};
		}
		throw new UngueltigerZugException();
	}
}
