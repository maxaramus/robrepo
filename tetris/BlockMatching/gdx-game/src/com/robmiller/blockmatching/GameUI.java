package com.robmiller.blockmatching;
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
	private TextButton holdButton;
	private TextButton.TextButtonStyle holdStyle; 
	private BitmapFont font;
	private BMScreen screen;
	private HeldWindow held;
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

		holdStyle = new TextButton.TextButtonStyle();
		holdStyle.font = font;
		holdStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("holdbuttonup.png"))));
		holdStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("holdbuttondown.png"))));

		holdButton = new TextButton(Utils.Buttons.HOLD.getString(),holdStyle);
		
		holdButton.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				Utils.getScreen().buttonPressed(Utils.Buttons.HOLD);
				return true;
			}
		});
		
		held = new HeldWindow();

		int smallestDimension;

		if (Gdx.graphics.getWidth() > Gdx.graphics.getHeight())	
			smallestDimension = Gdx.graphics.getHeight();
		else
			smallestDimension = Gdx.graphics.getWidth();
		
		table.setFillParent(true);
		table.top(); //.right();
		table.add(held).padLeft(Utils.getBoardBottomLeft().X + Utils.getBoardWidth() + Utils.getUIPadding() * 2).padRight(Utils.getUIPadding()).padTop(Utils.getUIPadding()).minSize(smallestDimension * 0.20f);		
		table.row();
		table.add(holdButton).padLeft(Utils.getBoardBottomLeft().X + Utils.getBoardWidth() + Utils.getUIPadding() * 2).padRight(Utils.getUIPadding());
		
		addActor(table);
	}
	
	public void setHeld(Class p) {
		if (p == null) return;
		
		String name = p.toString();
		String[] splitName = name.split("\\.");
		
		switch(splitName[splitName.length-1]) {
			case "IBlock":
				held.setCurrent(Utils.Shapes.IBLOCK);
				break;
			case "LBlock":
				held.setCurrent(Utils.Shapes.LBLOCK);
				break;
			case "JBlock":
				held.setCurrent(Utils.Shapes.JBLOCK);
				break;
			case "SBlock":
				held.setCurrent(Utils.Shapes.SBLOCK);
				break;
			case "ZBlock":
				held.setCurrent(Utils.Shapes.ZBLOCK);
				break;
			case "TBlock":
				held.setCurrent(Utils.Shapes.TBLOCK);
				break;
			case "SquareBlock":
				held.setCurrent(Utils.Shapes.QBLOCK);
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
