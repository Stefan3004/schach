/**
 * 
 */
package figuren;

import java.util.Arrays;

import enums.Farbe;
import enums.Richtung;

/**
 * @author Stefan
 *
 */
public class Dame extends LineareFiguren {

	/**
	 * 
	 */
	public Dame(Farbe farbe) {
		super("Dame", farbe, 7, Richtung.values());
		setFeld('d', farbe.grundreihe);
	}

	@Override
	public char toChar() {
		return 'D';
	}

	


}
