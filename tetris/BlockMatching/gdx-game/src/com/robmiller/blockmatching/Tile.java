package com.robmiller.blockmatching;
import com.badlogic.gdx.graphics.g2d.*;
import android.speech.tts.*;
import com.badlogic.gdx.scenes.scene2d.*;

public class Tile extends Actor
{
	Point bottomLeft;
	private Utils.Colors color;
	private Sprite tileSprite;
	
	public Tile() {
		color = Utils.Colors.NULL;
		tileSprite = color.getSprite();
	}
	
	public Tile(Point pos, Utils.Colors col){
		bottomLeft = pos;
		color = col;
		tileSprite = col.getSprite();
	}
	
	@Override
	public void draw(Batch batch, float alpha){
		tileSprite.setPosition(bottomLeft.X, bottomLeft.Y);
		tileSprite.setSize(Utils.getTileSize(), Utils.getTileSize());
		tileSprite.draw(batch);
	}
	
	public void changeColor(Utils.Colors newCol){
		color = newCol;
		tileSprite = color.getSprite();
	}
	
	public Utils.Colors getTileColor(){
		return color;
	}
	
	public boolean isOccupied(){
		return color != Utils.Colors.NULL;
	}
}
