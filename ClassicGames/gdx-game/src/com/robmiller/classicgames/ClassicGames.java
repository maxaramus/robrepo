package com.robmiller.classicgames;

import com.badlogic.gdx.*;

public class ClassicGames extends Game
{
	public ClassicGames(ScreenOrientator s) {
		Utils.setScreenOrientator(s);	
	}
	
	@Override
	public void create()
	{
		setScreen(new MainMenuScreen(this));
		//setScreen(new GameScreen());
	}	
}
