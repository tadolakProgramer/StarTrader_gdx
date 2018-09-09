package com.mygdx.game.GameObjekts.SpaceObjekt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.GameScreen;
import com.mygdx.game.Screenns.Hud;

;import static com.mygdx.game.MyGdxGame.GAME_HEIGHT;
import static com.mygdx.game.MyGdxGame.GAME_SCALE;
import static com.mygdx.game.MyGdxGame.GAME_WIDTH;

public class Planet extends SpaceObject {

    public double planetScale;
    private double priceTitan;
    private double priceGrafen;
    private double priceWoter;
    private double priceFuell;
    private MyGdxGame game;

    public Stage stage;
    public Viewport viewport;

    //Mario score/time Tracking Variables
    private Integer worldTimer;
    private boolean timeUp; // true when the world timer reaches 0
    private float timeCount;
    private static Integer score;

    //Scene2D widgets
    private Label countdownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label marioLabel;
    private Hud hud;

    public Planet(final MyGdxGame game, float x, float y){
        super(game);
        this.game = game;
        setScale(GAME_SCALE);
        setPosition(x,y);
        rotationSpeed =15;
        addClickListener();
    }

    public Planet(final MyGdxGame game, Hud hud, float x, float y, String path, String name, float speedRot, double priceT, double priceG, double priceW, double priceF) {
        super(game);
        this.path = path;
        this.hud = hud;
        setScale(GAME_SCALE);
        setPosition(x,y);
        rotationSpeed = speedRot;
        setSpaceObjectName(name);
        priceTitan = priceT;
        priceGrafen = priceG;
        priceWoter = priceW;
        priceFuell = priceF;
        setTexture(path);
        addClickListener();
        }

    private void addClickListener(){
        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hud.createWindowPlanetInfo(spaceObjectName);
                System.out.println("KLIK_PLANET");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void update(float dt) {
        setPositionC();
        setRotations(dt);
        setLabelPosition();
        setActualSize();
    }


    public double getPriceTitan() {
        return priceTitan;
    }

    public double getPriceGrafen() {
        return priceGrafen;
    }

    public double getPriceWoter() {
        return priceWoter;
    }

    public double getPriceFuell() {
        return priceFuell;
    }

}
