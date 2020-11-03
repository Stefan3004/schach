/**
 * 
 */
package interfaces;

import enums.Farbe;
import spiel.Zugnotation;

/** 
 * Dieses Interface dient zur Beschreibung einer Schachfigur.
 * 
 */
public interface Figur {
	
	/**
	 * @return Charakter zur Beschreibung der Figur
	 */
	public char toChar();

	/**
	 * @param x ist die Ziellinie für die Figur. Dieser Parameter muss zwischen 'a' und 'h' liegen.
	 * @param y	ist die Zielreihe für die Figur. Dieser Parameter muss zwischen 1 und 8 liegen.
	 * @return	Der Rückgabewert ist ein boolean. Wenn der Zug ausführbar ist, wird {@code true} zurückgegeben, ansonsten {@code false}.
	 */
	public boolean move(char x, int y);

	/**
	 * @return zweidimensinonales boolean Feld zur Beschreibung der möglichen Zugfelder. Eine Figur kann auf alle Felder ziehen, die true sind.
	 */
	public boolean[][] getZugfelder();
	
	/**
	 * @return Farbe der Figur. Dies ist entweder {@code Farbe.WEIß} oder {@code Farbe.SCHWARZ}.
	 */
	public Farbe getFarbe();
	
	
	/**
	 * @return Ausgabe der möglichen Zugfelder als String
	 */
	public String printZugfelder();
	
	/**
	 * @return Linie(a-h), auf der die Figur steht.
	 */
	public char getSpalte();

	/**
	 * @return Reihe(1-8), in der die Figur steht
	 */
	public int getReihe();
	
	/**
	 * @return String zur Beschreibung der Figur
	 */
	String figurBeschreibung();

	/**
	 * @return gibt an, ob die Figur schon bewegt wurde
	 */
	boolean hasMoved();
	
	public String getName();

	void setFeld(char x, int y);
}
