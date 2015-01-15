package com.robmiller.blockmatching;

import com.badlogic.gdx.*;
import java.util.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class GameScreen extends BMScreen
{
	private Texture background;
	private GameWorld world;
	private GameUI ui;
	private SpriteBatch batch;
	private InputMultiplexer multiInput;
	
	public GameScreen(Game g){
		super(g);
		setPrevious(g.getScreen());
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
		
		world.act(deltaTime);
		world.draw();
		
		ui.act(deltaTime);
		ui.draw();
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
			default:
				break;
		}	
	}
	
	@Override
	public void dispose()
	{
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
	}
}
