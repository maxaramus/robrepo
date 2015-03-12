package com.robmiller.classicgames.blockmatching;

import java.util.*;

public class SquareBlock extends Piece
{
	public SquareBlock(Board gb) {
		super(gb,new Point(0,0),Utils.Colors.GREEN);
		loadOffsets();
	}
	
	public SquareBlock(Board gb, Point loc) {
		super(gb,loc,Utils.Colors.GREEN);
		loadOffsets();
	}
	
	private void loadOffsets(){
		offsets = new HashMap<Integer,Point[]>();
		Point[] points = {new Point(1,0),
			new Point(1,1),
			new Point(0,1)};

		offsets.put(0,points);

		points = new Point[]{new Point(1,0),
			new Point(1,1),
			new Point(0,1)};

		offsets.put(1,points);

		points = new Point[]{new Point(1,0),
			new Point(1,1),
			new Point(0,1)};

		offsets.put(2,points);

		points = new Point[]{new Point(1,0),
			new Point(1,1),
			new Point(0,1)};

		offsets.put(3,points);
	}
}
