package game;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.awt.TextRenderer;

import engine.Engine;

public abstract class GameButton extends GameObject {

	private String name;
	private TextRenderer textRenderer;
	private float r,g,b;
	
	public GameButton(int x, int y, int xx, int yy, String n) {
		super(x,y,xx,yy);
		r = 0f;
		g = 1f;
		b = 0f;
		name = n;
		textRenderer = new TextRenderer(new Font("Verdana", Font.CENTER_BASELINE, 16));
		visible = false;
	}
	
	public void hide(){
		visible = false;
	}
	
	public void show(){
		visible = true;
	}

	@Override
	public void draw(final GL2 gl) {
		if(!visible) return;
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(r,g,b);
		gl.glVertex2f(Engine.relOX(this.getX()),Engine.relOY(this.getY()));
		gl.glVertex2f(Engine.relOX(this.getX()),Engine.relOY(this.getY() + this.getH()));
		gl.glVertex2f(Engine.relOX(this.getX() + this.getW()),Engine.relOY(this.getY() + this.getH()));
		gl.glVertex2f(Engine.relOX(this.getX() + this.getW()),Engine.relOY(this.getY()));
		gl.glEnd();


		textRenderer.setColor(Color.BLACK);
		textRenderer.setSmoothing(true);
		textRenderer.beginRendering(800,600);
		int textWidth = (int) textRenderer.getBounds(name.replace(" ","_")).getWidth();
		int textHeight = (int) textRenderer.getBounds(name).getHeight();
		textRenderer.draw(name, this.getX()+this.getW()/2-textWidth/2, this.getY()+this.getH()/2-textHeight/2);
		textRenderer.endRendering();
	}

	public abstract void onClick();

	@Override
	public void onhover() {
		g = 0f;
		b = 1f;
	}

	@Override
	public void offhover() {
		g = 1f;
		b = 0f;		
	}

}
