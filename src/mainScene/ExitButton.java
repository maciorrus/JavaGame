package mainScene;

import game.GameButton;

public class ExitButton extends GameButton {

	public ExitButton() {
		super(300,230,200,40,"Exit");
		
	}

	@Override
	public void onClick() {
		System.exit(0);
	}

}
