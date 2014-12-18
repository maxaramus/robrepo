package com.robmiller.blockmatching;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;

public class BlockMatchingGame implements ApplicationListener
{
	Texture background;
	Board board;
	SpriteBatch batch;
	Viewport viewport;
	Camera camera;

	@Override
	public void create()
	{
		background = Utils.getBgTexture();
		batch = new SpriteBatch();
		camera = new PerspectiveCamera();
		viewport = new FitViewport(1080, 1920, camera);
		board = new Board(Utils.getTopLeft());
		
		Utils.recalcSizes();
	}

	@Override
	public void render()
	{        
	    Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//Draw background
		batch.draw(background, 0, 0, 
				   Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		board.draw(batch);
		batch.end();
	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void resize(int width, int height)
	{
		Utils.recalcSizes();
		viewport.update(width, height);
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
}
