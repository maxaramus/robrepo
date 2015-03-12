package com.robmiller.classicgames.gridsweeper;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
//import android.util.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.robmiller.classicgames.*;

public class GSUtils extends Utils
{
	private static float BOARD_WIDTH = 0.90f;
	public static float UI_WIDTH = 0.10f;
	
	public static enum TileStates{
		ZERO("gs_zero.png"),
		ONE("gs_one.png"),
		TWO("gs_two.png"),
		THREE("gs_three.png"),
		FOUR("gs_four.png"),
		FIVE("gs_five.png"),
		SIX("gs_six.png"),
		SEVEN("gs_seven.png"),
		EIGHT("gs_eight.png"),
		HIDDEN("gs_hidden.png"),
		FLAGGED("gs_flagged.png"),
		MINE("gs_mine.png");
		
		private String textureHandle;
		private Texture texture;
		
		private TileStates(String tex) {
			if (tex == null) return;
			
			textureHandle = tex;
			texture = new Texture(Gdx.files.internal(textureHandle));
		}
		
		public String getTextureHandle() {
			return textureHandle;
		}
		
		public Texture getTexture() {
			return texture;
		}
	}
	
	public static enum Buttons{
		MENU("MENU"),
		PAUSE("PAUSE"),
		NEWGAME("NEW"),
		FLAG(""),
		RESUME("RESUME");

		private String str;

		private Buttons(String s) {
			str = s;	
		}

		public String getString() {
			return str;
		}
	}

	private static float tileSize;
	private static Point boardBottomLeft;
	private static float boardWidth;
	private static float boardHeight;
	private static float uiPadding;
	private static Point uiBottomLeft;
	private static Point uiTopRight;
	private static float playableWidth;
	private static float playableHeight;

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

	public static void calcTileSize(int numCols, int numRows) {
		tileSize = boardWidth / numCols;	
		while (tileSize * numRows > boardHeight) {
			tileSize--;
			boardWidth -= numCols;
		}
	}
	
	public static void recalcSizes(int w, int h) {
		uiPadding = h * 0.02f;
		boardWidth = w * BOARD_WIDTH - uiPadding * 2;
		boardHeight = h - uiPadding * 2;
		
		int bottomLeftX = (int)uiPadding;
		int bottomLeftY = (int)(h + uiPadding);

		boardBottomLeft = new Point(bottomLeftX,bottomLeftY);
		
		uiBottomLeft = new Point((int)(bottomLeftX + uiPadding * 4 + boardWidth), bottomLeftY);
		uiTopRight = new Point((int)(w - uiPadding), (int)(h - uiPadding));
	}

	public static void dispose(){
	}	

	public static void gameOver() {
		getScreen().gameOver();
	}
}
