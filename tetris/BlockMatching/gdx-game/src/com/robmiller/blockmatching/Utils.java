package com.robmiller.blockmatching;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Utils
{
	private static float tileWidth;
	private static float tileHeight;
	private static Texture bgTexture = null;
	private static Point boardTopLeft;
	
	public static Point getTopLeft() {
		return boardTopLeft;
	}
	
	public static float getTileWidth(){
		return tileWidth;
	}
	
	public static float getTileHeight(){
		return tileHeight;
	}
	
	public static void recalcSizes() {
		float xPct = (float)Gdx.graphics.getWidth() / (float)bgTexture.getWidth();
		float yPct = (float)Gdx.graphics.getHeight()/ (float)bgTexture.getHeight();
		
		tileWidth = Colors.BLUE.getTexture().getWidth() * xPct;
		tileHeight = Colors.BLUE.getTexture().getHeight() * yPct;
		
		boardTopLeft.X = (int)(Gdx.graphics.getWidth() * 0.05);
		boardTopLeft.Y = (int)(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.05);
	}
	
	public static Texture getBgTexture(){
		if(bgTexture == null)
			bgTexture = new Texture(Gdx.files.internal("uiframe.png"));
			
		return bgTexture;
	}
	
	public static enum Colors{
		NULL(""),
		ORANGE("blockorange.png"),
		RED("blockred.png"),
		GREEN("blockgreen.png"),
		BLUE("blockblue.png"),
		TEAL("blockteal.png"),
		PURPLE("blockpurple.png"),
		YELLOW("blockyellow.png");
		
		private String textureHandle;
		private Texture texture;
		private Sprite sprite;
		
		private Colors(String tex){
			textureHandle = tex;
			
			if(textureHandle != ""){
				texture = new Texture(Gdx.files.internal(textureHandle));
				sprite = new Sprite(texture);
			}
		}
		public String getTextureHandle(){
			return textureHandle;
		}
		
		public Texture getTexture(){
			return texture;
		}
		
		public Sprite getSprite(){
			return sprite;
		}
	}
}
