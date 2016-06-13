package GameCore;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Window;


public abstract class Core {

	public static DisplayMode modes[] = {
		new DisplayMode(1366, 768, 32, 0),
		new DisplayMode(1366, 768, 24, 0),
		new DisplayMode(1366, 768, 16, 0)
	};
	
	private boolean running;
	
	protected ScreenManager s;
	
	public void stop(){
		running = false;
	}
	
	public void run(){
		try{
			init();
			gameLoop();
		}finally{
			s.restore();
		}
	}
	
	public void init(){
		s = new ScreenManager();
		DisplayMode dm = s.findCM(modes);
		s.setFullScreen(dm);
		
		Window w = s.getFullScreenWindow();
		w.setFont(new Font("Arial", Font.PLAIN, 24));
		w.setBackground(Color.black);
		w.setForeground(Color.white);
		running = true;
		
	}
	
	public void gameLoop(){
		long startTime = System.currentTimeMillis();
		long curTime = startTime;
		
		while (running){
			long timePassed = System.currentTimeMillis() - curTime;
			curTime +=timePassed;
			update(timePassed);
			
			Graphics2D g = s.getGraphics();
			draw(g);
			g.dispose();
			s.update();
			
			try{
				Thread.sleep(20);
			}catch (Exception e) {
			}
		}
	}
	
	public void update(long timePassed){
		
	}
	
	public abstract void draw(Graphics2D g);
	
	
}
