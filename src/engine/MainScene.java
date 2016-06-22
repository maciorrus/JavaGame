package engine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import game.GameInputText;
import game.GameObject;
import mainScene.ExitButton;
import mainScene.StartButton;
import game.GameButton;

public class MainScene implements Scene {
	Texture t;
	private Set<GameObject> gameObjects;
	GameObject focus;
	private int mouseX, mouseY;
	private Set<Integer> keyCodes;
	private Engine e;

	public MainScene(Set<Integer> keyCode, Engine engine) {
		keyCodes = keyCode;
		gameObjects = new HashSet<GameObject>();
		ExitButton ex = new ExitButton();
		gameObjects.add(new StartButton(ex));
		gameObjects.add(ex);
		focus = null;
		e = engine;
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		try {
			t = TextureIO.newTexture(this.getClass().getResource("..\\images\\back\\mainBackground.jpg"), false,
					".jpg");
			int tex = t.getTextureObject();
		} catch (GLException | IOException e) {
			System.out.println("Failed to load img");
			e.printStackTrace();
		}

	}

	@Override
	public void update() {
	}

	@Override
	public void render(GL2 gl) {
		if (t != null) {
			gl.glEnable(GL2.GL_TEXTURE_2D);
			t.enable(gl);
			t.bind(gl);
			gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(0.0f, 0.0f);
			gl.glVertex2d(-1.0, -1.0);
			gl.glTexCoord2f(0.0f, 1.0f);
			gl.glVertex2d(-1.0, 1.0);
			gl.glTexCoord2f(1.0f, 1.0f);
			gl.glVertex2d(1.0, 1.0);
			gl.glTexCoord2f(1.0f, 0.0f);
			gl.glVertex2d(1.0, -1.0);
			gl.glEnd();
			gl.glDisable(GL2.GL_TEXTURE_2D);
			for (GameObject o : gameObjects)
				o.draw(gl);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		for (GameObject o : gameObjects) {
			if (o.hover(mouseX, 600 - mouseY))
				break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		for (GameObject o : gameObjects) {
			if (o.hover(mouseX, 600 - mouseY)) {
				if (o.focusable()) {
					if (focus != null)
						focus.offFocus();
					focus = o;
					focus.onFocus();
				}
				o.onClick();
				return;
			}
		}
		if (focus != null)
			focus.offFocus();
		focus = null;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		for (GameObject o : gameObjects) {
			o.keyTyped(e);
		}
	}

}
