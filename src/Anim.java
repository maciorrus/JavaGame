import java.awt.Image;
import java.util.ArrayList;

public class Anim {
	
	private ArrayList<OneScene> scenes;
	private int index;
	private long movieTime;
	private long totalTime;
	
	public Anim(){
		
		scenes 		= new ArrayList<OneScene>();
		index 		= 0;
		movieTime 	= 0;
		totalTime 	= 0;
		start();
		
	}

	public synchronized void addScene(Image im, long time){
		
		totalTime +=time;
		scenes.add(new OneScene(im, totalTime));
		
	}
	
	public synchronized void start(){
		
		movieTime 	= 0;
		index 		= 0;
		
	}
	
	public synchronized void update(long time){
		
		if(scenes.size()>1){
			movieTime += time;
			if (movieTime >= totalTime){
				movieTime 	= 0;
				index		= 0;
			}
			while(movieTime > getScene(index).endTime){
				index++;
			}
		}
		
	}
	
	public synchronized Image getImage(){
		if(scenes.size()==0) return null;
		else return getScene(index).pic;
	}
	
	private OneScene getScene(int x){
		return (OneScene)scenes.get(x);
	}
	
	private class OneScene{
		Image pic;
		long endTime;
		
		public OneScene(Image pic, long endTime){
			this.pic = pic;
			this.endTime = endTime;
		}
	}
}
