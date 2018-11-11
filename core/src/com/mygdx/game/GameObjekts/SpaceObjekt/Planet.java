package com.mygdx.game.GameObjekts.SpaceObjekt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameObjekts.SpaceShipParts.CargoType;
import com.mygdx.game.Helper.ModifiedXML;
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
    private float timeToChangePrice;
    private Ware ware;
    private CargoType cargoType;

    public List <Ware> wares = new ArrayList<>();

    private Hud hud;

    /** For create new space **/
    public Planet(final MyGdxGame game, Hud hud, float x, float y, String name) {
        super(game);
        this.path = "planet_"+MathUtils.random(1, 16);
        this.hud = hud;
        setScale(GAME_SCALE);
        setPosition(x,y);
        rotationSpeed = MathUtils.random(10f, 15f);
        setSpaceObjectName(name);
        priceTitan = MathUtils.random(10f, 15f);
        priceGrafen = MathUtils.random(15f, 20f);
        priceWoter = MathUtils.random(150f, 160f);
        priceFuell  = MathUtils.random(5f,10f);
        setTexture(path);
        newRandom();
        addClickListener();
        }

        /** For read from XML **/
    public Planet(final MyGdxGame game, Hud hud, float x, float y, String path, String name, float speedRot, List wares) {
        super(game);
        this.path = path;
        this.hud = hud;
        setScale(GAME_SCALE);
        setPosition(x, y);
        rotationSpeed = speedRot;
        setSpaceObjectName(name);
        this.wares = wares;
        setTexture(path);
        newRandom();
        addClickListener();
    }

    private void newRandom() {

        boolean rerandom;

        wares.add(new Ware(cargoType.values()[MathUtils.random(2, CargoType.values().length) - 1]));
        setPrice(0);

        int z = wares.size();

        for (int w = z; w < 3; w++) {
            rerandom = false;
            do {
                rerandom = false;
                cargoType = (cargoType.values()[MathUtils.random(2, CargoType.values().length) - 1]);
                for (int i = 0; i < w ; i++){
                    if (cargoType.equals(wares.get(i).getCargoType())){
                        rerandom = true;
                    }
                }
            }
            while (rerandom);
            wares.add(new Ware(cargoType));
            setPrice(w);
        }

        wares.add(new Ware(CargoType.FUEL));
        setPrice(3);
    }

    private void setPrice(int i2) {
        wares.get(i2).setPrice(wares.get(i2).randomPrice(wares.get(i2).getCargoType()));
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

        timeToChangePrice = timeToChangePrice + dt;

        if (timeToChangePrice > rotationSpeed * 100){
            changePrice();
            timeToChangePrice = 0;
        }

    }

    public void changePrice(){
        priceFuell = priceFuell + MathUtils.random((float)priceFuell*-0.049f, (float)priceFuell*0.051f);
        priceTitan = priceTitan + MathUtils.random((float)priceTitan*-0.049f, (float)priceTitan*0.051f);
        priceGrafen = priceGrafen + MathUtils.random((float)priceGrafen*-0.049f, (float)priceGrafen*0.051f);
        priceWoter = priceWoter + MathUtils.random((float)priceWoter*-0.049f, (float)priceWoter*0.051f);

        ModifiedXML.writeNewPriceToXml(spaceObjectName, priceTitan, priceGrafen, priceWoter, priceFuell);

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
