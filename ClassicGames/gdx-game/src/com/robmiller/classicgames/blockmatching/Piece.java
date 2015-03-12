package com.robmiller.classicgames.blockmatching;
import java.util.*;
//import android.util.*;

public class Piece
{  
	protected Integer rotation = 0;
	protected BMUtils.Point location;
	protected HashMap<Integer,BMUtils.Point[]> offsets = null;
	protected BMUtils.Colors color = null;
	protected Board gameBoard;
	public boolean isLocked = false;
	public int totalMoved = 0;
	
	public Piece(Board gb, BMUtils.Point loc, BMUtils.Colors col) {
		location = loc;
		gameBoard = gb;
		color = col;
	}
	
	public void pushDown(){
		clearPiece();
		BMUtils.Point newLoc = new BMUtils.Point(location.X,location.Y + 1);
		isLocked = !validMove(newLoc);
		
		if(!isLocked){
			location = newLoc;
			totalMoved++;
		}
		
		placePiece(location);
	}
	
	public void pushLeft(){
		clearPiece();
		BMUtils.Point newLoc = new BMUtils.Point(location.X-1,location.Y);

		if(validMove(newLoc)){
			location = newLoc;
		}

		placePiece(location);
	}
	
	public void pushRight(){
		clearPiece();
		BMUtils.Point newLoc = new BMUtils.Point(location.X+1,location.Y);

		if(validMove(newLoc)){
			location = newLoc;
		}

		placePiece(location);
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public void rotate() {
		clearPiece();
		int initialRot = rotation;
		
		if (rotation == 3)
			rotation = 0;
		else
			rotation++;
		
		if (!validMove(location)) {
			BMUtils.Point tryNew = new BMUtils.Point(location.X-1,location.Y);
			if (validMove(tryNew))
				location = tryNew;
			else {
				tryNew = new BMUtils.Point(location.X+1,location.Y);
				if(validMove(tryNew))
					location = tryNew;
				else
					rotation = initialRot;
			}
		}
			
		placePiece(location);
	}
	
	public void placePiece(BMUtils.Point xy){
		BMUtils.Point[] tiles = offsets.get(getRotation());

		location = xy;
		validLocation();
		gameBoard.setTileColor(xy, color);

		for (BMUtils.Point p : tiles){
			BMUtils.Point newTile = new BMUtils.Point(xy.X + p.X, xy.Y + p.Y);
			gameBoard.setTileColor(newTile, color);
		}
	}
	
	public boolean validMove(BMUtils.Point newP){
		BMUtils.Point[] tiles = offsets.get(getRotation());
		boolean valid = true;

		if (gameBoard.isOccupied(newP))
			valid = false;
			
		for (BMUtils.Point p : tiles){
			if (gameBoard.isOccupied(p.add(newP)))
				valid = false;
		}
		
		return valid;
	}
	
	protected boolean validLocation(){
		BMUtils.Point[] tiles = offsets.get(getRotation());
		boolean valid = true;
		
		for (BMUtils.Point p : tiles){
			if (location.X + p.X < 0){
				location.X++;
				valid = false;
			}
			else if(location.X + p.X >= Board.BOARD_COLS){
				location.X--;
				valid = false;
			}	
			if (location.Y + p.Y < 0){
				location.Y++;
				valid = false;
			}
			else if (location.Y + p.Y >= Board.BOARD_ROWS){
				location.Y--;
				valid = false;
			}
		}
		return valid;
	}
	
	public void clearPiece(){
		if (isLocked) return;
		
		BMUtils.Point[] tiles = offsets.get(getRotation());

		gameBoard.setTileColor(location, BMUtils.Colors.NULL);

		for (BMUtils.Point p : tiles){
			gameBoard.setTileColor(new BMUtils.Point(location.X + p.X, location.Y + p.Y), BMUtils.Colors.NULL);
		}
	}
}
