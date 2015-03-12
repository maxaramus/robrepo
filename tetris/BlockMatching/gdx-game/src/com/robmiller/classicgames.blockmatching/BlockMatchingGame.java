package com.robmiller.classicgames.blockmatching;

import com.badlogic.gdx.*;

public class BlockMatchingGame extends Game
{
	@Override
	public void create()
	{
		setScreen(new MainMenuScreen(this));
		//setScreen(new GameScreen());
	}	
}
