package com.robmiller.classicgames.gridsweeper;

import com.badlogic.gdx.graphics.g2d.*;
//import android.graphics.*;
import com.badlogic.gdx.*;

public class Player implements InputProcessor
{
	private final int DRAG_THRESHOLD = 72;
	private Board board;
	private boolean wasDragged = false;
	private boolean isDown = false;
	private GSUtils.Point lastTouch;
	private int linesCleared = 0;
	
	public Player(Board b){
		//board = new Board(Utils.getTopLeft(),this);
		board = b;
	}
	
	public void setBoard(Board b) {
		board = b;	
	}
	
	public void update(float deltaTime){
	}
	
	public void holdPressed(){
		
	}
	
	public void incrementLinesCleared(int n){
		linesCleared += n;
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
		isDown = true;
		lastTouch = new GSUtils.Point(x,y);
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button)
	{
		board.wasTouched(x,y);
		isDown = false;
		wasDragged = false;
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer)
	{
		if (lastTouch == null) return false;
		
		if (Math.abs(x - lastTouch.X) > DRAG_THRESHOLD){
			lastTouch = new GSUtils.Point(x,y);
			wasDragged = true;
		}

		if (y - lastTouch.Y > DRAG_THRESHOLD){
			lastTouch = new GSUtils.Point(x,y);
			wasDragged = true;
		}
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
