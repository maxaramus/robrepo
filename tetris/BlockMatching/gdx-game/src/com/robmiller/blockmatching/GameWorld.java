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
import com.badlogic.gdx.graphics.glutils.*;

public class GameWorld extends Stage
{
	private Player player;
	private Board board;
	private Viewport viewport;
	private Camera camera;
	//private Map touches = new HashMap<Integer,TouchInfo>();
	
	public GameWorld(){
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		viewport = new ScreenViewport(camera);
		setViewport(viewport);
		
		board = new Board(Utils.getBoardBottomLeft());
		player = new Player(board);

		addActor(board);
		//touches.put(0,new TouchInfo()); 
	}
	
	public Class holdPressed() {
		return board.holdSwapPiece();	
	}
	
	public void dispose(){
		super.dispose();
		Utils.dispose();
	}
	
	public void resize(int width, int height){
		getViewport().update(width,height,true);
	}
	
			//@Override
	//public void draw(){
	//	super.draw();
		//board.draw();
	//}
	
	//@Override
	//public void act(float delta){
	//	super.act(delta);
	//}
	
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
