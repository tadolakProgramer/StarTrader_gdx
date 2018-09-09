package com.mygdx.game.Screenns;


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
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.mygdx.game.GameObjekts.SpaceObjekt.Planet;
import com.mygdx.game.MyGdxGame;


import static com.mygdx.game.MyGdxGame.GAME_HEIGHT;
import static com.mygdx.game.MyGdxGame.GAME_WIDTH;


/**
 * Created by brentaureli on 8/17/15.
 */
public class Hud implements Disposable{

    //Scene2D.ui Stage and its own Viewport for HUD
    public Stage stage;
    public Viewport viewport;


    //Mario score/time Tracking Variables
    private Integer worldTimer;
    private boolean timeUp; // true when the world timer reaches 0
    private float timeCount;
    private static Integer score;

    //Scene2D widgets
    private Label moneyLabel;
    private static Label fuellLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label marioLabel;
    private ProgressBar fuelFillBar;
    private Label titanPriceLabel;
    private Label grafenPriceLabel;
    private Label woterPriceLabel;
    private Label fuellPriceLabel;
    private Image planetImage;
    private Planet planet;
    private int planetNumber;

    public TextureRegion region;

    private Skin skin;
    private Window window;
    private int windowInfoPlanetCount = 0;
    private MyGdxGame game;
    private GameScreen gameScreen;

    public Hud(final GameScreen gameScreen, MyGdxGame game, SpriteBatch sb){

        this.gameScreen = gameScreen;
        this.game = game;
        skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));

        //define our tracking variables
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        viewport = new FitViewport(GAME_WIDTH, GAME_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        gameScreen.multiplexer.addProcessor(stage);

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

        //add our table to the stage

        TextButton textButton1 = new TextButton("Start", skin);
        textButton1.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gameScreen.setCameraToSpaceSchip();
            }
        });
        stage.addActor(table);
        stage.addActor(textButton1);
        }


    public void createWindowPlanetInfo(String planetName) {

        windowInfoPlanetCount++;

        if (windowInfoPlanetCount<=1) {

            gameScreen.multiplexer.addProcessor(stage);

            window = new Window(planetName, skin);

            region = new TextureRegion();

            window.debug();

            window.setColor(Color.BLACK);
            window.getTitleLabel().setColor(Color.CHARTREUSE);
            window.setSize(400.0f, 400.0f);
            window.setPosition(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f, Align.center);

            Button button = new Button(skin, "close");
            window.getTitleTable().add(button).padRight(1.0f);

            Table winTable = new Table();
            //winTable.debug();
            window.add(winTable).grow().pad(10);
            winTable.align(1);
            stage.addActor(window);
            winTable.top();


            for (int i = 0; i <= gameScreen.planets.size() - 1; i++) {
                if (gameScreen.planets.get(i).getSpaceObjectName().equals(planetName)) {
                    planet = gameScreen.planets.get(i);
                    planetNumber = i;
                    region = game.textureAtlas.findRegion(planet.getPath());
                    planetImage = new Image(game.textureAtlas.findRegion(planet.getPath()));

                    planetImage.setSize(10,10);
                    titanPriceLabel = new Label(String.format("%.2f", planet.getPriceTitan())+" T$", skin, "titan");
                    titanPriceLabel.setFontScale(1.5f);
                    grafenPriceLabel = new Label(String.format("%.2f", planet.getPriceGrafen()), skin, "grafen");
                    grafenPriceLabel.setFontScale(1.5f);
                    woterPriceLabel = new Label(String.format("%.2f", planet.getPriceWoter()), skin, "woter");
                    woterPriceLabel.setFontScale(1.5f);
                    fuellPriceLabel = new Label(String.format("%.2f", planet.getPriceFuell()), skin, "fuell");
                    fuellPriceLabel.setFontScale(1.5f);
                    break;
                }
            }


            Label titanLabel = new Label("Titan: ", skin, "titan");
            titanLabel.setFontScale(1.5f);

            Label grafenLabel = new Label("Grafen", skin, "grafen");
            grafenLabel.setFontScale(1.5f);

            Label woterLabel = new Label("Woter", skin, "woter");
            woterLabel.setFontScale(1.5f);

            Label fuellLabel = new Label("Fuell", skin, "fuell");
            fuellLabel.setFontScale(1.5f);


            TextButton textButton1 = new TextButton("Start", skin);
            TextButton textButton2 = new TextButton("Close", skin);

            window.row().pad(5);
            winTable.row().colspan(2);
            winTable.add(planetImage).size(100,100);

            winTable.row().pad(5);
            winTable.add(titanLabel).expandX().left();
            winTable.add(titanPriceLabel).expandX().right();

            winTable.row().pad(5);
            winTable.add(grafenLabel).expandX().left();
            winTable.add(grafenPriceLabel).expandX().right();


            winTable.row().pad(5);
            winTable.add(woterLabel).expandX().left();
            winTable.add(woterPriceLabel).expandX().right();

            winTable.row().pad(5);
            winTable.add(fuellLabel).expandX().left();
            winTable.add(fuellPriceLabel).expand().right();

            winTable.row();
            winTable.add(textButton1).expand().center().pad(10);
            winTable.add(textButton2).expand().center().pad(10);


            textButton2.addListener(new ClickListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    window.remove();
                    windowInfoPlanetCount--;
                    gameScreen.multiplexer.removeProcessor(stage);
                }
            });
            textButton1.addListener(new ClickListener() {
                @Override
                public void  touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    gameScreen.spaceShipPlayer.setStart(planetNumber);
                    window.remove();
                    windowInfoPlanetCount--;
                    gameScreen.multiplexer.removeProcessor(stage);
                }
            });
        }
        else{
            windowInfoPlanetCount--;
        }
    }
    public void update(float dt) {
        progressBarUpdate();
        windowInfoPlanetUpdate(dt);

        }

    private void windowInfoPlanetUpdate(float dt) {
        if (windowInfoPlanetCount >= 1) {
            planetImage.setOrigin(planetImage.getImageWidth() / 2, planetImage.getImageHeight() / 2);
            planetImage.rotateBy(planet.getRotationSpeed() * dt);
        }
    }

    private void progressBarUpdate() {
        fuellLabel.setText(String.format("%.2f", gameScreen.spaceShipPlayer.fuelFill));
        fuelFillBar.setValue((float)gameScreen.spaceShipPlayer.fuelFill);
        fuelFillBar.setPosition(fuellLabel.getX() + fuellLabel.getWidth()/2 - fuelFillBar.getWidth()/2, fuellLabel.getY()+5 );
    }

    public static void addScore(int value){

    }

    @Override
    public void dispose() { stage.dispose(); }

    public boolean isTimeUp() { return timeUp; }
}