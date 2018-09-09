package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.ImageResolver;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.GameObjekts.SpaceObjekt.Planet;
import com.mygdx.game.GameObjekts.SpaceObjekt.SpaceShipPlayer;
import com.mygdx.game.Screenns.GameScreen;
import com.mygdx.game.Screenns.SpalshScreen;



public class MyGdxGame extends Game {



	public final static  String GAME_NAME = "Star Trader";
	public final static  int GAME_WIDTH = 1024;
	public final static  int GAME_HEIGHT = 768;
	public final static float SPACE_OBJECT_SCALIBG = GAME_WIDTH/1522;
	public final static float GAME_SCALE = 0.25f;
	public final static float SCROLL_SPEED =10f;
	public final static String FILE_SPRITE_ATLAS = "planets.pack";
	public Deque<Screen> kolejka = new ArrayDeque<Screen>();
	public BitmapFont myFont;
//	public final static Skin SKIN = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
	public Skin skin;
	public AssetManager assetManager;
	public TextureAtlas textureAtlas;

	private boolean paused;

	public List<Planet> planets = new ArrayList<Planet>();
	public Screen gameScreen;

	@Override
	public void create() {
		assetManager = new AssetManager();
		textureAtlas = new TextureAtlas(FILE_SPRITE_ATLAS);

		assetManager.load(FILE_SPRITE_ATLAS, TextureAtlas.class);
		//Texture texture = textureAtlas.findRegion("planet1").getTexture();
		//Texture texture = (textureAtlas.findRegion("planet_1").getTexture());


		myFont = new BitmapFont(Gdx.files.internal("fonts/Xspace3.fnt"));
		myFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
				Texture.TextureFilter.Linear);

		this.gameScreen = new GameScreen(this);
		this.setScreen(new SpalshScreen(this));
	}
	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

}


