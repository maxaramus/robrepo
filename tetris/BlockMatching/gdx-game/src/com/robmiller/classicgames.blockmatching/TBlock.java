package com.robmiller.classicgames.blockmatching;
import java.util.*;

public class TBlock extends Piece
{
	public TBlock(Board gb){
		super(gb,new Point(0,0),Utils.Colors.RED);
		loadOffsets();
	}
	
	public TBlock(Board gb, Point loc) {
		super(gb,loc,Utils.Colors.RED);
		loadOffsets();
	}
	
	private void loadOffsets(){
		offsets = new HashMap<Integer,Point[]>();
		Point[] points = {new Point(-1,0),
						  new Point(0,1),
						  new Point(1,0)};
						  
		offsets.put(0,points);
		
		points = new Point[]{new Point(0,1),
							 new Point(-1,0),
							 new Point(0,-1)};
		
		offsets.put(1,points);
		
		points = new Point[]{new Point(1,0),
			new Point(0,-1),
			new Point(-1,0)};

		offsets.put(2,points);
		
		points = new Point[]{new Point(0,-1),
			new Point(1,0),
			new Point(0,1)};

		offsets.put(3,points);
	}
}
