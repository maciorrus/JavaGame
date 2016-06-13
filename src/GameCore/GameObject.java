package GameCore;
import java.awt.Graphics2D;

public abstract class GameObject {

	protected int width, height, x ,y, state;
	public String id;
	
	public static double scaleX, scaleY;
	
	public GameObject(String s, int xx, int yy, int w, int h) {
		width = w;
		height = h;
		x =xx;
		y = yy;
		state = 0;
		id = s;

		scaleX = Core.modes[0].getWidth()/1000.0;
		scaleY = Core.modes[0].getHeight()/800.0;
	}

	public abstract void draw(Graphics2D g);

	public boolean onHover(int xpos, int ypos, int buttons) {
		if( (xpos > (int) (this.x * scaleX)) && (xpos < (int) (this.x * scaleX + width * scaleX)) && (ypos > (int) (this.y * scaleY)) && (ypos < (int) (this.y * scaleY + height * scaleY)) ) {
			if(buttons == 1){
				state = 2;
				return true;
			}else{
				if(state ==2) state = 3; 
				else state = 1;
				return true;
			}
		}else{
			state = 0;
			return false;
		}
	}

	public abstract void onClick() ;
	
	
	
	
}
