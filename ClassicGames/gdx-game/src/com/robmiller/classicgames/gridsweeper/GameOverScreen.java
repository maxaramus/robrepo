package com.robmiller.classicgames.gridsweeper;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.robmiller.classicgames.*;

public class GameOverScreen extends CGScreen implements InputProcessor
{
	private Texture background;
	private SpriteBatch batch;
	private InputMultiplexer multiInput;
	private Stage stage;
	
	public GameOverScreen(Game g) {
		super(g);
	}
	
	@Override
	public void show()
	{
		GSUtils.recalcSizes(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background = new Texture(Gdx.files.internal("bmbackground.png"));
		batch = new SpriteBatch();
		stage = new Stage();
		
		multiInput = new InputMultiplexer();
		multiInput.addProcessor(stage);
		multiInput.addProcessor(this);
		Gdx.input.setInputProcessor(multiInput); 
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
	}

	@Override
	public boolean keyDown(int p1)
	{
		return false;
	}

	@Override
	public boolean keyUp(int p1)
	{
		return false;
	}

	@Override
	public boolean keyTyped(char p1)
	{
		return false;
	}

	@Override
	public boolean touchDown(int p1, int p2, int p3, int p4)
	{
		game.setScreen(new MainMenuScreen(game));
		return false;
	}

	@Override
	public boolean touchUp(int p1, int p2, int p3, int p4)
	{
		return false;
	}

	@Override
	public boolean touchDragged(int p1, int p2, int p3)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(int p1, int p2)
	{
		return false;
	}

	@Override
	public boolean scrolled(int p1)
	{
		return false;
	}
	
}
