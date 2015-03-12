package com.robmiller.classicgames.blockmatching;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import java.util.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.graphics.glutils.*;

public class GameWorld extends Stage
{
	private Player player;
	private Board board;
	private Viewport viewport;
	private Camera camera;
	
	public GameWorld(){
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		viewport = new ScreenViewport(camera);
		setViewport(viewport);
		
		board = new Board(BMUtils.getBoardBottomLeft());
		player = new Player(board);

		addActor(board);
	}
	
	public Class holdPressed() {
		return board.holdSwapPiece();	
	}
	
	public void dispose(){
		super.dispose();
	}
	
	public void resize(int width, int height){
		getViewport().update(width,height,true);
		board.updateSize();
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
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer)
	{
		player.touchDragged(x,y,pointer);
		return true;
	}
}
