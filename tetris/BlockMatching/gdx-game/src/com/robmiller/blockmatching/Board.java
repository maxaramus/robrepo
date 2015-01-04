package com.robmiller.blockmatching;
import com.badlogic.gdx.graphics.g2d.*;
import android.util.*;
import com.badlogic.gdx.graphics.*;

public class Board
{
	public static final int BOARD_ROWS = 21;
	public static final int BOARD_COLS = 10;
	
	Tile[][] gameTiles = new Tile[BOARD_COLS][BOARD_ROWS];
	Point[][] vertices = new Point[BOARD_COLS][BOARD_ROWS];
	
	TBlock tb = new TBlock(this, new Point(0,0));
	
	Point origin;
	
	public Board(Point o){
		origin = o;
		
		for(int i = 0; i < BOARD_COLS; i++){
			for(int j = 0; j < BOARD_ROWS; j++){
				vertices[i][j] = new Point(o.X + j * Utils.Colors.BLUE.getTexture().getWidth(), o.Y - i * Utils.Colors.BLUE.getTexture().getHeight());
				gameTiles[i][j] = new Tile(this,vertices[i][j],Utils.Colors.NULL);
			}
		}
		tb.placePiece(new Point(5,5));
	}
	
	public Point getScreenCoords(Point xy){
		return vertices[xy.X][xy.Y];
	}
	
	public void draw(SpriteBatch batch){
		BitmapFont font = new BitmapFont(); 
		font.setColor(Color.RED);
		
		for(int i = 0; i < BOARD_COLS; i++){
			for(int j = 0; j < BOARD_ROWS; j++){
				gameTiles[i][j].draw(batch);
				font.draw(batch,i+""+j,vertices[i][j].X,vertices[i][j].Y);
			}
		}
		
		
	}
	
	public int translateLeft(int start){
		if (start <= 1) return 0;
		return start - 1;
	}
	
	public void setTileColor(Point xy, Utils.Colors col){
		if (pointOnBoard(xy))
			gameTiles[xy.X][xy.Y].color = col;
	}
	
	public void rotatePiece(){
		if (tb!=null)
			tb.rotate();
	}
	
	private boolean pointOnBoard(Point xy){
		if (xy.X < 0 || xy.X >= BOARD_COLS)
			return false;
		if (xy.Y < 0 || xy.Y >= BOARD_ROWS)
			return false;
			
		return true;
	}
}
