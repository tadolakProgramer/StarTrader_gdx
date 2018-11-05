package com.mygdx.game.Screenns.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameObjekts.SpaceObjekt.Planet;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.GameScreen;

import static com.mygdx.game.MyGdxGame.GAME_HEIGHT;
import static com.mygdx.game.MyGdxGame.GAME_WIDTH;

public abstract class AbstractHUD {


    //Scene2D.ui Stage and its own Viewport for HUD
    public Stage stage;
    public Viewport viewport;

    public TextureRegion region;
    protected Skin skin;
    protected Skin skin2;
    protected Window window;
    protected int windowInfoPlanetMarket = 0;
    protected MyGdxGame game;
    protected GameScreen gameScreen;

    protected Image planetImage;
    protected Planet planet;
    protected int planetNumber;
    protected SpriteBatch sb;
    protected Camera camera;

    public AbstractHUD(final GameScreen gameScreen, MyGdxGame game, SpriteBatch sb) {
        this.gameScreen = gameScreen;
        this.game = game;
        this.sb = sb;
        skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
        skin2 = new Skin(Gdx.files.internal("skin/uiskin.json"));

        camera = new OrthographicCamera();
        viewport = new FitViewport(GAME_WIDTH, GAME_HEIGHT, camera);
        stage = new Stage(viewport, sb);
        camera.update();

        gameScreen.multiplexer.addProcessor(stage);

    }

}
