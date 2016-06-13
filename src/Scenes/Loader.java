package Scenes;

import javax.swing.ImageIcon;

import Game.StartButton;
import GameCore.Core;
import GameCore.GameButton;
import GameCore.GameView;

public class Loader extends GameView{
	
	
	public Loader(Core app){
		super("Loader", null , app);
		background = new ImageIcon(getClass().getResource("/images/back/mainBackground.jpg").getFile()).getImage();
		objectList.add(new StartButton( getClass().getResource("/images/startCiemny.png").getFile(), getClass().getResource("/images/startJasny.png").getFile(), getClass().getResource("/images/startOn.png").getFile(),this));
		objectList.add(new GameButton("ExitButton", 450, 350, 100, 25, getClass().getResource("/images/exitCiemny.png").getFile(), getClass().getResource("/images/exitJasny.png").getFile(), getClass().getResource("/images/exitOn.png").getFile(),this));
	}

	@Override
	public void onClick() {
		
	}
}
