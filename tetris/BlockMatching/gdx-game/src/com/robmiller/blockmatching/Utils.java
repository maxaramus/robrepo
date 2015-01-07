package com.robmiller.blockmatching;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Utils
{
	public static enum Shapes{
		IBLOCK,
		LBLOCK,
		JBLOCK,
		TBLOCK,
		SBLOCK,
		QBLOCK,
		ZBLOCK
	}
	
	private static float tileWidth;
	private static float tileHeight;
	private static Texture bgTexture = null;
	private static Point boardTopLeft;
	private static float boardWidth;
	private static float boardHeight;
	
	public static Point getTopLeft() {
		return boardTopLeft;
	}
	
	public static float getBoardHeight(){
		return boardHeight;
	}
	
	public static float getBoardWidth(){
		return boardWidth;
	}
	
	public static float getTileWidth(){
		return tileWidth;
	}
	
	public static float getTileHeight(){
		return tileHeight;
	}
	
	public static void recalcSizes() {
		boardWidth = (float)Gdx.graphics.getWidth() * 0.665f;
		boardHeight=(float)Gdx.graphics.getHeight() * 0.774f;
		
		//tileWidth = boardWidth / 10;
		//tileHeight = boardHeight / 21;
		
		boardTopLeft = new Point(
			(int)(Gdx.graphics.getWidth() * 0.02),
			(int)(Gdx.graphics.getHeight() * 0.99));
			
		
	}
	
	public static void drawText(String s, Point p){
		SpriteBatch b = new SpriteBatch();
		BitmapFont font = new BitmapFont(); 
		font.setColor(Color.RED);
		
		b.begin();
		font.draw(b,s,p.X,p.Y);
		b.end();
	}
	
	public static void drawText(SpriteBatch b, String s, Point p){
		BitmapFont font = new BitmapFont(); 
		font.setColor(Color.RED);
		font.draw(b,s,p.X,p.Y);
	}
	
	public static Texture getBgTexture(){
		if(bgTexture == null)
			bgTexture = new Texture(Gdx.files.internal("bmbackground.png"));
			
		return bgTexture;
	}
	
	public static enum Colors{
		NULL("blocknull.png"),
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
