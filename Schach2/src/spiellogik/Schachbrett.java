package spiellogik;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import enums.Farbe;
import figuren.Koenig;
import interfaces.Figur;

/**
 * @author Stefan
 *
 */
public class Schachbrett {
	private Figur [] [] brett;
	private List<Figur> alleFiguren = new ArrayList<Figur>();
	
	/**
	 * Konstruktor erzeugt ein neues Brett mit der Größe 8x8
	 */
	public Schachbrett() {
		brett =  new Figur [8][8];
	}
	public boolean containsFigur(Figur figur) {
		return alleFiguren.contains(figur);
	}
	
	/**
	 * @param x Linie von a-h
	 * @param y Reihe von 1-8
	 * @return Figur an der angebenen Position
	 */
	public Figur getFigur(char x, int y){
		return brett [y-1][x-'a'];
	}
	
	/** Diese Methode fügt eine Figur an der übergebenen Position ein.
	 * @param figur Figur, die hinzugefügt werden soll
	 * @param x Linie von a-h
	 * @param y Reihe von 1-8
	 */
	public void addFigur(Figur figur, char x, int y){
		brett [y-1][x-'a'] = figur;
		if(!containsFigur(figur)) {
			alleFiguren.add(figur);
		}
	}
	
	/**
	 * Diese Methode löscht eine Figur aus dem Schachbrett. Wenn die angebene Position falsch ist, wird nichts ausgeführt.
	 * @param x Linie von a-h 
	 * @param y Reihe von 1-8
	 */
	public void removeFigur(char x, int y) {
		if(!erlaubtesFeld(x, y)) return;
		Figur figur = getFigur(x, y);
		alleFiguren.remove(figur);
		brett [y-1][x-'a'] = null;
	}
	
	/** Diese Methode liefert die Farbe der Figur zurück. Wenn die angegebene Position 
	 * nicht auf dem Schachbrett liegt oder auf dem Feld keine Figur steht, so wird null zurückgegeben.
	 * @param x Linie von a-h
	 * @param y Reihe von 1-8
	 * @return Farbe der Figur 
	 * 
	 */
	public Farbe getFarbe(char x, int y) {
		if (!erlaubtesFeld(x, y) || brett [y-1][x-'a'] == null) return null;
		return brett [y-1][x-'a'].getFarbe();
	}
	
	/**
	 * @param x Linie von a-h
	 * @param y Reihe von 1-8
	 * @return {@code true}, wenn das angegebene Feld frei ist, ansonsten {@code false}. Bei ungültigen Koordinaten wird ebenfalls {@code false} zurückgegeben.
	 */
	public boolean freiesFeld(char x, int y) {
		return erlaubtesFeld(x, y) && brett[y-1][x-'a']==null;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return {@code true}, wenn Feld frei
	 * 		   {@code false}, wenn Feld besetzt
	 * 		   {@code null}, wenn ungültige Koordinaten
	 */
	public Boolean freiesFeld1(char x, int y) { 
		if(!erlaubtesFeld(x, y))return null;
		return brett[y-1][x-'a']==null;
	}
	
	/** Dies ist eine Hilfsmethode zum Überprüfen der Brettkoordinaten.
	 * @param x Linie von a-h
	 * @param y Reihe von 1-8
	 */
	private static boolean erlaubtesFeld(char x, int y){
		return x>='a' && x<='h'&& y>=1 && y<=8;
	}
	
	public List<Figur> figurenAufBrett(){
		return alleFiguren;
	}
	

	public List<Figur> figurenEinerFarbe(Farbe farbe){
		return alleFiguren.stream().filter(figur -> figur.getFarbe()==farbe).collect(Collectors.toList());
	}
	
	public boolean bedrohtesFeld(char x, int y, Farbe farbe) {
		List<Figur> figurenFarbe = figurenEinerFarbe(farbe);
		return figurenFarbe.stream().anyMatch(figur -> !(figur instanceof Koenig) && figur.getZugfelder()[y-1][x -'a']);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("\n");
		for (int i = brett.length-1; i >= 0; i--) {
			builder.append(i+1);
			for (int j = 0; j < brett.length; j++) {
				builder.append("[");
				if(brett[i][j]!=null) {
					builder.append(brett[i][j]);
				}else {
					builder.append(" ");
				}
				builder.append("]");
				
			}
			builder.append("\n");
		}
		for (char i = 'A'; i <='H'; i++) {
			builder.append("  "+i);
		}
		builder.append("\n");
		return builder.toString();
	}
}
