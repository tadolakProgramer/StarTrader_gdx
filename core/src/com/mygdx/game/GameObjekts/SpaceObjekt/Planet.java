package com.mygdx.game.GameObjekts.SpaceObjekt;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

;

public class Planet extends SpaceObject {

    public double planetScale;
    private double priceTitan;
    private double priceGrafen;
    private double priceWoter;
    private double priceFuell;

    public Planet(final MyGdxGame game, float x, float y){
        super(game);
        setScale(0.5f);
        setPosition(x,y);
        rotationSpeed =15;
         addClickListener();
    }

    public Planet(final MyGdxGame game, float x, float y, Texture texture, String name, float speedRot, double priceT, double priceG, double priceW, double priceF) {
        super(game);
        setScale(0.5f);
        setPosition(x,y);
        rotationSpeed = speedRot;
        setSpaceObjectName(name);
        priceTitan = priceT;
        priceGrafen = priceG;
        priceWoter = priceW;
        priceFuell = priceF;
        setTexture(texture);
        addClickListener();
    }

    private void addClickListener(){
        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                System.out.println("KLIK_PLANET");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

}
