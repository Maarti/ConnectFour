package sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.KeyEvent;

public class Sound {
	public static final AudioClip OWL = Applet.newAudioClip(Sound.class.getResource("owl1.wav"));
	public static final AudioClip COIN = Applet.newAudioClip(Sound.class.getResource("coin1.wav"));
	public static final AudioClip WIN = Applet.newAudioClip(Sound.class.getResource("win1.wav"));
	public static final AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("back2.wav"));
	
	private boolean mute;
	
	public Sound() {
		super();
		this.mute = false;
	}
	
	public void switchMute(){
		if(mute)
		{
			this.mute=false;
			playBackground();
		}
		else
		{
			this.mute=true;
			stopAll();
		}
	}
	
	public void play(AudioClip a, boolean loop){
		if(!mute){
			if(loop)
				a.loop();
			else
				a.play();
		}
	}
	
	public void stop(AudioClip a){
		a.stop();
	}
	
	public void playBackground(){
		play(BACK, true);
	}
	
	public void stopAll(){
		OWL.stop();
		COIN.stop();
		WIN.stop();
		BACK.stop();
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_M)
			switchMute();
	}
}