package com.robmiller.blockmatching;
import com.badlogic.gdx.graphics.g2d.*;
import android.graphics.*;

public class Player
{
	private final int DRAG_THRESHOLD = 72;
	private Board board;
	private float updateTime;
	private float timeSinceLastUpdate = 0;
	private boolean wasDragged = false;
	private boolean isDown = false;
	private Point lastTouch;
	private int linesCleared = 0;
	
	public Player(){
		board = new Board(Utils.getTopLeft(),this);
		updateTime = 0.5f;
	}
	
	public void draw(SpriteBatch batch){
		board.draw(batch);
	}
	
	public void update(float deltaTime){
		timeSinceLastUpdate += deltaTime;
		
		if(timeSinceLastUpdate >= updateTime){
			board.update();
			timeSinceLastUpdate = 0;
		}
	}
	
	public void holdPressed(){
		
	}
	
	public void incrementLinesCleared(int n){
		linesCleared += n;
	}
	
	public void touchDown(int x, int y, int pointer, int button)
	{
		isDown = true;
		lastTouch = new Point(x,y);
	}

	public void touchUp(int x, int y, int pointer, int button)
	{
		if (isDown && !wasDragged)
			board.rotatePiece();
			
		isDown = false;
		wasDragged = false;
	}

	public void touchDragged(int x, int y, int pointer)
	{
		if (Math.abs(x - lastTouch.X) > DRAG_THRESHOLD){
			if (x > lastTouch.X)
				board.pushRight();
			else
				board.pushLeft();
			
			lastTouch = new Point(x,y);
			wasDragged = true;
		}
		
		if (y - lastTouch.Y > DRAG_THRESHOLD){
			board.pushDown();
			lastTouch = new Point(x,y);
			wasDragged = true;
		}
	}
}
