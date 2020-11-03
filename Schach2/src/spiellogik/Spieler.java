package spiellogik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.Farbe;
import figuren.Bauer;
import figuren.Dame;
import figuren.AbstrakteFigur;
import figuren.Koenig;
import figuren.Laeufer;
import figuren.Springer;
import figuren.Turm;
import interfaces.Figur;

public class Spieler {
	private Farbe farbe;
	boolean schachmatt = false;
	private Map<Character, List<Figur>> figuren = new HashMap<Character, List<Figur>>();
	private List<Figur> alleFiguren = new ArrayList<Figur>();
	
	public Spieler(Farbe farbe) {
		this.farbe = farbe;
		figuren.put('B', new ArrayList<Figur>());
		for (char i='a'; i <= 'h'; i++) {
			figuren.get('B').add(new Bauer(i, farbe));
			alleFiguren.addAll(figuren.get('B'));
		}
		List<Figur> turm = Arrays.asList(new Turm(true, farbe), new Turm(false, farbe));
		figuren.put('T', turm);
		alleFiguren.addAll(turm);
		List<Figur> springer = Arrays.asList(new Springer(true, farbe), new Springer(false, farbe));
		figuren.put('S', springer);
		alleFiguren.addAll(springer);
		List<Figur> laeufer = Arrays.asList(new Laeufer(true, farbe), new Laeufer(false, farbe));
		figuren.put('L', laeufer);
		alleFiguren.addAll(laeufer);
		figuren.put('D', Arrays.asList(new Dame(farbe)));
		alleFiguren.addAll(figuren.get('D'));
		figuren.put('K', Arrays.asList(new Koenig(farbe)));
		alleFiguren.addAll(figuren.get('K'));
	}
	
	public Spieler(String conf, Farbe farbe) {
		this(farbe);
		figuren.values().forEach(x -> x.forEach(y-> y.setFeld(' ', -1)));
		// T:1:A:1, S:1:B:1, D:1:D:1
		String [] figurArray = conf.split(",");
		for (String figur : figurArray) {
			String [] daten = figur.trim().split(":");
			char name = daten[0].charAt(0);
			int index = Integer.parseInt(daten[1])-1;
			char x = daten[2].charAt(0);
			int y = Integer.parseInt(daten[3]);
			figuren.get(name).get(index).setFeld(x, y);
		}
	}  
	
	public Farbe getFarbe(){
		return farbe;
	}
	
	public List<Figur> getAlleFiguren() {
		return alleFiguren;
	}
	
	public List<Figur> getFigur(char c){
		return figuren.get(c);
	}

}
