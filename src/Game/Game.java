package Game;

import java.util.ArrayList;
import java.util.List;

import GameCore.Keys;
import Scenes.Loader;
import GameCore.GameView;

public class Game {
	
	private Keys controls;
	private List<GameView> scenes;

	public static void main(String[] args) {
		Game game = new Game();
	}
	
	public Game() {
		controls = new Keys(this);
		scenes = new ArrayList<GameView>();
		scenes.add(new Loader(controls));
		controls.setCurrentScene(scenes.get(0));
		controls.run();		
	}
	
	
}
