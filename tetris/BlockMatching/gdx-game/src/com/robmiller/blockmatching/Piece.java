package com.robmiller.blockmatching;
import java.util.*;
import android.util.*;

public class Piece
{  
	protected Integer rotation = 0;
	protected Point location;
	protected HashMap<Integer,Point[]> offsets = null;
	protected Utils.Colors color = null;
	protected Board gameBoard;
	
	public Piece(Board gb, Point loc, Utils.Colors col) {
		location = loc;
		gameBoard = gb;
		color = col;
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public void rotate() {
		clearPiece();
		
		if (rotation == 3)
			rotation = 0;
		else
			rotation++;
		
		placePiece(location);
		validateLocation();
	}
	
	public void placePiece(Point xy){
		Point[] tiles = offsets.get(getRotation());

		location = xy;

		gameBoard.setTileColor(xy, color);

		for (Point p : tiles){
			Point newTile = new Point(xy.X + p.X, xy.Y + p.Y);
			gameBoard.setTileColor(newTile, color);
		}
	}
	
	protected void validateLocation(){
		Point[] tiles = offsets.get(getRotation());
		
		for (Point p : tiles){
			if (location.X + p.X < 0)
				location.X++;
			else if(location.X + p.X >= Board.BOARD_COLS)
				location.X--;
				
			if (location.Y + p.Y < 0)
				location.Y++;
			else if (location.Y + p.Y >= Board.BOARD_ROWS)
				location.Y--;
		}
	}
	
	protected void clearPiece(){
		Point[] tiles = offsets.get(getRotation());

		gameBoard.setTileColor(location, Utils.Colors.NULL);

		for (Point p : tiles){
			gameBoard.setTileColor(new Point(location.X + p.X, location.Y + p.Y), Utils.Colors.NULL);
		}
	}
}
