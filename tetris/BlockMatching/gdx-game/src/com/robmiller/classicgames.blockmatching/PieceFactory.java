package com.robmiller.classicgames.blockmatching;
import java.lang.reflect.*;

public class PieceFactory
{
	Board gameBoard;
	
	public PieceFactory(Board gb){
		gameBoard = gb;
	}
	
	public Piece getPiece(Class type) {
		try{
			return (Piece)(type.getConstructors()[0].newInstance(gameBoard));	
		}
		catch(InstantiationException e) {
			return null;
		}
		catch(IllegalAccessException e) {
			return null;
		}
		catch(InvocationTargetException e) {
			return null;
		}
	}
	
	public Piece getPiece(int type){
		if (type < 0 || type > Utils.Shapes.values().length)
			return null;
			
		Utils.Shapes s = Utils.Shapes.values()[type];
		
		switch(s){
			case IBLOCK:
				return new IBlock(gameBoard);
			case LBLOCK:
				return new LBlock(gameBoard);
			case JBLOCK:
				return new JBlock(gameBoard);
			case SBLOCK:
				return new SBlock(gameBoard);
			case QBLOCK:
				return new SquareBlock(gameBoard);
			case TBLOCK:
				return new TBlock(gameBoard);
			case ZBLOCK:
				return new ZBlock(gameBoard);
			default:
				return null;
		}
	}
}
