import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;


public class GameButton extends GameObject{

	private Image strong, weak, on;
	private gameView parentView;
	
	public GameButton(int xx, int yy, int w, int h, String s, String we, String o, gameView gv) {
		super(xx,yy,w,h);
		strong = new ImageIcon(s).getImage();
		weak = new ImageIcon(we).getImage();
		on = new ImageIcon(o).getImage();
	}

	@Override
	public void draw(Graphics2D g) {
		double scalex = 15;
		double scaley = 5;
		if (state == 3) state = 1;
		if(state == 0)
			g.drawImage(strong, (int) (this.x * scalex), (int) (this.y * scaley), (int) (width*scalex), (int) (height * scaley), null);
		else if (state == 1)
			g.drawImage(weak, (int) (this.x * scalex), (int) (this.y * scaley), (int) (width*scalex), (int) (height * scaley), null);
		else if (state == 2){
			g.drawImage(on, (int) (this.x * scalex), (int) (this.y * scaley), (int) (width*scalex), (int) (height * scaley), null);
		}
	}

	@Override
	public void onClick() {
		parentView.application.stop();
	}

}
