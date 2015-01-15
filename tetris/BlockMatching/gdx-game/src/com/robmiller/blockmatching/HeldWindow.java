package com.robmiller.blockmatching;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;

public class HeldWindow extends Actor
{
	private Texture windowTex;
	private Sprite current;
	
	public HeldWindow() {
		windowTex = new Texture(Gdx.files.internal("uiwindow.png"));
	}
	
	public void setCurrent(Utils.Shapes shape) {
		current = shape.getSprite();
		current.setBounds(getX(),getY(),getWidth(),getHeight());
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(windowTex, getX(), getY(), getWidth(), getHeight());
		
		if (current != null)
			current.draw(batch);
	}
}
