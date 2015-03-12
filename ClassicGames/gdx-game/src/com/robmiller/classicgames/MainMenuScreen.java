package com.robmiller.classicgames;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import javax.microedition.khronos.opengles.*;
//import android.app.*;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
import com.badlogic.gdx.utils.*;

public class MainMenuScreen extends CGScreen
{
	private Texture background;
	private Image bgImage;
	private ImageButton bmButton;
	private ImageButton gsButton;
	private ImageButton.ImageButtonStyle style;
	private Stage stage;								
	private boolean shouldStartBM = false;
	private boolean shouldStartGS = false;
	private boolean shouldShowGS = false;
	private Table table;
	private boolean isFirstLoop = true;
	private Camera camera;
	private Viewport viewport;
	public MainMenuScreen(Game g) {
		super(g);
	}
	
	@Override
	public void render(float p1)
	{
		Gdx.gl.glClearColor(0.0f,0.0f,0.f,1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
		
		if (shouldStartBM){
			shouldStartBM = false;
			game.setScreen(new com.robmiller.classicgames.blockmatching.GameScreen(game));
		}
		else if (shouldStartGS){
			shouldStartGS = false;
			shouldShowGS = true;
			Utils.setScreenLandscape();
		}
		else if (shouldShowGS) {
			shouldShowGS = false;
			game.setScreen(new com.robmiller.classicgames.gridsweeper.GameScreen(game));
		}
	}

	@Override
	public void resize(int p1, int p2)
	{
		stage.getViewport().update(p1,p2,true);
	}

	@Override
	public void show()
	{
		camera = new OrthographicCamera(Gdx.graphics.getHeight(),Gdx.graphics.getWidth());
		viewport = new ScreenViewport(camera);
		
		table = new Table();
		background = new Texture(Gdx.files.internal("bmbackground.png"));
		bgImage = new Image(background);
		bgImage.setFillParent(true);			
		style = new ImageButton.ImageButtonStyle();
		style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("bmlogoup.png"))));
		style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("bmlogodown.png"))));

		bmButton = new ImageButton(style);
		bmButton.addListener(new ClickListener() {
				@Override
				public void touchUp(InputEvent e, float x, float y, int pointer, int button) {
					shouldStartBM = true;
				}
			});
	
		style = new ImageButton.ImageButtonStyle();
		style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gslogoup.png"))));
		style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gslogodown.png"))));
		
		gsButton = new ImageButton(style);
		gsButton.addListener(new ClickListener(){
				@Override
				public void touchUp(InputEvent e, float x, float y, int pointer, int button) {
					shouldStartGS = true;
				}
		});
		
		table.add(bmButton);
		table.add(gsButton);
		
		table.setFillParent(true);
		
		stage = new Stage();
		stage.setViewport(viewport);
		stage.addActor(bgImage);
		stage.addActor(table);
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide()
	{
		dispose();
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
		super.dispose();
		stage.dispose();
		background.dispose();
	}

}
