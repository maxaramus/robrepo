package com.robmiller.classicgames.gridsweeper;

import com.badlogic.gdx.*;
import java.util.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.robmiller.classicgames.*;
import com.badlogic.gdx.audio.*;

public class GameScreen extends CGScreen
{
	public static final int DIFFICULTY = 0;

	private Music theme;
	private Texture background;
	private GameWorld world;
	private GameUI ui;
	private SpriteBatch batch;
	private InputMultiplexer multiInput;
	private boolean shouldGoToMenu = false;
	private boolean shouldShowMenu = false;
	private boolean isPaused = false;
	private boolean isGameOver = false;
	private boolean shouldRestart = false;
	private boolean dropFlags = false;
	
	public GameScreen(Game g){
		super(g);
		setPrevious(g.getScreen());
	}
	
	@Override
	public void gameOver() {
		isGameOver = true;	
	}

	@Override
	public void render(float deltaTime)
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		//Draw background
		batch.draw(background, 0, 0, Gdx.graphics.getHeight() * 2, Gdx.graphics.getWidth());
		batch.end();

		if (!isPaused)
			world.act(deltaTime);

		world.draw();
		
		ui.act(deltaTime);
		ui.draw();

		if (shouldShowMenu) {
			shouldShowMenu = false;
			game.setScreen((Screen)(new MainMenuScreen(game)));
		}
		
		if (shouldGoToMenu) {
			shouldShowMenu = true;
			shouldGoToMenu = false;
			Utils.setScreenPortrait();
		}
		
		if (shouldRestart) {
			shouldRestart = false;
			world.restart();
		}
		
		if (isGameOver) {
			shouldShowMenu = true;
			isGameOver = false;
			Utils.setScreenPortrait();
			//game.setScreen(new GameOverScreen(game));
		}
	}

	@Override
	public void show()
	{
		GSUtils.recalcSizes(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background = new Texture(Gdx.files.internal("bmbackground.png"));
		batch = new SpriteBatch();
		world = new GameWorld();
		ui = new GameUI(this);

		multiInput = new InputMultiplexer();

		multiInput.addProcessor(ui);
		multiInput.addProcessor(world);
		Gdx.input.setInputProcessor(multiInput); 

		//theme = Gdx.audio.newMusic(Gdx.files.internal("blockmatchingtheme.ogg"));
		//theme.play();
	}

	@Override
	public void buttonPressed(GSUtils.Buttons button) {
		switch(button){
			case MENU:
				shouldGoToMenu = true;
				break;
			case NEWGAME:
				shouldRestart = true;
				break;
			case PAUSE:
				isPaused = !isPaused;
				break;
			case FLAG:
				world.toggleFlags();
				break;
			default:
				break;
		}	
	}

	@Override
	public void dispose()
	{
		super.dispose();
		background.dispose();
		world.dispose();
		ui.dispose();
	}

	@Override
	public void resize(int width, int height)
	{
		GSUtils.recalcSizes(width,height);
		world.resize(width,height);
		ui.resize(width,height);
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void hide()
	{
		dispose();
	}
}
