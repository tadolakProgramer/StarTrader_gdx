package com.mygdx.game.GameObjekts.SpaceObjekt;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameObjekts.SpaceShipParts.CargoType;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.Hud.Hud;

;
import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.MyGdxGame.GAME_SCALE;

public class Planet extends SpaceObject {

    public double planetScale;
    private double priceTitan;
    private double priceGrafen;
    private double priceWoter;
    private double priceFuell;
    private MyGdxGame game;
    private float timeToRandom;
    private Ware ware;
    private CargoType cargoType;

    private List <Ware> wares = new ArrayList<>();

    private Hud hud;

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
        newRandom();
        addClickListener();
        }

    private void newRandom() {

        wares.add(new Ware(CargoType.FUEL));
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

        timeToRandom = timeToRandom + dt;

        if (timeToRandom > rotationSpeed*100){
            changePrice();
            timeToRandom = 0;
        }

    }

    public void changePrice(){
        priceFuell = priceFuell + MathUtils.random((float)priceFuell*-0.049f, (float)priceFuell*0.051f);
        priceTitan = priceTitan + MathUtils.random((float)priceTitan*-0.049f, (float)priceTitan*0.051f);
        priceGrafen = priceGrafen + MathUtils.random((float)priceGrafen*-0.049f, (float)priceGrafen*0.051f);
        priceWoter = priceWoter + MathUtils.random((float)priceWoter*-0.049f, (float)priceWoter*0.051f);

        hud.showDlgNewPrice(spaceObjectName);

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
