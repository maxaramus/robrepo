package com.robmiller.blockmatching;

import java.util.*;

public class JBlock extends Piece
{
	public JBlock(Board gb){
		super(gb,new Point(0,0),Utils.Colors.BLUE);
		loadOffsets();
	} 
	
	public JBlock(Board gb, Point loc) {
		super(gb,loc,Utils.Colors.BLUE);
		loadOffsets();
	}
	
	private void loadOffsets(){
		offsets = new HashMap<Integer,Point[]>();
		Point[] points = {new Point(0,-1),
			new Point(0,1),
			new Point(-1,1)};

		offsets.put(0,points);

		points = new Point[]{new Point(-1,-1),
			new Point(-1,0),
			new Point(1,0)};

		offsets.put(1,points);

		points = new Point[]{new Point(0,-1),
			new Point(1,-1),
			new Point(0,1)};

		offsets.put(2,points);

		points = new Point[]{new Point(1,0),
			new Point(1,1),
			new Point(-1,0)};

		offsets.put(3,points);
	}
}
