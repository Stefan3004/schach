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
	 * @param x ist die Ziellinie f�r die Figur. Dieser Parameter muss zwischen 'a' und 'h' liegen.
	 * @param y	ist die Zielreihe f�r die Figur. Dieser Parameter muss zwischen 1 und 8 liegen.
	 * @return	Der R�ckgabewert ist ein boolean. Wenn der Zug ausf�hrbar ist, wird {@code true} zur�ckgegeben, ansonsten {@code false}.
	 */
	public boolean move(char x, int y);

	/**
	 * @return zweidimensinonales boolean Feld zur Beschreibung der m�glichen Zugfelder. Eine Figur kann auf alle Felder ziehen, die true sind.
	 */
	public boolean[][] getZugfelder();
	
	/**
	 * @return Farbe der Figur. Dies ist entweder {@code Farbe.WEI�} oder {@code Farbe.SCHWARZ}.
	 */
	public Farbe getFarbe();
	
	
	/**
	 * @return Ausgabe der m�glichen Zugfelder als String
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
