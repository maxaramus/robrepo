package com.robmiller.blockmatching;
import com.badlogic.gdx.graphics.g2d.*;
import android.speech.tts.*;

public class Tile
{
	Point topLeft;
	private Utils.Colors color;
	Board myBoard;
	private Sprite tileSprite;
	
	public Tile(Board board, Point pos, Utils.Colors col){
		topLeft = pos;
		color = col;
		myBoard = board;
		tileSprite = col.getSprite();
	}
	
	public void draw(SpriteBatch batch){
		tileSprite.setPosition(topLeft.X, topLeft.Y - tileSprite.getHeight());
		tileSprite.draw(batch);
	}
	
	public void changeColor(Utils.Colors newCol){
		color = newCol;
		tileSprite = color.getSprite();
	}
	
	public Utils.Colors getColor(){
		return color;
	}
	
	public boolean isOccupied(){
		return color != Utils.Colors.NULL;
	}
}
