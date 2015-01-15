package com.robmiller.blockmatching;
import com.badlogic.gdx.graphics.g2d.*;
import android.util.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class Board extends Table
{
	public static final int BOARD_ROWS = 21;
	public static final int BOARD_COLS = 10;
	
	private Tile[][] gameTiles = new Tile[BOARD_COLS][BOARD_ROWS];
	private Point[][] vertices = new Point[BOARD_COLS][BOARD_ROWS];
	private Piece currentPiece = null;
	private Class heldPiece = null;
	private Point origin;
	private final PieceFactory factory;
	private float updateTime;
	private float timeSinceLastUpdate = 0;
	private int swapCount = 0;
	
	public Board(Point o){
		updateTime = 0.5f;
		origin = o;
		factory = new PieceFactory(this);
		
		for(int j = 0; j < BOARD_ROWS; j++){
			for(int i = 0; i < BOARD_COLS; i++){
				vertices[i][j] = new Point((int)(o.X + i * Utils.getTileSize()), (int)(o.Y + j * Utils.getTileSize()));
				gameTiles[i][j] = new Tile(vertices[i][j],Utils.Colors.NULL);
			}
		}
	}
	
	@Override
	public void act(float delta) {
		timeSinceLastUpdate += delta;

		if(timeSinceLastUpdate >= updateTime){
			pushDown();
			timeSinceLastUpdate = 0;
		}
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
						gameTiles[x][y].changeColor(gameTiles[x][y-1].getTileColor());
					}
				}
				for (int x = 0; x < BOARD_COLS; x++){
					gameTiles[x][0].changeColor(Utils.Colors.NULL);
				}
			}
		}
	}
	
	public boolean isOccupied(Point p){
		boolean ret = false;
		
		ret = !pointOnBoard(p);
		
		if (!ret)
			ret = gameTiles[p.X][p.Y].isOccupied();
		
		return ret;
	}
	
	public Class holdSwapPiece() {
		if (swapCount < 1)
			swapCount++;
		else
			return null;
			
		if (currentPiece == null) {
			return null;
		}
		
		if (heldPiece == null) {
			heldPiece = currentPiece.getClass();
			currentPiece.clearPiece();
			placePiece();
			swapCount = 1;
		}
		else {
			Class temp = heldPiece;
			heldPiece = currentPiece.getClass();
			currentPiece.clearPiece();
			currentPiece = factory.getPiece(temp);
			currentPiece.placePiece(new Point(5,gameTiles[0].length - 1));
		}
		
		return heldPiece;
	}
	
	private void placePiece(){
		int newPiece = MathUtils.random(0,6);
		currentPiece = getPiece(newPiece);
		
		if(currentPiece == null) return;
		 
		currentPiece.placePiece(new Point(5,gameTiles[0].length - 1));
		swapCount = 0;
	}
	
	private Piece getPiece(int i){
		return factory.getPiece(i);
	}
	
	//public Point getScreenCoords(Point xy){
	//	return vertices[xy.X][xy.Y];
	//}
	
	@Override
	public void draw(Batch batch, float alpha){
		batch.draw(Utils.Colors.NULL.getTexture(),Utils.getBoardBottomLeft().X, Utils.getBoardBottomLeft().Y, Utils.getBoardWidth(), Utils.getBoardHeight());
		for(int i = 0; i < BOARD_COLS; i++){
			for(int j = 0; j < BOARD_ROWS; j++){
				gameTiles[i][j].draw(batch,alpha);
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
