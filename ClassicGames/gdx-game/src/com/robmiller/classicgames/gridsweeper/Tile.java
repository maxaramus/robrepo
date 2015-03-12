package com.robmiller.classicgames.gridsweeper;

import com.badlogic.gdx.graphics.g2d.*;
//import android.speech.tts.*;
import com.badlogic.gdx.scenes.scene2d.*;
import android.util.*;

public class Tile extends Actor
{
	private GSUtils.Point bottomLeft;
	private GSUtils.TileStates state;
	private Sprite tileSprite;
	public int xIndex;
	public int yIndex;
	public boolean isMine = false;
	public boolean isFlagged = false;
	
	public Tile(GSUtils.Point pos){
		bottomLeft = pos;
		state = GSUtils.TileStates.HIDDEN;
		tileSprite = new Sprite(state.getTexture());
	}	
	
	public Sprite getSprite() {
		return tileSprite;
	}
	
	@Override
	public void draw(Batch batch, float alpha){
		tileSprite.setPosition(bottomLeft.X, bottomLeft.Y);
		tileSprite.setSize(GSUtils.getTileSize(), GSUtils.getTileSize());
		tileSprite.draw(batch);
	}
	
	public void setState(GSUtils.TileStates newState) {
		state = newState;
		tileSprite.setTexture(state.getTexture());
	}
	
	public void setBottomLeft(GSUtils.Point p) {
		bottomLeft.X = p.X;
		bottomLeft.Y = p.Y;
	}
	
	public GSUtils.Point getBottomLeft() {
		return bottomLeft;	
	}
	
	public GSUtils.TileStates getState() {
		return state;
	}
	
	public boolean containsPoint(GSUtils.Point p) {
		if (p.X < bottomLeft.X ||
			p.Y < bottomLeft.Y ||
			p.X > bottomLeft.X + GSUtils.getTileSize() ||
			p.Y > bottomLeft.Y + GSUtils.getTileSize())
			return false;
		else
			return true;
	}
}
