package mainScene;

import game.GameButton;

public class StartButton extends GameButton {
	
	private ExitButton exitButton;

	public StartButton(ExitButton exit) {
		super(300,280,200,40,"Start");
		visible = true;
		exitButton = exit;
	}

	@Override
	public void onClick() {
		exitButton.show();
		this.hide();
		System.out.println("Let's play!");
	}

}
