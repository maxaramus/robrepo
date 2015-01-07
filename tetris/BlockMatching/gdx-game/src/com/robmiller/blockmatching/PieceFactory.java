package com.robmiller.blockmatching;

public class PieceFactory
{
	Board gameBoard;
	
	public PieceFactory(Board gb){
		gameBoard = gb;
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