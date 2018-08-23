package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import java.util.ArrayDeque;
import java.util.Deque;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.GameObjekts.SpaceObjekt.SpaceShipPlayer;
import com.mygdx.game.Screenns.SpalshScreen;



public class MyGdxGame extends Game {

	public SpaceShipPlayer spaceShipPlayer;

	public final static  String GAME_NAME = "Star Trader";
	public final static  int GAME_WIDTH = 800;
	public final static  int GAME_HEIGHT = 600;
	public final static float SPACE_OBJECT_SCALIBG = GAME_WIDTH/1522;
	public final static float GAME_SCALE = 0.5f;
	public final static float SCROLL_SPEED =10f;
	public Deque<Screen> kolejka = new ArrayDeque<Screen>();
//	public final static Skin SKIN = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));

	private boolean paused;

	@Override
	public void create() {
		spaceShipPlayer = new SpaceShipPlayer(this);
		this.setScreen(new SpalshScreen(this));
		//int ilosc = kolejka.size;
			}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

}


