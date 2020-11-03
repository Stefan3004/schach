package spiellogik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import enums.Farbe;
import exceptions.FalscheFarbeException;
import exceptions.UngueltigerZugException;
import figuren.AbstrakteFigur;
import figuren.Bauer;
import figuren.Turm;
import interfaces.Figur;
import spiel.Zugnotation;

public class Spiel {
	public Spieler weiss;
	public Spieler schwarz;
	private boolean weissAmZug;
	StringBuilder spielverlauf = new StringBuilder();
	int zugNr = 1;
	
	public Spiel() {
		weiss = new Spieler(Farbe.Weiß);
		schwarz = new Spieler(Farbe.Schwarz);
		weissAmZug = true;
	}
	/**
	 * 
	 * @param datei Konstrukor lädt ein neues Spiel 
	 * @throws FileNotFoundException
	 */
	public Spiel(String datei) throws FileNotFoundException {
		File file = new File(datei);
		Scanner sc = new Scanner(file);
		Farbe farbe = Farbe.valueOf(sc.nextLine());
		String conf = sc.nextLine();
		sc.close();
		weissAmZug = farbe==Farbe.Weiß;
		String confW = conf.split(";")[0];
		String confS = conf.split(";")[1];
		weiss = new Spieler(confW, Farbe.Weiß);
		schwarz = new Spieler(confS, Farbe.Schwarz);
	}
	/**
	 * 
	 * @return String zur Beschreibung des Spiels, für jede Figur werden die Zugfelder ausgegeben
	 */
	public String spielfeldAusgabe() {
		StringBuilder builder = new StringBuilder(AbstrakteFigur.schachbrett.toString());
		AbstrakteFigur.schachbrett.figurenAufBrett().forEach(figur -> builder.append(figur.printZugfelder()));
		return builder.toString();
	}
	
	/**
	 * 
	 * @param figur zu bewegende Figur
	 * @param x2	x-Koordinate vom Zielfeld
	 * @param y2	y-Koordinate vom Zielfeld
	 * @return
	 */
	public boolean bewegeFigur(Figur figur, char x2, int y2) {
		if(figur.move(x2, y2)) {
			beendeZug();
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param zug String zur Beschreibung des Zuges
	 * @throws UngueltigerZugException, falls die Zugnotation ungültig ist
	 * @throws NoSuchElementException
	 */
	public void bewegeFigur(String zug) throws UngueltigerZugException, NoSuchElementException {
		Character[] zugdaten = Zugnotation.getZugfeld(zug, this);
		char figurChar = zugdaten[0];
		Character spalte1 = zugdaten[1];
		//Integer reihe1 =  zugdaten[2]==null?Character.getNumericValue(zugdaten[2]);
		int reihe2 = Character.getNumericValue(zugdaten[4]);
		Character spalte2 = zugdaten[3];
		List<Figur> figurListe = getSpielerAmZug().getFigur(figurChar);
		//figurListe.forEach(f ->System.out.println(f.printZugfelder()));
		Figur figur = figurListe.stream().
				filter(f -> (f.getZugfelder()[reihe2-1][spalte2-'a']&&((spalte1==null)||spalte1== f.getSpalte()))).findFirst().get();
		bewegeFigur(figur, zugdaten[3], Character.getNumericValue(zugdaten[4]));
		if (!weissAmZug) {
			spielverlauf.append(zugNr + ". "+zug+" ");
		} else {
			spielverlauf.append(zug+" ");
			zugNr++;
		}
	}
	/**
	 * 
	 * @param feld Beschreibung des Feldes als String von a1 bis h8
	 * @return Figur, die auf dem übergegebenen Feld liegt
	 */
	public Figur getFigur(String feld) {
		return AbstrakteFigur.schachbrett.getFigur(feld.charAt(0), Character.getNumericValue(feld.charAt(1)));
	}
	
	/**
	 * beendet den Zug durch Invertierung des boolean
	 */
	public void beendeZug() {
		weissAmZug = !weissAmZug;
	}
	
	/**
	 * 
	 * @return Spieler, welcher am Zug ist
	 */
	public Spieler getSpielerAmZug() {
		return weissAmZug?weiss:schwarz;
	}
	/**
	 * 
	 * @return Farbe des Gewinners
	 */
	public Farbe getGewinner() {
		if(weiss.schachmatt) return Farbe.Schwarz;
		if(schwarz.schachmatt) return Farbe.Weiß;
		return null;
	}
	
	public String getSpielverlauf() {
		return spielverlauf.toString();
	}
	
	/**
	 * Ausgabe des Schachbretts mit den darauf stehenden Figuren
	 */
	@Override
	public String toString() {
		return AbstrakteFigur.schachbrett.toString();
	}
	
}
