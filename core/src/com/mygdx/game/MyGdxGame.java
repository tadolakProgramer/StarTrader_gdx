package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.GameObjekts.SpaceObjekt.Planet;
import com.mygdx.game.Screenns.CreatePlayerScreen;
import com.mygdx.game.Screenns.GameScreen;
import com.mygdx.game.Screenns.SpalshScreen;


public class MyGdxGame extends Game {



	public final static  String GAME_NAME = "Star Trader";
	public final static  int GAME_WIDTH = 1024;
	public final static  int GAME_HEIGHT = 768;
	public final static float SPACE_OBJECT_SCALIBG = GAME_WIDTH/1522;
	public final static float GAME_SCALE = 0.25f;
	public final static float SCROLL_SPEED =10f;

	//Files
	public final static String FILE_SPRITE_ATLAS = "data/planets.pack";
	public final static String FILE_CARGO_ATLAS = "data/cargo.atlas";
	public final static String FILE_SPACESHIP = "data/SpaceShip_Empty.png";
	public final static String FILE_SPACE_SHIP = "data/spaceship.xml";
	public final static String FILE_PLAYER = "data/player.xml";
	public final static String FILE_PLANETS = "data/cars.xml";
	public final static String FILE_SHIP_MODULES = "data/spacemodule.xml";
	//Skins
	public final static String FILE_EARTH_SKIN = "data/skin/flat-earth-ui.json";
	public final static String FILE_UI_SKIN = "data/skin/uiskin.json";

	//
	public final static int TIME_TO_PAYMENT = 300; /* Time to payment in s real time*/

	//
	public  Skin skin;

	public static String DIR;
	public Deque<Screen> kolejka = new ArrayDeque<Screen>();
	public BitmapFont myFont;
	public AssetManager assetManager;
	public TextureAtlas textureAtlas;

	private boolean paused;
	private boolean Jest;
	public Screen gameScreen;

	@Override
	public void create() {

		assetManager = new AssetManager();
		textureAtlas = new TextureAtlas(FILE_SPRITE_ATLAS);
		skin = new Skin(Gdx.files.internal(FILE_EARTH_SKIN));

		assetManager.load(FILE_SPRITE_ATLAS, TextureAtlas.class);

		myFont = new BitmapFont(Gdx.files.internal("fonts/Xspace3.fnt"));
		myFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
				Texture.TextureFilter.Linear);

        this.gameScreen = new GameScreen(this);

		switch(Gdx.app.getType()) {
			case Android:{
				FileHandle handle = Gdx.files.local("data/");
				if (handle.isDirectory()){
					for (FileHandle file: handle.list()) {
						file.copyTo(Gdx.files.local(""));
						Gdx.app.log("File", file.toString());
					}
				}
			}
				// android specific code
			case Desktop:
				// desktop specific code
		}

		Jest = true;

		if (Jest) {
			System.out.println("Jest!");
			this.setScreen(new SpalshScreen(this));
		} else {
			System.out.println("Nie ma!");
			this.setScreen(new CreatePlayerScreen(this));
		}
	}

	@Override
	public void dispose () {
		System.out.println("Koniec");
		if (screen != null) screen.hide();
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

}


