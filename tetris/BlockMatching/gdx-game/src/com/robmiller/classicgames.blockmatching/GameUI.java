package com.robmiller.classicgames.blockmatching;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.viewport.*;
import android.util.*;

public class GameUI extends Stage
{
	private TextButton.TextButtonStyle buttonStyle; 
	private TextField.TextFieldStyle textStyle;
	private BitmapFont font;
	private BMScreen screen;
	private PieceWindow held;
	private PieceWindow next;
	private TextButton pauseButton;
	private ScoreBox score;
	private Camera camera;
	private Viewport viewport;
	private Table table;
	
	public GameUI(BMScreen s){
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

		final TextButton holdButton = new TextButton(Utils.Buttons.HOLD.getString(),buttonStyle);
		holdButton.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				Utils.getScreen().buttonPressed(Utils.Buttons.HOLD);
				return true;
			}
		});
		textStyle = new TextField.TextFieldStyle();
		textStyle.font = font;
		textStyle.fontColor = Color.WHITE;
		TextField scoreText = new TextField("SCORE", textStyle);
		TextField nextText = new TextField("NEXT", textStyle);
		
		final TextButton mainMenuButton = new TextButton(Utils.Buttons.MENU.getString(),buttonStyle);
		mainMenuButton.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				Utils.getScreen().buttonPressed(Utils.Buttons.MENU);
				return true;
			}
		});
		
		pauseButton = new TextButton(Utils.Buttons.PAUSE.getString(),buttonStyle);
		pauseButton.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				Utils.getScreen().buttonPressed(Utils.Buttons.PAUSE);
				return true;
			}
		});
		
		held = new PieceWindow();
		next = new PieceWindow();
		
		score = new ScoreBox();
		score.setString("1000");
		
		int smallestDimension;

		if (Gdx.graphics.getWidth() > Gdx.graphics.getHeight())	
			smallestDimension = Gdx.graphics.getHeight();
		else
			smallestDimension = Gdx.graphics.getWidth();
		
		table.setFillParent(true);
		table.top().pad(Utils.getUIPadding(),Utils.getBoardBottomLeft().X + Utils.getBoardWidth() + Utils.getUIPadding() * 2,Utils.getBoardBottomLeft().Y,Utils.getUIPadding()); //.right();
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
		score.setString(s + "");	
	}
	
	public void setHeld(Class p) {
		if (p == null) return;
		
		String name = p.toString();
		String[] splitName = name.split("\\.");
		
		switch(splitName[splitName.length-1]) {
			case "IBlock":
				held.setPiece(Utils.Shapes.IBLOCK);
				break;
			case "LBlock":
				held.setPiece(Utils.Shapes.LBLOCK);
				break;
			case "JBlock":
				held.setPiece(Utils.Shapes.JBLOCK);
				break;
			case "SBlock":
				held.setPiece(Utils.Shapes.SBLOCK);
				break;
			case "ZBlock":
				held.setPiece(Utils.Shapes.ZBLOCK);
				break;
			case "TBlock":
				held.setPiece(Utils.Shapes.TBLOCK);
				break;
			case "SquareBlock":
				held.setPiece(Utils.Shapes.QBLOCK);
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
				next.setPiece(Utils.Shapes.IBLOCK);
				break;
			case "LBlock":
				next.setPiece(Utils.Shapes.LBLOCK);
				break;
			case "JBlock":
				next.setPiece(Utils.Shapes.JBLOCK);
				break;
			case "SBlock":
				next.setPiece(Utils.Shapes.SBLOCK);
				break;
			case "ZBlock":
				next.setPiece(Utils.Shapes.ZBLOCK);
				break;
			case "TBlock":
				next.setPiece(Utils.Shapes.TBLOCK);
				break;
			case "SquareBlock":
				next.setPiece(Utils.Shapes.QBLOCK);
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
