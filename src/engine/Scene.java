package engine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Set;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public interface Scene {
	
	public void mouseClicked(MouseEvent arg0);
	public void mouseMoved(MouseEvent e);
	public void init(GLAutoDrawable drawable);
	public void update();
	public void render(GL2 gl);
	public void keyTyped(KeyEvent e);

}
