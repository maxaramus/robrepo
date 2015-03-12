package com.robmiller.classicgames.blockmatching;
import com.badlogic.gdx.*;

public class BMScreen implements Screen
{
	public boolean showNext = false;
	public boolean showPrevious = false;
	protected Game game;
	private Screen next = null;
	private Screen previous = null;
	
	public BMScreen(Game g) {
		game = g;
	}
	
	public void gameOver() {
		
	}
	
	public void updateScore(int s) {	
	}
	
	public void buttonPressed(Utils.Buttons button) {	
	}
	
	public void setNext(Screen s) {
		next = s;
	}
	
	public void setPrevious(Screen s) {
		previous = s;
	}
	
	public Screen getNext() {
		return next;
	}
	
	public Screen getPrevious() {
		return previous;
	}
	
	@Override
	public void render(float p1)
	{
	}

	@Override
	public void resize(int p1, int p2)
	{
	}

	@Override
	public void show()
	{
	}

	@Override
	public void hide()
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void dispose()
	{
	}
	
}
