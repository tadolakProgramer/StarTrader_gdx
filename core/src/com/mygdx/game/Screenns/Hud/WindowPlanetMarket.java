package com.mygdx.game.Screenns.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.GameObjekts.SpaceShipParts.CargoType;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.GameScreen;

public class WindowPlanetMarket extends AbstractHUD {

    private  double fuelPrice;
    private Label titanPriceLabel;
    private double titanPrice;
    private double grafenPrice;
    private Label grafenPriceLabel;
    private Label woterPriceLabel;
    private Label fuellPriceLabel;
    private TextField.TextFieldFilter filter;

    public WindowPlanetMarket(final GameScreen gameScreen, MyGdxGame game, SpriteBatch sb, String planetName ) {
        super(gameScreen, game, sb);

        windowInfoPlanetMarket=1;

        if (windowInfoPlanetMarket<=1) {

            gameScreen.multiplexer.addProcessor(stage);

            window = new Window(planetName, skin2);
            region = new TextureRegion();

            window.setColor(Color.BLACK);
            window.getTitleLabel().setColor(Color.CHARTREUSE);
            window.setSize(500.0f, 400.0f);
            window.setPosition(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f, Align.center);

            Button button = new Button(skin, "close");
            window.getTitleTable().add(button).padRight(1.0f);
            window.setBounds(window.getX(), window.getY(), window.getWidth(),window.getHeight());

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
                    titanPrice = planet.getPriceTitan();
                    fuelPrice =  planet.getPriceFuell();
                    titanPriceLabel = new Label(String.format("%.2f", planet.getPriceTitan())+" T$", skin, "titan");
                    titanPriceLabel.setFontScale(1.5f);
                    grafenPriceLabel = new Label(String.format("%.2f", planet.getPriceGrafen())+" T$", skin, "grafen");
                    grafenPriceLabel.setFontScale(1.5f);
                    woterPriceLabel = new Label(String.format("%.2f", planet.getPriceWoter())+" T$", skin, "woter");
                    woterPriceLabel.setFontScale(1.5f);
                    fuellPriceLabel = new Label(String.format("%.2f", planet.getPriceFuell())+" T$", skin, "fuell");
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


            TextButton textButtonClose = new TextButton("Close", skin2);

            //Titan
            TextButton textButtonBuyTitan =  new TextButton("Buy", skin2);
            final TextField textFieldTitan = new TextField("0", skin2);
            TextButton textButtonSellTitan =  new TextButton("Sell", skin2);

            //Grafen
            TextButton textButtonBuyGrafen =  new TextButton("Buy", skin2);
            final TextField textFieldGrafen = new TextField("0", skin2);
            TextButton textButtonSellGrafen =  new TextButton("Sell", skin2);

            //Water
            TextButton textButtonBuyWater =  new TextButton("Buy", skin2);
            final TextField textFieldWater = new TextField("0", skin2);
            TextButton textButtonSellWater =  new TextButton("Sell", skin2);

            //Fuell
            TextButton textButtonBuyFuel =  new TextButton("Buy", skin2);
            final TextField textFieldFuell = new TextField("0", skin2);
            //TextButton textButtonSellFuel =  new TextButton("Sell", skin2);

            /** Planet Image */
            window.row().pad(5);
            winTable.row().colspan(5);
            winTable.add(planetImage).size(100,100);

            /** Titan row */
            winTable.row().pad(5);
            winTable.add(titanLabel).expandX().left();
            winTable.add(titanPriceLabel).expandX().right();
            winTable.add(textButtonBuyTitan).left().pad(5);
            winTable.add(textFieldTitan).size(100,30).pad(5.0f);
            winTable.add(textButtonSellTitan).left().pad(5);

            textFieldTitan.setTextFieldFilter(new TextField.TextFieldFilter() {
                // Accepts only digits
                public boolean acceptChar(TextField textField, char c) {
                    return Character.toString(c).matches("^[0-9]");
                }
            });

            textButtonBuyTitan.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    String t = textFieldTitan.getText();
                    int i = Integer.valueOf(t);
                    gameScreen.spaceShipPlayer.buy(CargoType.TITAN, i, planet.getPriceTitan());
                    textFieldTitan.setText("0");
                    return true;
                }
            });

            textButtonSellTitan.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    String t = textFieldTitan.getText();
                    int i = Integer.valueOf(t);
                    gameScreen.spaceShipPlayer.sell(CargoType.TITAN, i, planet.getPriceTitan());
                    textFieldTitan.setText("0");
                    return true;
                }
            });


            /** Grafen row **/
            winTable.row().pad(5);
            winTable.add(grafenLabel).expandX().left();
            winTable.add(grafenPriceLabel).expandX().right();
            winTable.add(textButtonBuyGrafen).left().pad(5);
            winTable.add(textFieldGrafen).size(100,30).pad(5.0f);
            winTable.add(textButtonSellGrafen).left().pad(5);

            textFieldGrafen.setTextFieldFilter(new TextField.TextFieldFilter() {
                // Accepts only digits
                public boolean acceptChar(TextField textField, char c) {
                    return Character.toString(c).matches("^[0-9]");
                }
            });

            textButtonBuyGrafen.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    String t = textFieldGrafen.getText();
                    int i = Integer.valueOf(t);
                    gameScreen.spaceShipPlayer.buy(CargoType.GRAFEN, i, planet.getPriceGrafen());
                    textFieldGrafen.setText("0");
                    return true;
                }
            });

            textButtonSellGrafen.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    String t = textFieldGrafen.getText();
                    int i = Integer.valueOf(t);
                    gameScreen.spaceShipPlayer.sell(CargoType.GRAFEN, i, planet.getPriceGrafen());
                    textFieldGrafen.setText("0");
                    return true;
                }
            });

            //Water
            winTable.row().pad(5);
            winTable.add(woterLabel).expandX().left();
            winTable.add(woterPriceLabel).expandX().right();

            winTable.add(textButtonBuyWater).left().pad(5);
            winTable.add(textFieldWater).size(100,30).pad(5.0f);
            winTable.add(textButtonSellWater).left().pad(5);

            textFieldWater.setTextFieldFilter(new TextField.TextFieldFilter() {
                // Accepts only digits
                public boolean acceptChar(TextField textField, char c) {
                    return Character.toString(c).matches("^[0-9]");
                }
            });

            textButtonBuyWater.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    String t = textFieldWater.getText();
                    int i = Integer.valueOf(t);
                    gameScreen.spaceShipPlayer.buy(CargoType.WATER, i, planet.getPriceWoter());
                    textFieldWater.setText("0");
                    return true;
                }
            });

            textButtonSellWater.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    String t = textFieldWater.getText();
                    int i = Integer.valueOf(t);
                    gameScreen.spaceShipPlayer.sell(CargoType.WATER, i, planet.getPriceWoter());
                    textFieldWater.setText("0");
                    return true;
                }
            });

            //Fuel
            winTable.row().pad(5);
            winTable.add(fuellLabel).expandX().left();
            winTable.add(fuellPriceLabel).expand().right();
            winTable.add(textButtonBuyFuel).left().pad(5);
            winTable.add(textFieldFuell).size(100,30).pad(5.0f);
            //winTable.add(textButtonSellFuel).left().pad(5);

            textFieldFuell.setTextFieldFilter(new TextField.TextFieldFilter() {
                // Accepts only digits
                public boolean acceptChar(TextField textField, char c) {
                    if (Character.toString(c).matches("^[0-9]")) {
                        return true;
                    }
                    return false;
                }
            });

            textButtonBuyFuel.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    String t = textFieldFuell.getText();
                    int i = Integer.valueOf(t);
                    gameScreen.spaceShipPlayer.buy(CargoType.FUEL, i, fuelPrice);
                    textFieldFuell.setText("0");
                    return true;
                }
            });

            winTable.row();
            winTable.row().colspan(5);
            winTable.add(textButtonClose).expand().center().pad(10);


            textButtonClose.addListener(new ClickListener() {
                @Override
                public void  touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    window.remove();
                    windowInfoPlanetMarket--;
                    gameScreen.multiplexer.removeProcessor(stage);
                }
            });
        }
        else{
            windowInfoPlanetMarket--;
        }
    }
    }
