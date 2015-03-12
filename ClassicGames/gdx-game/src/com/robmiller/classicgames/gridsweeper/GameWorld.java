package com.robmiller.classicgames.gridsweeper;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import java.util.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import android.util.*;

public class GameWorld extends Stage
{
	private Player player;
	private Board board;
	private Viewport viewport;
	private Camera camera;
	private boolean isGameOver = false;
	private Image gameOverOverlay;
	
	public GameWorld(){
		//reverse width and height, because this game will be in landscape
		camera = new OrthographicCamera(Gdx.graphics.getHeight(),Gdx.graphics.getWidth());
		viewport = new ScreenViewport(camera);
		setViewport(viewport);
		
		board = new Board(this, GSUtils.getBoardBottomLeft(),2);
		board.updateSize();
		
		player = new Player(board);

		gameOverOverlay = new Image(new Texture(Gdx.files.internal("gameoveroverlay.png")));
		gameOverOverlay.setVisible(false);
		
		addActor(board);
		addActor(gameOverOverlay);
	}
	
	public void setGameOver() {
		isGameOver = true;	
		gameOverOverlay.setVisible(true);
	}
	
	public void toggleFlags() {
		board.toggleFlags();
	}
	
	public void restart() {
		board = new Board(this, GSUtils.getBoardBottomLeft(),2);
		board.updateSize();
		player.setBoard(board);
		addActor(board);
		gameOverOverlay.setVisible(false);
		isGameOver = false;
	}
	
	public void dispose(){
		super.dispose();
	}
	
	public void resize(int width, int height){
		getViewport().update(width,height,true);
		camera.translate(0,height,0);
		board.updateSize();
		gameOverOverlay.setBounds(GSUtils.getBoardBottomLeft().X, 
								GSUtils.getBoardBottomLeft().Y + GSUtils.getBoardHeight() - board.getActualHeight(),
								GSUtils.getBoardWidth(), 
								board.getActualHeight());
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
		if (isGameOver) return true;
		
		Vector3 v = new Vector3();
		camera.unproject(v, x, y, getViewport().getScreenWidth(), getViewport().getScreenHeight());
		player.touchUp(-(int)v.x, (int)v.y, pointer, button);
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer)
	{
		player.touchDragged(x,y,pointer);
		return true;
	}
}
