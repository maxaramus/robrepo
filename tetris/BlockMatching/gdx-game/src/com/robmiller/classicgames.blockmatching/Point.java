package com.robmiller.classicgames.blockmatching;

public class Point
{
	public int X;
	public int Y;
	
	Point(int x, int y){
		X = x;
		Y = y;
	}
	
	public Point add(Point rh){
		Point ret = new Point(X+rh.X,Y+rh.Y);
		return ret;
	}
}
