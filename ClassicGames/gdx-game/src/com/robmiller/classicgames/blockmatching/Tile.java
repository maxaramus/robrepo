package com.robmiller.classicgames.blockmatching;
import com.badlogic.gdx.graphics.g2d.*;
//import android.speech.tts.*;
import com.badlogic.gdx.scenes.scene2d.*;

public class Tile extends Actor
{
	BMUtils.Point bottomLeft;
	private BMUtils.Colors color;
	private Sprite tileSprite;
	
	public Tile() {
		color = BMUtils.Colors.NULL;
		tileSprite = color.getSprite();
	}
	
	public Tile(BMUtils.Point pos, BMUtils.Colors col){
		bottomLeft = pos;
		color = col;
		tileSprite = col.getSprite();
	}
	
	@Override
	public void draw(Batch batch, float alpha){
		tileSprite.setPosition(bottomLeft.X, bottomLeft.Y);
		tileSprite.setSize(BMUtils.getTileSize(), BMUtils.getTileSize());
		tileSprite.draw(batch);
	}
	
	public void changeColor(BMUtils.Colors newCol){
		color = newCol;
		tileSprite = color.getSprite();
	}
	
	public BMUtils.Colors getTileColor(){
		return color;
	}
	
	public boolean isOccupied(){
		return color != BMUtils.Colors.NULL;
	}
}
