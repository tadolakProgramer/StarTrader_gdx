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
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.GameScreen;

public class WindowPlanetMarket extends AbstractHUD {

    private Label titanPriceLabel;
    private Label grafenPriceLabel;
    private Label woterPriceLabel;
    private Label fuellPriceLabel;

    public WindowPlanetMarket(final GameScreen gameScreen, MyGdxGame game, SpriteBatch sb, String planetName ) {
        super(gameScreen, game, sb);

        windowInfoPlanetMarket=1;

        if (windowInfoPlanetMarket<=1) {

            gameScreen.multiplexer.addProcessor(stage);

            window = new Window(planetName, skin);
            region = new TextureRegion();

            window.setColor(Color.BLACK);
            window.getTitleLabel().setColor(Color.CHARTREUSE);
            window.setSize(400.0f, 400.0f);
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


            TextButton textButtonClose = new TextButton("Close", skin);

            TextButton textButtonBuyTitan =  new TextButton("Buy", skin);
            TextField textField = new TextField("", skin);

            TextButton textButtonSellTitan =  new TextButton("Sell", skin);

            TextButton textButtonBuyFuel =  new TextButton("Buy", skin);
            TextButton textButtonSellFuel =  new TextButton("Sell", skin);

            window.row().pad(5);
            winTable.row().colspan(4);
            winTable.add(planetImage).size(100,100);

            winTable.row().pad(5);
            winTable.add(titanLabel).expandX().left();
            winTable.add(titanPriceLabel).expandX().right();
            winTable.add(textButtonBuyTitan).left().pad(5);
            winTable.add(textField).padLeft(10.0f);
            winTable.add(textButtonSellTitan).left().pad(5);

            winTable.row().pad(5);
            winTable.add(grafenLabel).expandX().left();
            winTable.add(grafenPriceLabel).expandX().right();


            winTable.row().pad(5);
            winTable.add(woterLabel).expandX().left();
            winTable.add(woterPriceLabel).expandX().right();

            winTable.row().pad(5);
            winTable.add(fuellLabel).expandX().left();
            winTable.add(fuellPriceLabel).expand().right();
            winTable.add(textButtonBuyFuel).left().pad(5);
            winTable.add(textButtonSellFuel).left().pad(5);

            winTable.row();
            winTable.row().colspan(4);
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
