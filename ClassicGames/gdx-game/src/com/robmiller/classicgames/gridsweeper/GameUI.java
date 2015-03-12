package com.robmiller.classicgames.gridsweeper;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.viewport.*;
//import android.util.*;
import com.robmiller.classicgames.*;
import android.util.*;

public class GameUI extends Stage
{
	private TextButton.TextButtonStyle buttonStyle; 
	private TextField.TextFieldStyle textStyle;
	private BitmapFont font;
	private CGScreen screen;
	private TextButton mainMenuButton;
	private TextButton newGameButton;
	private TextButton pauseButton;
	private ImageButton flagButton;
	private Camera camera;
	private Viewport viewport;
	private Table table;
	private ScoreBox timeBox;
	
	public GameUI(CGScreen s){
		screen = s;
		
		camera = new OrthographicCamera(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
		viewport = new ScreenViewport(camera);
		setViewport(viewport);
		
		font = new BitmapFont();
		font.setScale(2.0f);
		font.setColor(1,1,1,1);
	
		timeBox = new ScoreBox();
		
		ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
		imageButtonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gs_flagged.png"))));
		imageButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("gs_flagged_down.png"))));
		
		flagButton = new ImageButton(imageButtonStyle);
		flagButton.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				((GameScreen)(Utils.getScreen())).buttonPressed(GSUtils.Buttons.FLAG);
				return true;
			}
		});
		
		buttonStyle = new TextButton.TextButtonStyle();
		buttonStyle.font = font;
		buttonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("holdbuttonup.png"))));
		buttonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("holdbuttondown.png"))));

		mainMenuButton = new TextButton(GSUtils.Buttons.MENU.getString(),buttonStyle);
		mainMenuButton.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				((GameScreen)(Utils.getScreen())).buttonPressed(GSUtils.Buttons.MENU);
				return true;
			}
		});
		
		newGameButton = new TextButton(GSUtils.Buttons.NEWGAME.getString(), buttonStyle);
		newGameButton.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				((GameScreen)(Utils.getScreen())).buttonPressed(GSUtils.Buttons.NEWGAME);
				return true;
			}
		});
		
		
		pauseButton = new TextButton(GSUtils.Buttons.PAUSE.getString(),buttonStyle);
		pauseButton.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				((GameScreen)(Utils.getScreen())).buttonPressed(GSUtils.Buttons.PAUSE);
				return true;
			}
		});
	}
	
	@Override
	public void draw(){
		super.draw();	
	}
	
	@Override
	public void dispose(){
		super.dispose();
		font.dispose();
	}
	
	public void resize(int w, int h) {
		getViewport().update(w,h,true);
		camera.translate(0,h,0);
	
		TextField.TextFieldStyle style = new TextField.TextFieldStyle();
		style.font = font;
		style.fontColor = Color.WHITE;
		
		TextField time = new TextField("TIME", style);
		
		timeBox.setScore(0);
		
		table = new Table();
		table.setFillParent(true);
		table.top().pad(h - w + GSUtils.getUIPadding(),GSUtils.getBoardBottomLeft().X + GSUtils.getBoardWidth() + GSUtils.getUIPadding() * 2,GSUtils.getBoardBottomLeft().Y,GSUtils.getUIPadding()); //.right();
		table.row();
		table.add(time).center();
		table.row();
		table.add(timeBox).minWidth(w * GSUtils.UI_WIDTH).center();
		table.row().padTop(GSUtils.getUIPadding());
		table.add().expand().fill();
		table.row();
		table.add(flagButton).expand().fill().bottom().center();
		table.row();
		table.add(newGameButton).bottom().center();
		table.row();
		table.add(mainMenuButton).bottom().center();
		table.row();
		table.add(pauseButton).bottom().center();
		table.row();
		table.add().expand().fill();
		addActor(table);
	}
}
