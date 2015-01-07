package com.robmiller.blockmatching;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import java.util.*;
import android.util.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.*;

public class BlockMatchingGame implements ApplicationListener, InputProcessor
{
	private Stage stage;
	private Texture background;
	private SpriteBatch batch;
	private Viewport viewport;
	private Camera camera;
	private Player player;
	private Map touches = new HashMap<Integer,TouchInfo>();
	TextButton holdButton;
	TextButton.TextButtonStyle holdStyle;
	
	@Override
	public void create()
	{
		stage = new Stage();
		Utils.recalcSizes();
		background = Utils.getBgTexture();
		batch = new SpriteBatch();
		player = new Player();
		
		Gdx.input.setInputProcessor(this); 
		touches.put(0,new TouchInfo()); 
		
		BitmapFont font = new BitmapFont();
		
		holdStyle = new TextButton.TextButtonStyle();
		holdStyle.font = font;
		holdStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("holdbuttonup.png"))));
		holdStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("holdbuttondown.png"))));
		holdButton = new TextButton("HOLD",holdStyle);
		holdButton.addListener(new ChangeListener() {
				@Override
				public void changed (ChangeEvent event, Actor actor) {
					player.holdPressed();
				}
			});
			
		stage.addActor(holdButton);
		
	}

	@Override
	public void render()
	{   
		player.update(Gdx.graphics.getDeltaTime());
		
	    Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//Draw background
		batch.draw(background, 0, 0, 
				   Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		player.draw(batch);
		stage.draw();
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
		//viewport.update(width, height);
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
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		player.touchDown(x,y,pointer,button);
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button)
	{
		player.touchUp(x,y,pointer,button);
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer)
	{
		player.touchDragged(x,y,pointer);
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
}
