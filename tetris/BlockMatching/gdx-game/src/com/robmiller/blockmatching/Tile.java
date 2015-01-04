package com.robmiller.blockmatching;
import com.badlogic.gdx.graphics.g2d.*;

public class Tile
{
	Point topLeft;
	boolean isOccupied;
	Utils.Colors color;
	int tileWidth;
	Board myBoard;
	
	public Tile(Board board, Point pos, Utils.Colors col){
		topLeft = pos;
		color = col;
		myBoard = board;
	}
	
	public void draw(SpriteBatch batch){
		if(color.getSprite() != null){
			Sprite tileSprite = color.getSprite();
			//tileSprite.setSize(Utils.getTileWidth(),Utils.getTileHeight());
			tileSprite.setPosition(topLeft.X, topLeft.Y - color.getSprite().getHeight());
			tileSprite.draw(batch);
		}
	}
}
