package com.robmiller.classicgames.blockmatching;

import com.badlogic.gdx.*;
import java.util.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class GameScreen extends BMScreen
{
	public static final int MAX_LEVEL = 10;
	
	private Texture background;
	private GameWorld world;
	private GameUI ui;
	private SpriteBatch batch;
	private InputMultiplexer multiInput;
	private boolean shouldGoToMenu = false;
	private int level = 1;
	private int score = 0;
	private boolean isPaused = false;
	private boolean isGameOver = false;
	
	public GameScreen(Game g){
		super(g);
		setPrevious(g.getScreen());
	}
	
	public void setNextPiece(Class c) {
		ui.setNext(c);
	}
	
	@Override
	public void updateScore(int s) {
		score += s;	
		ui.updateScore(score);
		level = 1 + score / 1500;
		;
		if (level > MAX_LEVEL) level = MAX_LEVEL;
	}
	
	@Override
	public void gameOver() {
		isGameOver = true;	
	}
	
	public int getLevel(){
		return level;
	}
	
	@Override
	public void render(float deltaTime)
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		//Draw background
		batch.draw(background, 0, 0, 
				   Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		
		if (!isPaused)
			world.act(deltaTime);
			
		world.draw();
		
		ui.act(deltaTime);
		ui.draw();

		if (shouldGoToMenu) {
			shouldGoToMenu = false;
			game.setScreen(new MainMenuScreen(game));
		}
		
		if (isGameOver) {
			isGameOver = false;
			//game.setScreen(new GameOverScreen(game));
			game.setScreen(new MainMenuScreen(game)); 				
		}
	}

	@Override
	public void show()
	{
		Utils.recalcSizes(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background = new Texture(Gdx.files.internal("bmbackground.png"));
		batch = new SpriteBatch();
		world = new GameWorld();
		ui = new GameUI(this);
		
		multiInput = new InputMultiplexer();

		multiInput.addProcessor(ui);
		multiInput.addProcessor(world);
		Gdx.input.setInputProcessor(multiInput); 
	}
	
	@Override
	public void buttonPressed(Utils.Buttons button) {
		switch(button){
			case HOLD:
				ui.setHeld(world.holdPressed());
				break;
			case MENU:
				shouldGoToMenu = true;
				break;
			case PAUSE:
				isPaused = !isPaused;
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
		Utils.recalcSizes(width,height);
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
