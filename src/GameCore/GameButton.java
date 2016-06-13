package GameCore;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;


public class GameButton extends GameObject{

	private Image strong, weak, on;
	private GameView parentView;
	
	public GameButton(String ss,int xx, int yy, int w, int h, String s, String we, String o, GameView gv) {
		super(ss,xx,yy,w,h);
		parentView = gv;
		strong = new ImageIcon(s).getImage();
		weak = new ImageIcon(we).getImage();
		on = new ImageIcon(o).getImage();
	}

	@Override
	public void draw(Graphics2D g) {
		if (state == 3) state = 1;
		if(state == 0)
			g.drawImage(strong, (int) (this.x * scaleX), (int) (this.y * scaleY), (int) (width*scaleX), (int) (height * scaleY), null);
		else if (state == 1)
			g.drawImage(weak, (int) (this.x * scaleX), (int) (this.y * scaleY), (int) (width*scaleX), (int) (height * scaleY), null);
		else if (state == 2){
			g.drawImage(on, (int) (this.x * scaleX), (int) (this.y * scaleY), (int) (width*scaleX), (int) (height * scaleY), null);
		}
		//System.out.println("Drawing button at " + (int) (this.x * scaleX) + ":" + (int) (this.y * scaleY));
	}

	@Override
	public void onClick() {
		parentView.application.stop();
	}

}
