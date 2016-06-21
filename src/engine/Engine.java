package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import game.GameInputText;
import game.GameObject;
import game.TestButton;

public class Engine extends JFrame implements GLEventListener, MouseMotionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 8819738880472328756L;
	final private int width = 800;
	final private int height = 600;
	private int texture;
	private Set<GameObject> gameObjects;
	private int mouseX, mouseY;
	private String mouseState;
	private Set<Integer> keyCode;
	Texture t;
	char lastPressed;
	GameObject focus;

	public Engine() {
		super("Minimal OpenGL");
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		GLCanvas canvas = new GLCanvas(capabilities);
		canvas.addGLEventListener(this);
		
		FPSAnimator anim = new FPSAnimator(canvas,60);
		anim.start();

		this.setName("Minimal OpenGL");
		this.getContentPane().add(canvas);

		this.setSize(width, height+29);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		canvas.requestFocusInWindow();
		canvas.addMouseMotionListener(this);
		canvas.addMouseListener(this);
		canvas.addKeyListener(this);
		mouseState = "OFF";
		keyCode = new HashSet<Integer>();
		gameObjects = new HashSet<GameObject>();
		gameObjects.add(new TestButton());
		gameObjects.add(new GameInputText(110,300,100,30));
		lastPressed = ' ';
		focus = null;
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		update();
		render(drawable);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		try {
	         t = TextureIO.newTexture(this.getClass().getResource("pobrane.jpg"), false, ".jpg"); 
	         texture= t.getTextureObject(gl);
		} catch (GLException | IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub

	}

	public void play() {
	}

	public void update() {
	}

	public void render(GLAutoDrawable drawable) {
        drawable.getGL().glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		final GL2 gl = drawable.getGL().getGL2();
        gl.glEnable(GL2.GL_TEXTURE_2D);
        t.enable(gl);
        t.bind(gl);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0.5f, 0.5f);gl.glVertex2d(0.0, 0.0);
		gl.glTexCoord2f(0.5f, 1.0f);gl.glVertex2d(0.0, 0.9);
		gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex2d(0.9, 0.9);
		gl.glTexCoord2f(1.0f, 0.5f);gl.glVertex2d(0.9, 0.0);
        gl.glEnd();
        gl.glDisable(GL2.GL_TEXTURE_2D);
        
		for (GameObject o:gameObjects) o.draw(drawable);
		TextRenderer textRenderer = new TextRenderer(new Font("Verdana", Font.CENTER_BASELINE, 16));
		textRenderer.setColor(Color.WHITE);
		textRenderer.setSmoothing(true);
		textRenderer.beginRendering(800,600);
		textRenderer.draw(mouseX + ":" + (600 - mouseY), 50, 50);
		textRenderer.draw(mouseState, 50, 30);
		String keyList = "";
		int c = 1;
		for(int i:keyCode) {
			textRenderer.draw(i + " " + String.valueOf(Character.toChars(i)), 50*(c++), 10);
		}
		textRenderer.draw("" + lastPressed, 50, 70);
		textRenderer.endRendering();
	}
	
	public static float relOX(int x){
		return (float) x/800*2-1;
	}
	
	public static float relOY(int y){
		return (float) y/600*2-1;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		for (GameObject o:gameObjects){
			if(o.hover(mouseX, 600-mouseY)) break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		for (GameObject o:gameObjects){
			if(o.hover(mouseX, 600-mouseY)){
				if (o.focusable()){
					if(focus!=null) focus.offFocus();
					focus = o;
					focus.onFocus();
				}
				o.onClick();
				return;
			}
		}
		if(focus!=null) focus.offFocus();
		focus = null;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		mouseState = "ON " + arg0.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mouseState = "OFF";
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyCode.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e){
		keyCode.remove(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		lastPressed = e.getKeyChar();
		if(focus != null)
			focus.keyTyped(e);
	}

}
