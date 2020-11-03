package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import figuren.AbstrakteFigur;
import interfaces.Figur;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import spiellogik.Spiel;

public class SchachspielController implements Initializable {
	Spiel spiel;
	boolean figurBestimmt = false;
	Figur figur;
	ImageView figurBild;
	private HashMap<Figur, ImageView> figurenBilder = new HashMap<Figur, ImageView>();
	List<Pane> felder = new ArrayList<Pane>();
	
	@FXML
	private GridPane schachbrett;
	
	private void ladeBilder() {
		for (Figur figur : spiel.weiss.getAlleFiguren()) {
			figurenBilder.put(figur, new ImageView("file:C:/Users/Stefan/Desktop/Schach/Weiss/"+figur.getName()+".png"));
		}
		for (Figur figur : spiel.schwarz.getAlleFiguren()) {
			figurenBilder.put(figur, new ImageView("file:C:/Users/Stefan/Desktop/Schach/Schwarz/"+figur.getName()+".png"));
		}
		figurenBilder.values().forEach(iv->{iv.setFitWidth(70); iv.setFitHeight(70);});
	}
	
	private void clearBrett() {
		felder.forEach(pane -> pane.getChildren().clear());
	}
	
	private void aktualisiereStellung() {
		clearBrett();
		for (char x = 'a'; x <= 'h'; x++) {
			for (int y = 1; y <= 8; y++) {
				String id = "#"+x+y;
				Pane feld = (Pane) schachbrett.lookup(id);
				Figur figur = AbstrakteFigur.schachbrett.getFigur(x, y);
				if (figur!=null) {
					feld.getChildren().add(figurenBilder.get(figur));
				}
				
			}
		}
//		System.out.println(AbstrakteFigur.schachbrett.figurenAufBrett());
//		for (Figur figur : AbstrakteFigur.schachbrett.figurenAufBrett()) {
//			String id = figur.getSpalte()+""+figur.getReihe();
//			Pane feld = (Pane) schachbrett.lookup("#"+id);
//			try {
//				feld.getChildren().add(figurenBilder.get(figur));
//			} catch (Exception e) {
//			}
//		}
		
		for (Node feld : schachbrett.getChildren()) {
			feld.getStyleClass().remove("zugfeld");
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		spiel = new Spiel();
		ladeBilder();
		for (char i = 'a'; i <= 'h'; i++) {
			for (int j = 1; j <= 8; j++) {
				Pane feld = new Pane();
				if((i+j)%2==0) {
					feld.getStyleClass().add("schwarz");
				}else {
					feld.getStyleClass().add("weiss");
				}
				feld.setOnMouseClicked(mouseEvent -> feldauswahl(mouseEvent));
				feld.setId(i+""+j);
				schachbrett.add(feld, i-'a', 8-j);
				felder.add(feld);
			}
		}
		aktualisiereStellung();
	}
//	private void setzeFeld(char x, int y, ImageView iv) {
//		Pane sp = (Pane) schachbrett.lookup("#"+x+""+y);
//		sp.getChildren().add(iv);
//	}
	private void feldauswahl(MouseEvent mouseEvent) {
		Pane feld = (Pane) mouseEvent.getSource();
		if(!figurBestimmt && spiel.getFigur(feld.getId())!= null) {
			figur = spiel.getFigur(feld.getId());
			if (figur.getFarbe()==spiel.getSpielerAmZug().getFarbe()) {
				System.out.println(figurBestimmt);
				zeigeZugfelder(figur);
				figurBestimmt = true;
			}
		} else
		if (figurBestimmt) {
			String id = feld.getId();
			char x = id.charAt(0);
			int y = Character.getNumericValue(id.charAt(1));
			if(spiel.bewegeFigur(figur, x, y)) {
				aktualisiereStellung();
				
			}
			figurBestimmt = false;
		}
	}
	
	private void zeigeZugfelder(Figur figur){
		boolean[][] zugfelder = figur.getZugfelder();
		for (char i = 'a'; i <= 'h'; i++) {
			for (int j = 1; j <= 8; j++) {
				if(zugfelder[j-1][i-'a']) {
					System.out.println("#"+i+""+j);
					Pane feld = (Pane) schachbrett.lookup("#"+i+""+j);
					feld.getStyleClass().add("zugfeld");
				}
			}
		}
	}
//	private ImageView getFigurBild(String feldId) {
//		Pane feld = (Pane) schachbrett.lookup(feldId);
//		return (ImageView) feld.getChildren().get(1);
//	}
//	private void addFiguren() {
//		
//	}

}
