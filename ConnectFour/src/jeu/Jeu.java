package jeu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sound.Sound;

import enums.CaseEtat;


import modele.Case;
import modele.Grille;

@SuppressWarnings("serial")
public class Jeu extends JPanel {

	private Grille grille;
	private CaseEtat player;
	private Sound sound;


	public Jeu () {
		super();
		this.grille = new Grille(this);
		this.player = CaseEtat.Jaune;
		this.sound = new Sound();

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				grille.keyPressed(e, player);
				sound.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		setFocusable(true);
		sound.playBackground();
	}


	public void checkGameProgress(){
		if (this.grille.gameWon()){
			sound.stop(Sound.BACK);
			sound.play(Sound.WIN, false);
			JOptionPane.showMessageDialog(this, "Victoire du "+grille.getLastPlayer().toString()+" !", "Victoire", JOptionPane.INFORMATION_MESSAGE);
			System.exit(ABORT);
		}else if (this.grille.gameFull()){
			sound.stop(Sound.BACK);
			JOptionPane.showMessageDialog(this, "Grille pleine", "Game Over", JOptionPane.YES_NO_OPTION);
			System.exit(ABORT);
		}
	}

	public void switchPlayer() {
		if (player == CaseEtat.Jaune){
			player = CaseEtat.Rouge;
		}else{
			player = CaseEtat.Jaune;
		}		
	}


	@Override
	public void paint(Graphics g) {
		super.paint(g);	//Efface l'écran
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

		grille.paint(g2d);
		paintPlayer(g2d);
	}

	private void paintPlayer(Graphics2D g2d) {
		if (player==CaseEtat.Rouge)
			g2d.drawImage(img.Image.SPRITE_RED, Case.CASEWIDTH*7+25, 25, null);
		else
			g2d.drawImage(img.Image.SPRITE_YELLOW, Case.CASEWIDTH*7+25, 25, null);
	}


	public Sound getSound() {
		return sound;
	}	

	public static void main(String [ ] args) throws InterruptedException
	{		
		JFrame frame = new JFrame("Puissance4");
		Jeu jeu = new Jeu();		

		frame.add(jeu);
		frame.setSize((Case.CASEWIDTH+2)*7+110, 460);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			jeu.repaint();
			Thread.sleep(10);
		}
	}


}
