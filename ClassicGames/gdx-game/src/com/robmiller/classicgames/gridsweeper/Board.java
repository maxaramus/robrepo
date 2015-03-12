package com.robmiller.classicgames.gridsweeper;

import com.badlogic.gdx.graphics.g2d.*;
//import android.util.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.*;
import android.util.*;
import java.util.*;

public class Board extends Table
{
	public static final int NUM_DIFFICULTIES = 3;
	public static final int[] BOARD_ROWS = {9,16,16};
	public static final int[] BOARD_COLS = {9,16,30};
	public static final int[] NUM_MINES = {10,40,99};
	
	private Tile[][] gameTiles;
	private GSUtils.Point[][] vertices;
	private GSUtils.Point origin;
	private float updateTime;
	private float timeSinceLastUpdate = 0;
	private GameWorld world;
	private int currentDifficulty = 0;
	private boolean isFirstClick = true;
	private boolean dropFlags = false;
	
	public Board(GameWorld w, GSUtils.Point o,int diff){
		world = w;
		updateTime = 0.5f;
		origin = o;
		setDifficulty(diff);
	}
	
	public void toggleFlags() {
		dropFlags = !dropFlags;	
	}
	
	public float getActualHeight() {
		return BOARD_ROWS[currentDifficulty] * GSUtils.getTileSize();	
	}
	
	public void setDifficulty(int newDiff) {
		if (newDiff < 0 || newDiff >= NUM_DIFFICULTIES)
			return;
			
		currentDifficulty = newDiff;
		generateBoard();
	}
	
	private void generateBoard() {
		int numMines = NUM_MINES[currentDifficulty];
		
		gameTiles = new Tile[BOARD_COLS[currentDifficulty]][BOARD_ROWS[currentDifficulty]];
		vertices = new GSUtils.Point[BOARD_COLS[currentDifficulty]][BOARD_ROWS[currentDifficulty]];

		Random random = new Random();
		GSUtils.calcTileSize(BOARD_COLS[currentDifficulty],BOARD_ROWS[currentDifficulty]);
		
		for(int j = 0; j < BOARD_ROWS[currentDifficulty]; j++) {
			for(int i = 0; i < BOARD_COLS[currentDifficulty]; i++) {
				vertices[i][j] = new GSUtils.Point((int)(GSUtils.getBoardBottomLeft().X + i * GSUtils.getTileSize()), (int)(GSUtils.getBoardBottomLeft().Y + GSUtils.getBoardHeight() - (j + 1 ) * GSUtils.getTileSize()));
				gameTiles[i][j] = new Tile(vertices[i][j]);
				gameTiles[i][j].xIndex = i;
				gameTiles[i][j].yIndex = j;
				
				if (numMines > 0) {
					gameTiles[i][j].isMine = true;
					numMines--;
				}
			}
		}
		
		int shuffleCounter = NUM_MINES[currentDifficulty];
		int xCounter = 0,yCounter = 0;
		
		while(yCounter < BOARD_ROWS[currentDifficulty] && shuffleCounter > 0) {
			xCounter = 0;
			while(xCounter < BOARD_COLS[currentDifficulty] && shuffleCounter > 0) {
				if (gameTiles[xCounter][yCounter].isMine) {
					int newX = random.nextInt(BOARD_COLS[currentDifficulty]);
					int newY = random.nextInt(BOARD_ROWS[currentDifficulty]);
					
					if (!gameTiles[newX][newY].isMine) {
						gameTiles[xCounter][yCounter].isMine = false;
						gameTiles[newX][newY].isMine = true;
					
						shuffleCounter--;
					}
				}
				xCounter++;
			}	
			yCounter++;
		}
	}

	public void wasTouched(int screenX, int screenY) {
		if (screenX < origin.X ||
			screenX > origin.X + GSUtils.getBoardWidth() ||
			screenY < origin.Y ||
			screenY > origin.Y + GSUtils.getBoardHeight()) {
			return;
		}
			
		for (int j = 0; j < BOARD_ROWS[currentDifficulty]; j++) {
			for (int i = 0; i < BOARD_COLS[currentDifficulty]; i++) {
				if (!gameTiles[i][j].containsPoint(new GSUtils.Point(screenX, screenY)))
					continue;
		
				ArrayList<Tile> neighbors = getNeighbors(i,j);
				
				if (isFirstClick && !dropFlags) {
					isFirstClick = false;
					moveMine(i,j);
					
					for (Tile t : neighbors) {
						moveMine(t.xIndex,t.yIndex);
					}
				}			
				
				if (dropFlags) {
					if (gameTiles[i][j].getState() == GSUtils.TileStates.FLAGGED) {
						gameTiles[i][j].setState(GSUtils.TileStates.HIDDEN);
					}
					else if (gameTiles[i][j].getState() == GSUtils.TileStates.HIDDEN) {
						gameTiles[i][j].setState(GSUtils.TileStates.FLAGGED);
					}
					gameTiles[i][j].isFlagged = !gameTiles[i][j].isFlagged;
				}
				else {
					if (gameTiles[i][j].isFlagged) {
						
					}
					else {
						if (gameTiles[i][j].isMine) {
							gameTiles[i][j].setState(GSUtils.TileStates.MINE);
							gameTiles[i][j].getSprite().setColor(1.0f,0.0f,0.0f,1.0f);
							exposeMines();
							world.setGameOver();
							break;
						}
						else if (!(gameTiles[i][j].isFlagged ||
								   (gameTiles[i][j].getState() != GSUtils.TileStates.HIDDEN))) {
							int numNeighboringMines = 0;
							Iterator<Tile> iter = neighbors.iterator();
							
							while (iter.hasNext()) {
								if (iter.next().isMine) {
									numNeighboringMines++;
								}	
							}
					
							gameTiles[i][j].setState(GSUtils.TileStates.values()[numNeighboringMines]);
						
							if (numNeighboringMines == 0) {
								keepExposing(neighbors);
							}
							break;
						}
					}
				}
			}
		}
	}
	
	private void moveMine(int x, int y) {
		Random rand = new Random();
		int newX = rand.nextInt(BOARD_COLS[currentDifficulty]);
		int newY = rand.nextInt(BOARD_ROWS[currentDifficulty]);
		
		while (gameTiles[newX][newY].isMine) {
			newX = rand.nextInt(BOARD_COLS[currentDifficulty]);
			newY = rand.nextInt(BOARD_ROWS[currentDifficulty]);
		}
		
		gameTiles[x][y].isMine = false;
		gameTiles[newX][newY].isMine = true;
	}
	
	private void keepExposing(ArrayList<Tile> list) {
		for (int i = 0; i < list.size(); ++i) {
			Tile temp = list.get(i);
			ArrayList<Tile> neighbors = getNeighbors(temp.xIndex, temp.yIndex);
			
			int mines = 0;
			for (int j = 0; j < neighbors.size(); ++j) {
				if (neighbors.get(j).isMine) {
					mines++;
				}
			}
			
			gameTiles[temp.xIndex][temp.yIndex].setState(GSUtils.TileStates.values()[mines]);
			
			if (mines == 0) {
				Iterator<Tile> iter = neighbors.iterator();
				while(iter.hasNext()) {
					Tile next = iter.next();
					if (next.getState() != GSUtils.TileStates.HIDDEN) {
						iter.remove();
					}
				}
				keepExposing(neighbors);
			}
		}
	}
	
	private ArrayList<Tile> getNeighbors(int x, int y) {
		ArrayList<Tile> returnArr = new ArrayList<Tile>();
		
		if (pointOnBoard(new GSUtils.Point(x-1,y-1))) {
			returnArr.add(gameTiles[x-1][y-1]);
		}	
		if (pointOnBoard(new GSUtils.Point(x,y-1))) {
			returnArr.add(gameTiles[x][y-1]);
		}
		if (pointOnBoard(new GSUtils.Point(x+1,y-1))) {
			returnArr.add(gameTiles[x+1][y-1]);
		}
		if (pointOnBoard(new GSUtils.Point(x+1,y))) {
			returnArr.add(gameTiles[x+1][y]);
		}
		if (pointOnBoard(new GSUtils.Point(x+1,y+1))) {
			returnArr.add(gameTiles[x+1][y+1]);
		}
		if (pointOnBoard(new GSUtils.Point(x,y+1))) {
			returnArr.add(gameTiles[x][y+1]);
		}
		if (pointOnBoard(new GSUtils.Point(x-1,y+1))) {
			returnArr.add(gameTiles[x-1][y+1]);
		}
		if (pointOnBoard(new GSUtils.Point(x-1,y))) {
			returnArr.add(gameTiles[x-1][y]);
		}
		
		return returnArr;
	}
	
	private void exposeMines() {
		for (int j = 0; j < BOARD_ROWS[currentDifficulty]; j++) {
			for (int i = 0; i < BOARD_COLS[currentDifficulty]; i++) {
				if (gameTiles[i][j].isMine && !gameTiles[i][j].isFlagged) {
					gameTiles[i][j].setState(GSUtils.TileStates.MINE);
				}
			}
		}	
	}
	
	public void updateSize() {
		origin = GSUtils.getBoardBottomLeft();
		GSUtils.calcTileSize(BOARD_COLS[currentDifficulty],BOARD_ROWS[currentDifficulty]);
		for(int j = 0; j < BOARD_ROWS[currentDifficulty]; j++) {
			for(int i = 0; i < BOARD_COLS[currentDifficulty]; i++) {
				vertices[i][j] = new GSUtils.Point((int)(origin.X + i * GSUtils.getTileSize()), (int)(origin.Y + GSUtils.getBoardHeight() - (j + 1 ) * GSUtils.getTileSize()));
				gameTiles[i][j].setBottomLeft(vertices[i][j]);
			}
		}
	}
	
	@Override
	public void act(float delta) {
		timeSinceLastUpdate += delta;
	}
	
	@Override
	public void draw(Batch batch, float alpha){
		for(int i = 0; i < BOARD_COLS[currentDifficulty]; i++){
			for(int j = 0; j < BOARD_ROWS[currentDifficulty]; j++){
				gameTiles[i][j].draw(batch,alpha);
			}
		}
	}
	
	private boolean pointOnBoard(GSUtils.Point xy){
		if (xy.X < 0 || xy.X >= BOARD_COLS[currentDifficulty])
			return false;
		if (xy.Y < 0 || xy.Y >= BOARD_ROWS[currentDifficulty])
			return false;
			
		return true;
	}
}
