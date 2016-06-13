package Game;

import GameCore.GameButton;
import GameCore.GameView;

public class StartButton extends GameButton {

	public StartButton(String s, String we, String o, GameView gv) {
		super("StartButton",450, 300, 100, 25, s,we,o,gv);
	}
	

	@Override
	public void onClick() {
		this.y++;
	}

}
