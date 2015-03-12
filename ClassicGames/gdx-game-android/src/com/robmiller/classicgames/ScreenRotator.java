package com.robmiller.classicgames;
import com.robmiller.classicgames.gridsweeper.*;
import android.app.*;
import android.content.pm.*;

public class ScreenRotator implements ScreenOrientator
{
	Activity myActivity;
	
	public ScreenRotator(Activity a) {
		myActivity = a;
	}
	
	@Override
	public void makePortrait()
	{
		myActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
	}

	@Override
	public void makeLandscape()
	{
		myActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}

}
