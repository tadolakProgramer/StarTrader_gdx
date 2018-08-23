package com.mygdx.game.GameObjekts.SpaceShipParts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpaceShipCocpit extends ShipModule{

    public SpaceShipCocpit(ModuleType moduleType, String name, double capacity, double fill, double cost, double baseFailureRate){
        super(moduleType, name, capacity, cost);


        this.capacity = 2.0;
        this.fill = fill;
        this.baseFailureRate = baseFailureRate;

        this.texture = new Texture("badlogic.jpg");
        this.sprite = new Sprite(texture);

    }
}
