package com.robmiller.blockmatching;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import javax.microedition.khronos.opengles.*;
import android.app.*;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.utils.viewport.*;

public class MainMenuScreen extends BMScreen
{
	private Texture background = new Texture(Gdx.files.internal("bmbackground.png"));
	private Image bgImage = new Image(background);
	private Stage stage = new Stage();
	
	public MainMenuScreen(Game g) {
		super(g);
		setNext(new GameScreen(g));
	}
	
	@Override
	public void render(float p1)
	{
		Gdx.gl.glClearColor(0.0f,0.0f,0.f,1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
		
		if (Gdx.input.justTouched())
			game.setScreen(getNext());
	}

	@Override
	public void resize(int p1, int p2)
	{
	}

	@Override
	public void show()
	{
		stage.addActor(bgImage);
	}

	@Override
	public void hide()
	{
		// TODO: Implement this method
	}

	@Override
	public void pause()
	{
		// TODO: Implement this method
	}

	@Override
	public void resume()
	{
		// TODO: Implement this method
	}

	@Override
	public void dispose()
	{
		// TODO: Implement this method
	}

}
