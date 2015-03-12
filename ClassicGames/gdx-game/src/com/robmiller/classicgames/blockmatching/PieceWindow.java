package com.robmiller.classicgames.blockmatching;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;

public class PieceWindow extends Actor
{
	private Texture windowTex;
	private Sprite piece;
	
	public PieceWindow() {
		windowTex = new Texture(Gdx.files.internal("uiwindow.png"));
	}
	
	public void setPiece(BMUtils.Shapes shape) {
		piece = shape.getSprite();
		piece.setBounds(getX(),getY(),getWidth(),getHeight());
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(windowTex, getX(), getY(), getWidth(), getHeight());
		
		if (piece != null)
			piece.draw(batch);
	}
}
