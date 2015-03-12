package com.robmiller.classicgames.blockmatching;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.viewport.*;
//import android.util.*;
import com.robmiller.classicgames.*;

public class GameUI extends Stage
{
	private TextButton.TextButtonStyle buttonStyle; 
	private TextField.TextFieldStyle textStyle;
	private BitmapFont font;
	private CGScreen screen;
	private PieceWindow held;
	private PieceWindow next;
	private TextButton pauseButton;
	private ScoreBox score;
	private Camera camera;
	private Viewport viewport;
	private Table table;
	
	public GameUI(CGScreen s){
		screen = s;
		
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ScreenViewport(camera);
		setViewport(viewport);
		
		table = new Table();
		
		font = new BitmapFont();
		font.setScale(2.0f);

		buttonStyle = new TextButton.TextButtonStyle();
		buttonStyle.font = font;
		buttonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("holdbuttonup.png"))));
		buttonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("holdbuttondown.png"))));

		final TextButton holdButton = new TextButton(BMUtils.Buttons.HOLD.getString(),buttonStyle);
		holdButton.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				((GameScreen)(Utils.getScreen())).buttonPressed(BMUtils.Buttons.HOLD);
				return true;
			}
		});
		textStyle = new TextField.TextFieldStyle();
		textStyle.font = font;
		textStyle.fontColor = Color.WHITE;
		TextField scoreText = new TextField("SCORE", textStyle);
		TextField nextText = new TextField("NEXT", textStyle);
		
		final TextButton mainMenuButton = new TextButton(BMUtils.Buttons.MENU.getString(),buttonStyle);
		mainMenuButton.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				((GameScreen)(Utils.getScreen())).buttonPressed(BMUtils.Buttons.MENU);
				return true;
			}
		});
		
		pauseButton = new TextButton(BMUtils.Buttons.PAUSE.getString(),buttonStyle);
		pauseButton.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				((GameScreen)(Utils.getScreen())).buttonPressed(BMUtils.Buttons.PAUSE);
				return true;
			}
		});
		
		held = new PieceWindow();
		next = new PieceWindow();
		
		score = new ScoreBox();
		score.setScore(1000);
		
		int smallestDimension;

		if (Gdx.graphics.getWidth() > Gdx.graphics.getHeight())	
			smallestDimension = Gdx.graphics.getHeight();
		else
			smallestDimension = Gdx.graphics.getWidth();
		
		table.setFillParent(true);
		table.top().pad(BMUtils.getUIPadding(),BMUtils.getBoardBottomLeft().X + BMUtils.getBoardWidth() + BMUtils.getUIPadding() * 2,BMUtils.getBoardBottomLeft().Y,BMUtils.getUIPadding()); //.right();
		table.add(nextText).center();
		table.row();
		table.add(next).minSize(smallestDimension * 0.20f).center();
		table.row();
		table.add().expand().fill();
		table.row();
		table.add(held).minSize(smallestDimension * 0.20f).center();		
		table.row();
		table.add(holdButton).minWidth(smallestDimension * 0.20f).center();
		table.row().padTop(Gdx.graphics.getHeight() * 0.10f);
		table.add(scoreText).center();
		table.row();
		table.add(score).minWidth(smallestDimension * 0.20f).minHeight(smallestDimension * 0.10f).center();
		table.row();
		table.add().expand().fill();
		table.row();
		table.add(mainMenuButton).bottom().center();
		table.row();
		table.add().padTop(smallestDimension * 0.05f);
		table.row();
		table.add(pauseButton).bottom().center();
		
		addActor(table);
	}
	
	public void updateScore(int s) {
		score.setScore(s);	
	}
	
	public void setHeld(Class p) {
		if (p == null) return;
		
		String name = p.toString();
		String[] splitName = name.split("\\.");
		
		switch(splitName[splitName.length-1]) {
			case "IBlock":
				held.setPiece(BMUtils.Shapes.IBLOCK);
				break;
			case "LBlock":
				held.setPiece(BMUtils.Shapes.LBLOCK);
				break;
			case "JBlock":
				held.setPiece(BMUtils.Shapes.JBLOCK);
				break;
			case "SBlock":
				held.setPiece(BMUtils.Shapes.SBLOCK);
				break;
			case "ZBlock":
				held.setPiece(BMUtils.Shapes.ZBLOCK);
				break;
			case "TBlock":
				held.setPiece(BMUtils.Shapes.TBLOCK);
				break;
			case "SquareBlock":
				held.setPiece(BMUtils.Shapes.QBLOCK);
				break;
			default:
				break;
		}
	}
	
	public void setNext(Class p) {
		if (p == null) return;

		String name = p.toString();
		String[] splitName = name.split("\\.");

		switch(splitName[splitName.length-1]) {
			case "IBlock":
				next.setPiece(BMUtils.Shapes.IBLOCK);
				break;
			case "LBlock":
				next.setPiece(BMUtils.Shapes.LBLOCK);
				break;
			case "JBlock":
				next.setPiece(BMUtils.Shapes.JBLOCK);
				break;
			case "SBlock":
				next.setPiece(BMUtils.Shapes.SBLOCK);
				break;
			case "ZBlock":
				next.setPiece(BMUtils.Shapes.ZBLOCK);
				break;
			case "TBlock":
				next.setPiece(BMUtils.Shapes.TBLOCK);
				break;
			case "SquareBlock":
				next.setPiece(BMUtils.Shapes.QBLOCK);
				break;
			default:
				break;
		}
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
	}
}
