import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public abstract class gameView extends GameObject{


	private Image background;
	protected ArrayList<GameObject> objectList;
	public Core application;
	
	public gameView(String url, Core app){
		super(0,0,1920,1080);
		application = app;
		this.background = new ImageIcon(url).getImage();
		objectList = new ArrayList<GameObject>();
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.drawImage(this.background, 0 , 0 , 1920, 1080, null);
		int counter = 0;
		while(counter<objectList.size()){
			objectList.get(counter).draw(g);
			counter++;
		}
		
	}

	@Override
	public boolean onHover(int x, int y, int state){

		int counter = 0;
		while(counter<objectList.size()){
			if(objectList.get(counter).onHover(x, y, state)){
				if ((state == 0)&&(objectList.get(counter).state==3)){
					objectList.get(counter).state = 1;
					objectList.get(counter).onClick();
				}
			}
			
			counter++;
		}
		return false;
		
	}
}
