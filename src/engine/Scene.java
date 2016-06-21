package engine;

import com.jogamp.opengl.GLAutoDrawable;

public interface Scene {
	
	public void draw(GLAutoDrawable drawable);
	public void update();

}
