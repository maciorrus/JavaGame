package GameCore;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public abstract class GameView extends GameObject{


	protected Image background;
	public ArrayList<GameObject> objectList;
	public Core application;
	
	public GameView(String s,String url, Core app){
		super(s,0,0,Core.modes[0].getWidth(),Core.modes[0].getHeight());
		application = app;
		this.background = new ImageIcon(url).getImage();
		objectList = new ArrayList<GameObject>();
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.drawImage(this.background, 0 , 0 , Core.modes[0].getWidth(), Core.modes[0].getHeight(), null);
		int counter = 0;
		while(counter<objectList.size()){
			objectList.get(counter).draw(g);
			counter++;
			if(objectList.get(counter).onHover(xpos, ypos, buttons))
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
