package com.mygdx.game.GameObjekts.SpaceObjekt;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

;

public class Planet extends SpaceObject {

    public double planetScale;
    private double priceCarbon;
    private double priceWoter;
    private double priceFuell;

    public Planet(final MyGdxGame game, float x, float y){
        super(game);
        setScale(0.5f);
        setPosition(x,y);
        rotationSpeed =15;


        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                System.out.println("KLIK_PLANET");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }

}
