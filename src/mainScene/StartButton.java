package mainScene;

import PlayScene.PlayScene;
import engine.Engine;
import game.GameButton;

public class StartButton extends GameButton {
	
	private ExitButton exitButton;
	private Engine e;

	public StartButton(ExitButton exit,Engine engine) {
		super(300,280,200,40,"Start");
		visible = true;
		exitButton = exit;
		e = engine;
	}

	@Override
	public void onClick() {
		e.scene = e.sceneList.get(1);
	}

}
