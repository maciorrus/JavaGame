package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.awt.TextRenderer;

import engine.Engine;

public class GameInputText extends GameObject {
	
	private String text;
	private TextRenderer textRenderer;
	private Color color;

	public GameInputText(int xx, int yy, int w, int h) {
		super(xx, yy, w, h);
		text = "Hello";
		color = Color.BLUE;
	}

	@Override
	public void draw(final GL2 gl) {
		float col;
		if(focused) col = 0f;
		else col = 1f;
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3f(0f,col,1f);
		gl.glVertex2f(Engine.relOX(this.getX()),Engine.relOY(this.getY()));
		gl.glVertex2f(Engine.relOX(this.getX()),Engine.relOY(this.getY() + this.getH()));
		gl.glEnd();
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2f(Engine.relOX(this.getX()),Engine.relOY(this.getY() + this.getH()));
		gl.glVertex2f(Engine.relOX(this.getX() + this.getW()),Engine.relOY(this.getY() + this.getH()));
		gl.glEnd();
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2f(Engine.relOX(this.getX() + this.getW()),Engine.relOY(this.getY() + this.getH()));
		gl.glVertex2f(Engine.relOX(this.getX() + this.getW()),Engine.relOY(this.getY()));
		gl.glEnd();
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2f(Engine.relOX(this.getX() + this.getW()),Engine.relOY(this.getY()));
		gl.glVertex2f(Engine.relOX(this.getX()),Engine.relOY(this.getY()));
		gl.glEnd();

		textRenderer = new TextRenderer(new Font("Verdana", Font.CENTER_BASELINE, 16));
		textRenderer.setColor(color);
		textRenderer.setSmoothing(true);
		textRenderer.beginRendering(800,600);
		textRenderer.draw(text, this.getX()+10, this.getY()+10);
		textRenderer.endRendering();
	}
	
	


	@Override
	public void keyTyped(KeyEvent e){
		if(e.getKeyChar() == 8){
			if(text.length()>0)
				text = text.substring(0,text.length()-1);
		}else{
			if(text.length()<this.getW()/11)
				text = text + e.getKeyChar();
			
	}
	}
	
	@Override
	public boolean focusable() {
		return true;
	}

	@Override
	public void onhover() {
		color = Color.MAGENTA;
	}

	@Override
	public void offhover() {
		color = Color.BLUE;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub

	}

}
