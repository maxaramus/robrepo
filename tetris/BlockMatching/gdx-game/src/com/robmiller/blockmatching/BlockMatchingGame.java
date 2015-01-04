package com.robmiller.blockmatching;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import java.util.*;
import android.util.*;

public class BlockMatchingGame implements ApplicationListener, InputProcessor
{

	@Override
	public boolean keyDown(int p1)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean keyUp(int p1)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean keyTyped(char p1)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean touchDown(int p1, int p2, int p3, int p4)
	{
		board.rotatePiece();
		return true;
	}

	@Override
	public boolean touchUp(int p1, int p2, int p3, int p4)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean touchDragged(int p1, int p2, int p3)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean mouseMoved(int p1, int p2)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean scrolled(int p1)
	{
		// TODO: Implement this method
		return false;
	}

	Texture background;
	Board board;
	SpriteBatch batch;
	Viewport viewport;
	Camera camera;
	
	class TouchInfo { 
		public float touchX = 0; 
		public float touchY = 0; 
		public boolean touched = false; 
	} 
	
	private Map touches = new HashMap<Integer,TouchInfo>();
	
	@Override
	public void create()
	{
		Utils.recalcSizes();
		background = Utils.getBgTexture();
		batch = new SpriteBatch();
		camera = new PerspectiveCamera();
		viewport = new FitViewport(1080, 1920, camera);
		board = new Board(Utils.getTopLeft());
		
		Gdx.input.setInputProcessor(this); 
		//for(int i = 0; i < 5; i++){ 
			touches.put(0,new TouchInfo()); 
		//}
	}

	@Override
	public void render()
	{        
	    Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//Draw background
		batch.draw(background, 0, 0, 
				background.getWidth(), background.getHeight());
				   //Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
