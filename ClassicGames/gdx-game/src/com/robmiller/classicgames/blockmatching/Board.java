package com.robmiller.classicgames.blockmatching;
import com.badlogic.gdx.graphics.g2d.*;
//import android.util.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.*;

public class Board extends Table
{
	public static final int BOARD_ROWS = 21;
	public static final int BOARD_COLS = 10;
	
	private Tile[][] gameTiles = new Tile[BOARD_COLS][BOARD_ROWS];
	private BMUtils.Point[][] vertices = new BMUtils.Point[BOARD_COLS][BOARD_ROWS];
	private Piece currentPiece = null;
	private Class heldPiece = null;
	private Class nextPiece = null;
	private BMUtils.Point origin;
	private final PieceFactory factory;
	private float updateTime;
	private float timeSinceLastUpdate = 0;
	private int swapCount = 0;
	private GameWorld world;
	
	public Board(BMUtils.Point o){
		updateTime = 0.5f;
		origin = o;
		factory = new PieceFactory(this);
		updateSize();
	}
	
	public void updateSize() {
		for(int j = 0; j < BOARD_ROWS; j++){
			for(int i = 0; i < BOARD_COLS; i++){
				vertices[i][j] = new BMUtils.Point((int)(BMUtils.getBoardBottomLeft().X + i * BMUtils.getTileSize()), (int)(BMUtils.getBoardBottomLeft().Y + BMUtils.getBoardHeight() - (j + 1 ) * BMUtils.getTileSize()));
				gameTiles[i][j] = new Tile(vertices[i][j],BMUtils.Colors.NULL);
			}
		}
	}
	
	@Override
	public void act(float delta) {
		timeSinceLastUpdate += delta;
		float actualTime = updateTime / ((GameScreen)(BMUtils.getScreen())).getLevel();
	
		if(timeSinceLastUpdate >= actualTime){
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
					gameTiles[x][0].changeColor(BMUtils.Colors.NULL);
				}
			}
		}
		
		BMUtils.updateScore(cleared * 100);
	}
	
	public boolean isOccupied(BMUtils.Point p){
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
			currentPiece.placePiece(new BMUtils.Point(5,0));
		}
		
		return heldPiece;
	}
	
	private void placePiece(){
		int newPiece = MathUtils.random(0,6);
		
		if (nextPiece == null) {
			currentPiece = getPiece(newPiece);
			nextPiece = factory.getPiece(MathUtils.random(0,6)).getClass();
		}
		else {
			currentPiece = factory.getPiece(nextPiece);
			nextPiece = getPiece(newPiece).getClass();
		}
		
		if(currentPiece == null) return;
		 
		BMUtils.setNextPiece(nextPiece);
		currentPiece.placePiece(new BMUtils.Point(5,0));
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
		batch.draw(BMUtils.Colors.NULL.getTexture(),BMUtils.getBoardBottomLeft().X, BMUtils.getBoardBottomLeft().Y, BMUtils.getBoardWidth(), BMUtils.getBoardHeight());
		for(int i = 0; i < BOARD_COLS; i++){
			for(int j = 0; j < BOARD_ROWS; j++){
				gameTiles[i][j].draw(batch,alpha);
			}
		}
	}
	
	public void pushDown(){
		if (currentPiece == null || currentPiece.isLocked){
			checkRows();
			if (currentPiece != null && currentPiece.totalMoved == 0) {
				BMUtils.gameOver();
			}
			else {
				placePiece();
			}
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
	
	public void setTileColor(BMUtils. Point xy, BMUtils.Colors col){
		if (pointOnBoard(xy))
			gameTiles[xy.X][xy.Y].changeColor(col);
	}
	
	public void rotatePiece(){
		if (currentPiece != null)
			currentPiece.rotate();
		timeSinceLastUpdate = 0;
	}
	
	private boolean pointOnBoard(BMUtils.Point xy){
		if (xy.X < 0 || xy.X >= BOARD_COLS)
			return false;
		if (xy.Y < 0 || xy.Y >= BOARD_ROWS)
			return false;
			
		return true;
	}
}
