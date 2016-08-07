package img;


import java.awt.Toolkit;

public class Image {
	
	//private static InputStream is_vide = ClassLoader.getSystemResourceAsStream("sprite_vide.png") ;
			//Image.class.getClassLoader().getResourceAsStream("sprite_vide.png");
//	public static final java.awt.Image SPRITE_VIDE = ImageIO.read(is_vide);	
	
	public static final java.awt.Image SPRITE_VIDE = Toolkit.getDefaultToolkit().getImage(Image.class.getResource("sprite_vide.png"));
	
	public static final java.awt.Image SPRITE_RED = Toolkit.getDefaultToolkit().getImage(Image.class.getResource("sprite_red.png"));
	public static final java.awt.Image SPRITE_YELLOW = Toolkit.getDefaultToolkit().getImage(Image.class.getResource("sprite_yellow.png"));

	/*public static final java.awt.Image SPRITE_VIDE = new javax.swing.ImageIcon(Jeu.class.getResource("../img/sprite_vide.png")).getImage();
	public static final java.awt.Image SPRITE_RED = new javax.swing.ImageIcon(Jeu.class.getResource("../img/sprite_red.png")).getImage();
	public static final java.awt.Image SPRITE_YELLOW = new javax.swing.ImageIcon(Jeu.class.getResource("../img/sprite_yellow.png")).getImage();
*/


}
