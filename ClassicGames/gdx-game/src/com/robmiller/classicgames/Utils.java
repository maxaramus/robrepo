package com.robmiller.classicgames;

import com.badlogic.gdx.*;

public class Utils
{
	public static enum Orientations {
		PORTRAIT,
		LANDSCAPE;
	}
	
	public static class Point
	{
		public int X;
		public int Y;

		public Point(int x, int y){
			X = x;
			Y = y;
		}

		public Point add(Point rh){
			Point ret = new Point(X+rh.X,Y+rh.Y);
			return ret;
		}
	}
	
	private static ScreenOrientator screenOrientator = null;
	private static Orientations orientation = Orientations.PORTRAIT;
	
	public static Orientations getOrientation() {
		return orientation;
	}
	
	public static void setScreenOrientator(ScreenOrientator s) {
		screenOrientator = s;
	}
	
	public static void setScreenPortrait() {
		orientation = Orientations.PORTRAIT;
		screenOrientator.makePortrait();
	}
	
	public static void setScreenLandscape() {
		orientation = Orientations.LANDSCAPE;
		screenOrientator.makeLandscape();	
	}
	
	public static CGScreen getScreen() {
		return (CGScreen)(((Game)Gdx.app.getApplicationListener()).getScreen());
	}
}
