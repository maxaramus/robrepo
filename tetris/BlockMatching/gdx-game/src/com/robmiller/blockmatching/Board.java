package com.robmiller.blockmatching;
import com.badlogic.gdx.graphics.g2d.*;
import android.util.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;

public class Board
{
	public static final int BOARD_ROWS = 21;
	public static final int BOARD_COLS = 10;
	
	private Tile[][] gameTiles = new Tile[BOARD_COLS][BOARD_ROWS];
	private Point[][] vertices = new Point[BOARD_COLS][BOARD_ROWS];
	private Piece currentPiece = null;
	private Point origin;
	private final PieceFactory factory;
	private Player player;
	
	public Board(Point o, Player p){
		origin = o;
		factory = new PieceFactory(this);
		player = p;
		
		for(int i = 0; i < BOARD_COLS; i++){
			for(int j = 0; j < BOARD_ROWS; j++){
				vertices[i][j] = new Point(o.X + i * Utils.Colors.BLUE.getTexture().getWidth(), o.Y - j * Utils.Colors.BLUE.getTexture().getHeight());
				gameTiles[i][j] = new Tile(this,vertices[i][j],Utils.Colors.NULL);
			}
		}
	}
	
	public void update(){
		pushDown();
		
		//if(currentPiece.isLocked)
			//checkRows();
	}
	
	private void checkRows(){
		int cleared = 0;
		
		for (int i = 0; i < BOARD_ROWS; i++){
			int j = 0;
			boolean matched = true;
			while(matched && j < BOARD_COLS){
				if (!gameTiles[j][i].isOccupied()){
					matched = false;
				}
				
				j++;
			}

			if (matched){
				cleared++;
				for (int y = i; y >0; y--){
					for (int x = 0; x < BOARD_COLS; x++){
						gameTiles[x][y].changeColor(gameTiles[x][y-1].getColor());
					}
				}
				for (int x = 0; x < BOARD_COLS; x++){
					gameTiles[x][0].changeColor(Utils.Colors.NULL);
				}
			}
		}
		
		player.incrementLinesCleared(cleared);
	}
	
	public boolean isOccupied(Point p){
		boolean ret = false;
		
		ret = !pointOnBoard(p);
		
		if (!ret)
			ret = gameTiles[p.X][p.Y].isOccupied();
		
		return ret;
	}
	
	private void placePiece(){
		int newPiece = MathUtils.random(0,6);
		currentPiece = getPiece(newPiece);
		
		if(currentPiece == null) return;
		 
		currentPiece.placePiece(new Point(5,0));
	}
	
	private Piece getPiece(int i){
		return factory.getPiece(i);
	}
	
	public Point getScreenCoords(Point xy){
		return vertices[xy.X][xy.Y];
	}
	
	public void draw(SpriteBatch batch){
		for(int i = 0; i < BOARD_COLS; i++){
			for(int j = 0; j < BOARD_ROWS; j++){
				gameTiles[i][j].draw(batch);
			}
		}
	}
	
	public void pushDown(){
		if (currentPiece == null || currentPiece.isLocked){
			checkRows();
			placePiece();
		}
		else{
			currentPiece.pushDown();
		}
	}
	
	public void pushLeft(){
		if (currentPiece == null || currentPiece.isLocked){
			return;
		}
		else{
			currentPiece.pushLeft();
		}
	}
	
	public void pushRight(){
		if (currentPiece == null || currentPiece.isLocked){
			return;
		}
		else{
			currentPiece.pushRight();
		}
	}
	
	public void setTileColor(Point xy, Utils.Colors col){
		if (pointOnBoard(xy))
			gameTiles[xy.X][xy.Y].changeColor(col);
	}
	
	public void rotatePiece(){
		if (currentPiece != null)
			currentPiece.rotate();
	}
	
	private boolean pointOnBoard(Point xy){
		if (xy.X < 0 || xy.X >= BOARD_COLS)
			return false;
		if (xy.Y < 0 || xy.Y >= BOARD_ROWS)
			return false;
			
		return true;
	}
}
