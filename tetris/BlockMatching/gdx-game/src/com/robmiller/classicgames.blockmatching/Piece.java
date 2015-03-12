package com.robmiller.classicgames.blockmatching;
import java.util.*;
import android.util.*;

public class Piece
{  
	protected Integer rotation = 0;
	protected Point location;
	protected HashMap<Integer,Point[]> offsets = null;
	protected Utils.Colors color = null;
	protected Board gameBoard;
	public boolean isLocked = false;
	public int totalMoved = 0;
	
	public Piece(Board gb, Point loc, Utils.Colors col) {
		location = loc;
		gameBoard = gb;
		color = col;
	}
	
	public void pushDown(){
		clearPiece();
		Point newLoc = new Point(location.X,location.Y + 1);
		isLocked = !validMove(newLoc);
		
		if(!isLocked){
			location = newLoc;
			totalMoved++;
		}
		
		placePiece(location);
	}
	
	public void pushLeft(){
		clearPiece();
		Point newLoc = new Point(location.X-1,location.Y);

		if(validMove(newLoc)){
			location = newLoc;
		}

		placePiece(location);
	}
	
	public void pushRight(){
		clearPiece();
		Point newLoc = new Point(location.X+1,location.Y);

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
			Point tryNew = new Point(location.X-1,location.Y);
			if (validMove(tryNew))
				location = tryNew;
			else {
				tryNew = new Point(location.X+1,location.Y);
				if(validMove(tryNew))
					location = tryNew;
				else
					rotation = initialRot;
			}
		}
			
		placePiece(location);
	}
	
	public void placePiece(Point xy){
		Point[] tiles = offsets.get(getRotation());

		location = xy;
		validLocation();
		gameBoard.setTileColor(xy, color);

		for (Point p : tiles){
			Point newTile = new Point(xy.X + p.X, xy.Y + p.Y);
			gameBoard.setTileColor(newTile, color);
		}
	}
	
	public boolean validMove(Point newP){
		Point[] tiles = offsets.get(getRotation());
		boolean valid = true;

		if (gameBoard.isOccupied(newP))
			valid = false;
			
		for (Point p : tiles){
			if (gameBoard.isOccupied(p.add(newP)))
				valid = false;
		}
		
		return valid;
	}
	
	protected boolean validLocation(){
		Point[] tiles = offsets.get(getRotation());
		boolean valid = true;
		
		for (Point p : tiles){
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
		
		Point[] tiles = offsets.get(getRotation());

		gameBoard.setTileColor(location, Utils.Colors.NULL);

		for (Point p : tiles){
			gameBoard.setTileColor(new Point(location.X + p.X, location.Y + p.Y), Utils.Colors.NULL);
		}
	}
}
