package game;

import java.awt.event.KeyEvent;

import com.jogamp.opengl.GLAutoDrawable;

public abstract class GameObject {

	private int x, y, width, height;
	protected boolean hover, focused;

	public abstract void draw(GLAutoDrawable drawable);

	public abstract void onhover();

	public abstract void offhover();

	public abstract void onClick();

	public boolean focusable() {
		return false;
	}

	public void offFocus() {
		focused = false;
	}

	public void onFocus() {
		focused = true;
	}

	public boolean hover(int xx, int yy) {
		if (x < xx && x + width > xx && y < yy && y + height > yy) {
			if (hover == false) {
				hover = true;
				onhover();
			}
		} else {
			if (hover == true) {
				hover = false;
				offhover();
			}
		}
		return hover;
	}
	
	public void keyTyped(KeyEvent e){
		
	}

	public GameObject(int xx, int yy, int w, int h) {
		x = xx;
		y = yy;
		width = w;
		height = h;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return width;
	}

	public int getH() {
		return height;
	}

	public int getX() {
		return x;
	}

}
