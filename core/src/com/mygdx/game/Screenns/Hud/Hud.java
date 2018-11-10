package com.mygdx.game.Screenns.Hud;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

import com.mygdx.game.GameObjekts.SpaceShipParts.CargoType;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.GameScreen;


import static com.mygdx.game.MyGdxGame.GAME_HEIGHT;
import static com.mygdx.game.MyGdxGame.GAME_WIDTH;

public class Hud extends  AbstractHUD {



    //Scene2D widgets
    private final Label dateLabel;
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
        moneyLabel = new Label(String.format("%.2f", gameScreen.spaceShipPlayer.getMoney()), skin, "grafen");
        fuellLabel =new Label(String.format("%.2f", gameScreen.spaceShipPlayer.fuelFill), skin, "grafen");
        fuelFillBar = new ProgressBar(0.0f, (float) gameScreen.spaceShipPlayer.fuelCapacity, 1, false, skin, "fancy");
        dateLabel = new Label(gameScreen.getDateOfGame(),skin, "grafen");


        timeLabel = new Label("Money", skin, "grafen");
        levelLabel = new Label("1-1: ", skin, "grafen");
        worldLabel = new Label("WORLD: ", skin, "grafen");
        marioLabel = new Label("Fuel: ",skin, "grafen");

        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add(marioLabel).expandX().padTop(1);
        table.add(worldLabel).expandX().padTop(1);
        table.add(timeLabel).expandX().padTop(1);
        table.add(new Label("Date: ", skin, "grafen")).expandX();
        //add a second row to our table
        table.row();
        table.add(fuellLabel).expandX();
        stage.addActor(fuelFillBar);
        table.add(levelLabel).expandX();
        table.add(moneyLabel).expandX();
        table.add(dateLabel).expandX();

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

    public void showDlgPayment() {
        WindowPaymentList windowPaymentList = new WindowPaymentList(gameScreen, game, sb);
        stage.addActor(windowPaymentList.dlgListPayment);
    }

    public void update(float dt) {
        progressBarUpdate();
        moneyLabel.setText(String.format("%.2f", gameScreen.spaceShipPlayer.getMoney()));
        dateLabel.setText(gameScreen.getDateOfGame());
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
        fuellLabel.setText(String.format("%.2f", gameScreen.spaceShipPlayer.getFill(CargoType.FUEL))+"  "+String.format("%.2f", gameScreen.spaceShipPlayer.fuelFill/gameScreen.spaceShipPlayer.fuelCapacity*100)+"$");
        fuelFillBar.setRange(0,(float)gameScreen.spaceShipPlayer.fuelCapacity);
        fuelFillBar.setValue((float)gameScreen.spaceShipPlayer.fuelFill);
        fuelFillBar.setPosition(fuellLabel.getX() + fuellLabel.getWidth()/2 - fuelFillBar.getWidth()/2, fuellLabel.getY()+5 );
    }

    public static void addScore(int value){

    }

    public void showDlgNewPrice(String planetName) {

        final Dialog dlgNewPrice = new Dialog("New price ", skin);
        TextButton btnMain = new TextButton("Close", skin);
        dlgNewPrice.text("New price on "+ planetName);
        dlgNewPrice.button(btnMain);
        dlgNewPrice.setSize(200,200);
        dlgNewPrice.setPosition(GAME_WIDTH / 2.0f - 100, GAME_HEIGHT / 2.0f - 100);
        stage.addActor(dlgNewPrice);

        btnMain.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dlgNewPrice.remove();
                return true;
            }
        });
    }

    public void showDlgLowMony() {

        Dialog dlgNewPrice = new Dialog("Pozor! ", skin);
        TextButton btnMain = new TextButton("Main", skin);
        TextButton btnTryAgain = new TextButton("Try Again", skin);
        dlgNewPrice.text("To low money or capacity ");
        dlgNewPrice.button(btnTryAgain);
        dlgNewPrice.button(btnMain);
        dlgNewPrice.setSize(200,200);
        dlgNewPrice.setPosition(GAME_WIDTH / 2.0f - 100, GAME_HEIGHT / 2.0f - 100);
        stage.addActor(dlgNewPrice);
    }

}