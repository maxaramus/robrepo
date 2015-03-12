package com.robmiller.classicgames.blockmatching;
import com.badlogic.gdx.graphics.g2d.*;
//import android.graphics.*;
import com.badlogic.gdx.*;

public class Player implements InputProcessor
{
	private final int DRAG_THRESHOLD = 72;
	private Board board;
	private boolean wasDragged = false;
	private boolean isDown = false;
	private BMUtils.Point lastTouch;
	private int linesCleared = 0;
	
	public Player(Board b){
		//board = new Board(Utils.getTopLeft(),this);
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
		lastTouch = new BMUtils.Point(x,y);
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button)
	{
		if (isDown && !wasDragged)
			board.rotatePiece();

		isDown = false;
		wasDragged = false;
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer)
	{
		if (lastTouch == null) return false;
		
		if (Math.abs(x - lastTouch.X) > DRAG_THRESHOLD){
			if (x > lastTouch.X)
				board.pushRight();
			else
				board.pushLeft();

			lastTouch = new BMUtils.Point(x,y);
			wasDragged = true;
		}

		if (y - lastTouch.Y > DRAG_THRESHOLD){
			board.pushDown();
			lastTouch = new BMUtils.Point(x,y);
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
