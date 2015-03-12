package com.robmiller.classicgames;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
//import android.graphics.drawable.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class ScoreBox extends Group
{
	private Texture texture;
	private Sprite sprite;
	private Image digitOnes;
	private Image digitTens;
	private Image digitHundreds;
	private Image digitThousands;
	private Image digitTenThousands;
	private Image digitHundredThousands;
	private Table table;
	
	public ScoreBox() {
		texture = new Texture(Gdx.files.internal("uibox.png"));
		sprite = new Sprite(texture);
		digitOnes = new Image(new Texture(Gdx.files.internal("number0.png")));
		digitTens = new Image(new Texture(Gdx.files.internal("number0.png")));
		digitHundreds = new Image(new Texture(Gdx.files.internal("number0.png")));
		digitThousands = new Image(new Texture(Gdx.files.internal("number0.png")));
		digitTenThousands = new Image(new Texture(Gdx.files.internal("number0.png")));
		digitHundredThousands = new Image(new Texture(Gdx.files.internal("number0.png")));
		table = new Table();
		table.pad(10,10,10,10);	
		table.add(digitHundredThousands).expand().fill();
		table.add(digitTenThousands).expand().fill();
		table.add(digitThousands).expand().fill();
		table.add(digitHundreds).expand().fill();
		table.add(digitTens).expand().fill();
		table.add(digitOnes).expand().fill();
		
		table.setFillParent(true);
	
		addActor(table);
	}
	
	public void setScore(int score) {
		String file = "number";		
		
		int numDigits = String.valueOf(score).length();

		digitOnes.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal(file + (score % 10) + ".png")))));

		if (numDigits >= 2) {
			digitTens.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal(file + ((score / 10) % 10) + ".png")))));
			digitTens.setVisible(true);
		}
		else {
			digitTens.setVisible(false);
		}
		
		if (numDigits >= 3) {
			digitHundreds.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal(file + ((score / 100) % 10) + ".png")))));
			digitHundreds.setVisible(true);
		}
		else {
			digitHundreds.setVisible(false);
		}
		
		if (numDigits >= 4) {
			digitThousands.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal(file + ((score / 1000) % 10) + ".png")))));
			digitThousands.setVisible(true);
		}
		else {
			digitThousands.setVisible(false);
		}
		
		if (numDigits >= 5) {
			digitTenThousands.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal(file + ((score / 10000) % 10) + ".png")))));
			digitTenThousands.setVisible(true);
		}
		else {
			digitTenThousands.setVisible(false);
		}
		
		if (numDigits >= 6) {
			digitHundredThousands.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal(file + ((score / 100000) % 10) + ".png")))));
			digitHundredThousands.setVisible(true);
		}
		else {
			digitHundredThousands.setVisible(false);
		}
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		super.draw(batch,alpha);
	}
}
