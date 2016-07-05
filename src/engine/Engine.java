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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import game.GameButton;

public class Engine extends JFrame implements GLEventListener, MouseMotionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 8819738880472328756L;
	final private int width = 800;
	final private int height = 600;
	private String mouseState;
	private Set<Integer> keyCode;
	char lastPressed;
	private int mouseX, mouseY;
	private Scene scene;
	public List<Scene> sceneList;
	private static TextRenderer defaultTextRenderer;

	public Engine() {
		super("Minimal OpenGL");
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		GLCanvas canvas = new GLCanvas(capabilities);
		canvas.addGLEventListener(this);
		defaultTextRenderer = new TextRenderer(new Font("Verdana", Font.CENTER_BASELINE, 16));

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
		lastPressed = ' ';
		scene = new MainScene(keyCode, this);
		sceneList = new ArrayList<Scene>();
		sceneList.add(scene);
		
		FPSAnimator anim = new FPSAnimator(canvas,60);
		anim.start();
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
		scene.init(drawable);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub

	}

	public void play() {
	}

	public void update() {
		scene.update();
	}

	public void render(GLAutoDrawable drawable) {
        drawable.getGL().glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		final GL2 gl = drawable.getGL().getGL2();
        
		TextRenderer textRenderer = new TextRenderer(new Font("Verdana", Font.CENTER_BASELINE, 16));
		textRenderer.setColor(Color.WHITE);
		textRenderer.setSmoothing(true);
		textRenderer.beginRendering(800,600);
		textRenderer.draw(mouseX + ":" + (600 - mouseY), 50, 50);
		textRenderer.draw(mouseState, 50, 30);
		int c = 1;
		for(int i:keyCode) {
			textRenderer.draw(i + " " + String.valueOf(Character.toChars(i)), 50*(c++), 10);
		}
		textRenderer.draw("" + lastPressed, 50, 70);
		textRenderer.endRendering();
		scene.render(gl);
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
		if(scene != null) scene.mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		scene.mouseClicked(arg0);
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
		scene.keyTyped(e);
	}
	
	public static void drawRectangle(GL2 gl,int x, int y, int xx, int yy, float r, float g, float b){
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(r,g,b);
		gl.glVertex2f(relOX(x),relOY(y));
		gl.glVertex2f(relOX(x),relOY(y + yy));
		gl.glVertex2f(relOX(x + xx),relOY(y + yy));
		gl.glVertex2f(relOX(x + xx),relOY(y));
		gl.glEnd();
	}
	
	public static void outText(GL2 gl,int x, int y, Color c, String s){
	defaultTextRenderer.setColor(Color.BLACK);
	defaultTextRenderer.setSmoothing(true);
	defaultTextRenderer.beginRendering(800,600);
	int textWidth = (int) defaultTextRenderer.getBounds(s.replace(" ","_")).getWidth();
	int textHeight = (int) defaultTextRenderer.getBounds(s).getHeight();
	defaultTextRenderer.draw(s, x - textWidth/2, y - textHeight/2);
	defaultTextRenderer.endRendering();

	}

}
