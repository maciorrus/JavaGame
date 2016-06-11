import java.awt.Graphics2D;

public abstract class GameObject {

	protected int width, height, x ,y, state;
	
	public GameObject(int xx, int yy, int w, int h) {
		width = w;
		height = h;
		x =xx;
		y = yy;
		state = 0;
	}

	public abstract void draw(Graphics2D g);

	public boolean onHover(int xpos, int ypos, int buttons) {
		double scalex = 2;
		double scaley = 2;
		if( (xpos > (int) (this.x * scalex)) && (xpos < (int) (this.x * scalex + width * scalex)) && (ypos > (int) (this.y * scaley)) && (ypos < (int) (this.y * scaley + height * scaley)) ) {
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
