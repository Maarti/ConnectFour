package modele;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import sound.Sound;

import jeu.Jeu;

import enums.CaseEtat;

public class Grille {
	private Case[][] grille;
	private Jeu jeu;
	private CaseEtat lastPlayer;
	private Case lastCasePlayed;

	public Grille(int nbLignes, int nbColonnes, Jeu jeu) {
		super();
		this.grille = new Case[nbLignes][nbColonnes] ;
		for(int ligne=0; ligne<nbLignes; ligne++){
			for(int col=0; col<nbColonnes; col++){
				Case laCase = new Case(ligne, col);
				this.grille[ligne][col] = laCase;
			}
		}
		this.jeu = jeu;
	}

	public Grille(Jeu jeu) {
		this(6, 7, jeu);
	}

	public void display(){
		display(true);
	}

	public void display(boolean showNumber){
		String rowPrint = new String();
		for (int ligne=0; ligne<this.grille.length; ligne++){
			rowPrint = "";
			for (int col=0; col<this.grille[ligne].length; col++){
				Case laCase = this.grille[ligne][col];
				//rowPrint = rowPrint + laCase.printId(this.grille[ligne].length)) + " ";
				rowPrint = rowPrint + laCase.toString() + " ";
			}
			System.out.println(rowPrint);
		}
		if (showNumber){
			rowPrint = "";
			for (int col=0; col<this.grille[0].length; col++){
				rowPrint = rowPrint + " " + col + "  ";
			}
			System.out.println(rowPrint);
		}
	}


	public void jouer (int numCol, CaseEtat couleur){
		if (numCol >= 0 && numCol < grille[0].length){
			int ligneJouable = getLigneJouable(numCol);
			if (ligneJouable >= 0) {			
				Case c = this.grille[ligneJouable][numCol];
				c.setEtat(couleur);
				this.lastPlayer = couleur;
				this.lastCasePlayed = c;
				if (couleur == CaseEtat.Jaune)
					jeu.getSound().play(Sound.OWL, false);
				else
					jeu.getSound().play(Sound.COIN, false);
				display();
				jeu.checkGameProgress();
				jeu.switchPlayer();

			}else{
				System.out.println("Colonne "+numCol+" pleine !");
			}
		}else{
			System.out.println("Colonne incorrecte : "+numCol);
		}
	}

	// Retourne la ligne jouable d'une colonne
	private int getLigneJouable(int numCol){		
		for(int ligne=this.grille.length-1; ligne>=0; ligne--){
			CaseEtat etat = grille[ligne][numCol].getEtat();
			if (etat == CaseEtat.Vide){
				return ligne;
			}
		}
		// Pas de ligne jouable
		return -1;
	}

	public boolean gameWon(){		
		if (checkRow() || checkCol() || checkDiag())
			return true;		
		return false;
	}

	private boolean checkRow(){
		for (int row=this.grille.length-1; row>=0; row--){
			if (checkRow(row))
				return true;
		}
		return false;
	}

	private boolean checkRow(int row){
		int inArow =1;
		CaseEtat prevEtat = this.grille[row][0].getEtat();

		for(int i=1;i<=this.grille[row].length-1;i++){			
			CaseEtat curEtat = this.grille[row][i].getEtat();
			if (curEtat!=CaseEtat.Vide && curEtat==prevEtat)
				inArow++;
			else
				inArow=1;			
			prevEtat = curEtat;
			if (inArow==4)
				return true;
		}		
		return false;
	}

	private boolean checkCol(){
		for (int col=0; col<this.grille[0].length; col++){
			if (checkCol(col))
				return true;
		}
		return false;
	}

	private boolean checkCol(int col){
		int rowMin = getLigneJouable(col);
		int nbRow = this.grille.length;
		if (rowMin<nbRow-4){
			int inArow=1;
			CaseEtat prevEtat = this.grille[nbRow-1][col].getEtat();

			for(int i=nbRow-2;i>rowMin;i--){			
				CaseEtat curEtat = this.grille[i][col].getEtat();
				if (curEtat!=CaseEtat.Vide && curEtat==prevEtat)
					inArow++;
				else
					inArow=1;			
				prevEtat = curEtat;
				if (inArow==4)
					return true;
			}	
		}		
		return false;
	}

	private boolean checkDiag(){		
		for(int j=3;j<grille[0].length;j++){
			if (checkDiagUp(0,j))
				return true;
		}
		for(int j=0;j<grille[0].length-3;j++){
			if (checkDiagUp(grille.length-1,j))
				return true;
		}
		for(int j=0;j<grille[0].length-3;j++){
			if (checkDiagDown(0,j))
				return true;
		}
		for(int j=3;j<grille[0].length;j++){
			if (checkDiagDown(grille.length-1,j))
				return true;
		}
		return false;
	}

	private boolean checkDiagUp(int row, int col){
		int inArow =1;
		CaseEtat prevEtat = this.grille[row][col].getEtat();
		if (row==0){
			for(int i=row+1, j=col-1;i<grille.length && j>=0;i++,j--){

				CaseEtat curEtat = this.grille[i][j].getEtat();
				if (curEtat!=CaseEtat.Vide && curEtat==prevEtat)
					inArow++;
				else
					inArow=1;			
				prevEtat = curEtat;
				if (inArow==4)
					return true;
			}
		}else{
			for(int i=row-1, j=col+1;i>=0 && j<grille[0].length;i--,j++){				
				CaseEtat curEtat = this.grille[i][j].getEtat();
				if (curEtat!=CaseEtat.Vide && curEtat==prevEtat)
					inArow++;
				else
					inArow=1;			
				prevEtat = curEtat;
				if (inArow==4)
					return true;
			}
		}
		return false;
	}

	private boolean checkDiagDown(int row, int col){
		int inArow =1;
		CaseEtat prevEtat = this.grille[row][col].getEtat();
		if (row==0){
			for(int i=row+1, j=col+1;i<grille.length && j<grille[i].length;i++,j++){

				CaseEtat curEtat = this.grille[i][j].getEtat();
				if (curEtat!=CaseEtat.Vide && curEtat==prevEtat)
					inArow++;
				else
					inArow=1;			
				prevEtat = curEtat;
				if (inArow==4)
					return true;
			}
		}else{
			for(int i=row-1, j=col-1;i>=0 && j>=0;i--,j--){				
				CaseEtat curEtat = this.grille[i][j].getEtat();
				if (curEtat!=CaseEtat.Vide && curEtat==prevEtat)
					inArow++;
				else
					inArow=1;			
				prevEtat = curEtat;
				if (inArow==4)
					return true;
			}
		}
		return false;
	}

	public boolean gameFull(){		
		for(Case c : this.grille[0]){
			if(c.getEtat()==CaseEtat.Vide)
				return false;
		}
		return true;
	}

	public void paint(Graphics2D g){
		for (Case[] ligne : grille){
			for(Case c : ligne){
				c.paint(g);
			}
		}
		if (lastCasePlayed != null)
			lastCasePlayed.paintCircle(g,Color.BLUE);
		paintNumCol(g);
	}

	public void paintNumCol(Graphics2D g){
		g.setColor(Color.BLACK);
		g.setFont(new Font("Verdana", Font.BOLD, 26));
		for (int i=1;i<=grille[0].length;i++){
			String s = Integer.toString(i);
			g.drawString(s, (i-1)*Case.CASEWIDTH+Case.CASEWIDTH/2-10, grille.length*Case.CASEWIDTH+30);
		}
	}
	
	/*public void paintWinCircles() {
		if (lastCasePlayed!=null){
			int inArow = 1;
			for(Case c : grille[lastCasePlayed.getLigne()]){
				
			}
		}
	}*/

	public void keyPressed(KeyEvent e, CaseEtat player) {
		char c = e.getKeyChar();
		int i = Character.getNumericValue(c);		
		jouer(i-1, player);
	}

	public CaseEtat getLastPlayer() {
		return lastPlayer;
	}
}
