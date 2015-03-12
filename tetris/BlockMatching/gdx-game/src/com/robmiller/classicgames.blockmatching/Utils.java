package com.robmiller.classicgames.blockmatching;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import android.util.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class Utils
{
	public static enum Buttons{
		HOLD("HOLD"),
		MENU("MENU"),
		PAUSE("PAUSE"),
		RESUME("RESUME");
		
		private String str;
		
		private Buttons(String s) {
			str = s;	
		}
		
		public String getString() {
			return str;
		}
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
			texture = new Texture(Gdx.files.internal(textureHandle));
			sprite = new Sprite(texture);
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
	
	public static enum Shapes{
		IBLOCK("pieceiblock.png"),
		LBLOCK("piecelblock.png"),
		JBLOCK("piecejblock.png"),
		TBLOCK("piecetblock.png"),
		SBLOCK("piecesblock.png"),
		QBLOCK("piecesquare.png"),
		ZBLOCK("piecezblock.png");
		
		private String texHandle;
		private Texture texture;
		private Shapes(String str){
			texHandle = str;
			texture = new Texture(Gdx.files.internal(texHandle));
		}
		
		public String getHandle() {
			return texHandle;
		}
		
		public Texture getTexture() {
			return texture;
		}
		
		public Sprite getSprite() {
			return new Sprite(texture);
		}
	}
	
	private static float tileSize;
	private static Point boardBottomLeft;
	private static float boardWidth;
	private static float boardHeight;
	private static float uiPadding;
	private static Point uiBottomLeft;
	private static Point uiTopRight;
	
	public static BMScreen getScreen() {
		return (BMScreen)(((Game)Gdx.app.getApplicationListener()).getScreen());
	}
	
	public static void updateScore(int s) {
		getScreen().updateScore(s);
	}
	public static Point getUIBottomLeft() {
		return uiBottomLeft;
	}
	
	public static Point getUITopRight() {
		return uiTopRight;
	}
	
	public static Point getBoardBottomLeft() {
		return boardBottomLeft;
	}
	
	public static float getBoardHeight(){
		return boardHeight;
	}
	
	public static float getBoardWidth(){
		return boardWidth;
	}
	
	public static float getTileSize(){
		return tileSize;
	}
	
	public static float getUIPadding(){
		return uiPadding;	
	}
	
	public static void recalcSizes(int w, int h) {
		int largerDim = (w > h)? w : h;
		int smallerDim = (w < h)? w : h;
		
		uiPadding = smallerDim * 0.02f;
		boardWidth = (float)smallerDim * 0.66f - uiPadding * 2;
		tileSize = boardWidth / 10;
		
		int bottomLeftX = (int)uiPadding;
		
		if (tileSize * 21 + uiPadding * 2 > largerDim) {
			tileSize--;
			boardWidth -= 10;
		}
		
		boardHeight = tileSize * 21;
		
		int bottomLeftY = (int)(largerDim - boardHeight - uiPadding);
		
		boardBottomLeft = new Point(bottomLeftX,bottomLeftY);
		uiBottomLeft = new Point((int)(bottomLeftX + uiPadding * 4 + boardWidth), bottomLeftY);
		uiTopRight = new Point((int)(w - uiPadding), (int)(h - uiPadding));
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
	
	public static void dispose(){
		Colors[] cols = Colors.values();
		
		for(int i = 0; i < cols.length; i++){
			cols[i].getTexture().dispose();
		}
	}	
	
	public static void gameOver() {
		getScreen().gameOver();
	}
	
	public static void setNextPiece(Class c) {
		((GameScreen)(getScreen())).setNextPiece(c);
	}
}
