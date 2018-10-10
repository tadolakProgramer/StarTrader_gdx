package com.mygdx.game.Screenns.Hud;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.mygdx.game.GameObjekts.SpaceObjekt.Planet;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.GameScreen;


import static com.mygdx.game.MyGdxGame.GAME_HEIGHT;
import static com.mygdx.game.MyGdxGame.GAME_WIDTH;

public class Hud extends  AbstractHUD {


    //Scene2D widgets
    private Label moneyLabel;
    private static Label fuellLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label marioLabel;
    private ProgressBar fuelFillBar;


    private float time = 0;
    private int fps = 0;


    public Hud(final GameScreen gameScreen, MyGdxGame game, SpriteBatch sb){

        super(gameScreen, game, sb);

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Top-Align table
        table.top().pad(10f);
        table.debug();
        //make the table fill the entire stage
        table.setFillParent(true);
        table.setColor(255,1,1,255);

        ProgressBar.ProgressBarStyle progressBarStyle = skin.get("fancy", ProgressBar.ProgressBarStyle.class);
        TiledDrawable tiledDrawable = skin.getTiledDrawable("slider-fancy-knob").tint(skin.getColor("fuell"));
        tiledDrawable.setMinWidth(0);
        progressBarStyle.knobBefore = tiledDrawable;


        //define our labels using the String, and a Label style consisting of a font and color
        moneyLabel = new Label(String.format("%.2f", gameScreen.spaceShipPlayer.getMoney()), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        fuellLabel =new Label(String.format("%.2f", gameScreen.spaceShipPlayer.fuelFill), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        fuelFillBar = new ProgressBar(0.0f, (float) gameScreen.spaceShipPlayer.fuelCapacity, 1, false, skin, "fancy");

        timeLabel = new Label("Money", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        levelLabel = new Label("1-1: ", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        worldLabel = new Label("WORLD: ", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        marioLabel = new Label("Fuel: ", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));

        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add(marioLabel).expandX().padTop(1);
        table.add(worldLabel).expandX().padTop(1);
        table.add(timeLabel).expandX().padTop(1);
        //add a second row to our table
        table.row();
        table.add(fuellLabel).expandX();
        stage.addActor(fuelFillBar);
        table.add(levelLabel).expandX();
        table.add(moneyLabel).expandX();

        //add

        TextButton textButton1 = new TextButton("Start", skin);
        textButton1.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameScreen.setCameraToSpaceSchip();
                return true;
            }
        });

        TextButton textButtonZoom = new TextButton("Start", skin);
        textButtonZoom.setPosition(100,0);
        textButtonZoom.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameScreen.camera.zoom = gameScreen.camera.zoom - 0.01f;
                return true;
            }
        });

        stage.addActor(table);
        stage.addActor(textButton1);
        stage.addActor(textButtonZoom);
        }


    public void createWindowPlanetInfo(String planetName) {

        WindowPlanetInfo windowInfoPlanet = new WindowPlanetInfo(gameScreen, game, sb, planetName);
        stage.addActor(windowInfoPlanet.window);
    }

    public void createWindowPlanetMarket(String planetName) {

        WindowPlanetMarket windowPlanetMarket = new WindowPlanetMarket(gameScreen, game, sb, planetName);
        stage.addActor(windowPlanetMarket.window);

    }

    public void update(float dt) {
        progressBarUpdate();
        time = time +dt;
        fps ++;
        if (time >= 1){
            float fpsA = fps;
           levelLabel.setText("FPS"+fpsA);
           time = 0;
           fps = 0;
        }
        }

    private void progressBarUpdate() {
        fuellLabel.setText(String.format("%.2f", gameScreen.spaceShipPlayer.fuelFill)+"  "+String.format("%.2f", gameScreen.spaceShipPlayer.fuelFill/gameScreen.spaceShipPlayer.fuelCapacity*100)+"$");
        fuelFillBar.setRange(0,(float)gameScreen.spaceShipPlayer.fuelCapacity);
        fuelFillBar.setValue((float)gameScreen.spaceShipPlayer.fuelFill);
        fuelFillBar.setPosition(fuellLabel.getX() + fuellLabel.getWidth()/2 - fuelFillBar.getWidth()/2, fuellLabel.getY()+5 );
    }

    public static void addScore(int value){

    }



}