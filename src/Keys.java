import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;

import java.awt.Toolkit;

public class Keys extends Core implements KeyListener, MouseInputListener{
	public static void main(String args[]){
		new Keys().run();
	}
	
	private Image im;
	private int x,y,state;
	private gameView loader;
	
	public void init(){
		super.init();
		//----------------  Inicjacja Ekranu --------------
		Window w = s.getFullScreenWindow();
		w.setFocusTraversalKeysEnabled(false);

		//----------------  Ustawienie niwidocznego kursora --------------
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT); 
		Point hotSpot = new Point(0,0);
		Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, hotSpot, "InvisibleCursor");        
		w.setCursor(invisibleCursor);
		im = new ImageIcon(getClass().getResource("/images/cursor.png").getFile()).getImage();
		loader = new Loader(this);
		
		//----------------  Inicjacja Eventów --------------
		w.addKeyListener(this);
		w.addMouseMotionListener(this);
		w.addMouseListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		e.consume();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE){
			stop();
		}else{
			e.consume();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		e.consume();
		
	}

	@Override
	public synchronized void draw(Graphics2D g) {
		Window w = s.getFullScreenWindow();
		g.setColor(w.getBackground());
		g.fillRect(0, 0, s.getWidth(), s.getHeight());
		loader.onHover(this.x, this.y, state); 
		loader.draw(g);
		g.drawImage(im, this.x, this.y, im.getWidth(null), im.getHeight(null), null);
		g.setColor(new Color(255,255,255));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.state = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.state = 0;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
