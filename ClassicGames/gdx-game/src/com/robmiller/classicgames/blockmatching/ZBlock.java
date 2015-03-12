package com.robmiller.classicgames.blockmatching;

import java.util.*;

public class ZBlock extends Piece
{
	public ZBlock(Board gb){
		super(gb,new BMUtils.Point(0,0),BMUtils.Colors.PURPLE);
		loadOffsets();
	}
	
	public ZBlock(Board gb, BMUtils.Point loc) {
		super(gb,loc,BMUtils.Colors.PURPLE);
		loadOffsets();
	}
	
	private void loadOffsets(){
		offsets = new HashMap<Integer,BMUtils.Point[]>();
		BMUtils.Point[] points = {new BMUtils.Point(1,0),
			new BMUtils.Point(0,-1),
			new BMUtils.Point(-1,-1)};

		offsets.put(0,points);

		points = new BMUtils.Point[]{new BMUtils.Point(1,0),
			new BMUtils.Point(0,1),
			new BMUtils.Point(1,-1)};

		offsets.put(1,points);

		points = new BMUtils.Point[]{new BMUtils.Point(1,0),
			new BMUtils.Point(0,-1),
			new BMUtils.Point(-1,-1)};
			
		offsets.put(2,points);

		points = new BMUtils.Point[]{new BMUtils.Point(1,0),
			new BMUtils.Point(0,1),
			new BMUtils.Point(1,-1)};

		offsets.put(3,points);
	}
}
