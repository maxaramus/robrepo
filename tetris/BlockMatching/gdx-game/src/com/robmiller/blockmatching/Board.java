package com.robmiller.blockmatching;
import com.badlogic.gdx.graphics.g2d.*;

public class Board
{
	public static final int BOARD_ROWS = 21;
	public static final int BOARD_COLS = 10;
	
	Tile[][] gameTiles = new Tile[BOARD_ROWS][BOARD_COLS];
	Point origin;
	
	public Board(Point o){
		origin = o;
		
		for(int i = 0; i < BOARD_ROWS; i++){
			for(int j = 0; j < BOARD_COLS; j++){
				gameTiles[i][j] = new Tile(this, 
					new Point(o.X + j * (int)Utils.getTileWidth(), o.Y - i * (int)Utils.getTileHeight()),
					Utils.Colors.GREEN);
			}
		}
	}
	
	public void draw(SpriteBatch batch){
		for(int i = 0; i < BOARD_ROWS; i++){
			for(int j = 0; j < BOARD_COLS; j++){
				gameTiles[i][j].draw(batch);
			}
		}
	}
	public int translateLeft(int start){
		if (start <= 1) return 0;
		return start - 1;
	}
}
