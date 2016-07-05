package PlayScene;

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

import engine.Engine;
import engine.Scene;
import game.GameInputText;
import game.GameObject;
import game.GameButton;

public class PlayScene implements Scene {
	Texture t;
	private Set<GameObject> gameObjects;
	GameObject focus;
	private int mouseX, mouseY;
	private Set<Integer> keyCodes;
	private Engine e;
	private Snake snake;

	public PlayScene(Set<Integer> keyCode, Engine engine) {
		keyCodes = keyCode;
		gameObjects = new HashSet<GameObject>();
		focus = null;
		e = engine;
		snake = new Snake();
	}

	@Override
	public void init(GLAutoDrawable drawable) {
	}

	@Override
	public void update() {
		if (snake.update())
			e.scene = e.sceneList.get(0);
	}

	@Override
	public void render(GL2 gl) {
		snake.draw(gl);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyChar() + " pressed");
		switch (e.getKeyChar()) {
		case 'w':
			System.out.println("Go up!");
			snake.move(2);
			break;
		case 'd':
			snake.move(1);
			break;
		case 's':
			snake.move(4);
			break;
		case 'a':
			snake.move(3);
			break;
		}
	}

}
