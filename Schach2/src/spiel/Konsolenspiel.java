package spiel;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

import enums.Farbe;
import exceptions.FalscheFarbeException;
import exceptions.UngueltigerZugException;
import spiellogik.Spiel;

public class Konsolenspiel {
	public static void main(String[] args) {
		Spiel spiel = new Spiel();
		System.out.println(spiel);
		Scanner sc = new Scanner(System.in);
		while(spiel.getGewinner()==null) {
			System.out.print("Gebe einen Zug ein:");
			String zug = sc.next();
			try {
				spiel.bewegeFigur(zug);
			} catch (UngueltigerZugException e) {
				System.err.println("Ungültiger Zug");
			}catch(NoSuchElementException e) {
				System.err.println("Falsche Farbe am Zug");
			}
			System.out.println(spiel);
			System.out.println("Stellung nach: "+spiel.getSpielverlauf());
			System.out.println();
		}
		sc.close();
	}

}
