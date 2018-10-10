package com.mygdx.game.GameObjekts.SpaceObjekt;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.Hud.Hud;

;
import static com.mygdx.game.MyGdxGame.GAME_SCALE;

public class Planet extends SpaceObject {

    public double planetScale;
    private double priceTitan;
    private double priceGrafen;
    private double priceWoter;
    private double priceFuell;
    private MyGdxGame game;

    private Hud hud;
/**
    public Planet(final MyGdxGame game, float x, float y){
        super(game);
        this.game = game;
        setScale(GAME_SCALE);
        setPosition(x,y);
        rotationSpeed =15;
        addClickListener();
    }
*/
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
