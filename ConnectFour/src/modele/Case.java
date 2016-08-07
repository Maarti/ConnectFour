package modele;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;	
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.net.URL;

import enums.CaseEtat;

public class Case {
	private CaseEtat etat;
	private int ligne;
	private int colonne;
	public static final int COINDIAMETER = 64;
	public static final int CASEWIDTH = 64;
	URL url = getClass().getResource("sprite.png");
	Image img1 = Toolkit.getDefaultToolkit().getImage(url);

	public Case(int ligne, int colonne) {
		super();
		this.etat = CaseEtat.Vide;
		this.ligne = ligne;
		this.colonne = colonne;
	}
	public CaseEtat getEtat() {
		return etat;
	}
	public void setEtat(CaseEtat etat) {
		this.etat = etat;
	}
	public int getLigne() {
		return ligne;
	}
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}
	public int getColonne() {
		return colonne;
	}
	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public String toString(){
		switch (etat) {
		case Rouge:
			return "[X]";

		case Jaune:
			return "[O]";

		default:
			return "[ ]";
		}
	}

	// Retourne l'id de la case en fonction du nb max de colonnes
	public int getId(int nbMaxCol){
		return this.ligne*nbMaxCol + this.colonne;
	}

	public String printId(int nbMaxCol){
		return "["+ Integer.toString(this.getId(nbMaxCol)) + "]";
	}

	private int getXcase(){
		return colonne*CASEWIDTH;
	}
	private int getYcase(){
		return ligne*CASEWIDTH;
	}
	private int getXcoin(){
		return getXcase()+(CASEWIDTH-COINDIAMETER)/2;
	}
	private int getYcoin(){
		return getYcase()+(CASEWIDTH-COINDIAMETER)/2;
	}


	public void paint(Graphics2D g) {
		paintImage(g);
		//paintShape(g);
	}	

	private void paintImage(Graphics2D g){

		//img.Image myimage = new img.Image();
		g.drawImage(img.Image.SPRITE_VIDE, getXcase(), getYcase(), null);
		switch (etat) {
		case Jaune:
			g.drawImage(img.Image.SPRITE_YELLOW, getXcase(), getYcase(), null);
			break;
		case Rouge:
			g.drawImage(img.Image.SPRITE_RED, getXcase(), getYcase(), null);
			break;
		default:
			break;
		}
	}

	
	@SuppressWarnings("unused")
	private void paintShape(Graphics2D g){
		g.setColor(Color.BLUE);
		//g.fillRect(getXcase(), getYcase(), CASEWIDTH, CASEWIDTH);
		g.fill3DRect(getXcase(), getYcase(), CASEWIDTH, CASEWIDTH,true);
		switch (etat) {
		case Jaune:
			g.setColor(Color.YELLOW);	
			break;
		case Rouge:
			g.setColor(Color.RED);
			break;
		default:
			g.setColor(Color.WHITE);
			break;
		}
		g.fillOval(getXcoin(), getYcoin(), COINDIAMETER, COINDIAMETER);
	}

	public void paintCircle(Graphics2D g, Color c){
		g.setColor(c);
		Stroke oldStroke = g.getStroke(); 
		g.setStroke(new BasicStroke(5));
		g.drawOval(getXcoin(), getYcoin(), COINDIAMETER-2, COINDIAMETER-2);
		g.setStroke(oldStroke);
	}
}
