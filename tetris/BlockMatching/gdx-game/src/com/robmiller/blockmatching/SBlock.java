package com.robmiller.blockmatching;

import java.util.*;

public class SBlock extends Piece
{
	public SBlock(Board gb){
		super(gb,new Point(0,0),Utils.Colors.YELLOW);
		loadOffsets();
	}
	
	public SBlock(Board gb, Point loc) {
		super(gb,loc,Utils.Colors.YELLOW);
		loadOffsets();
	}
	
	private void loadOffsets(){
		offsets = new HashMap<Integer,Point[]>();
		Point[] points = {new Point(1,0),
			new Point(0,1),
			new Point(-1,1)};

		offsets.put(0,points);

		points = new Point[]{new Point(-1,-1),
			new Point(-1,0),
			new Point(0,1)};

		offsets.put(1,points);

		points = new Point[]{new Point(1,0),
			new Point(0,1),
			new Point(-1,1)};

		offsets.put(2,points);

		points = new Point[]{new Point(-1,-1),
			new Point(-1,0),
			new Point(0,1)};
		
		offsets.put(3,points);
	}
}
