package com.mygdx.game.GameObjekts.SpaceShipParts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class FuelTank extends ShipModule{


    public FuelTank(ModuleType moduleType, String name, double capacity, double fill, double cost, double baseFailureRate){
        super(moduleType, name, capacity, cost);
        this.capacity = capacity;
        this.fill = fill;
        this.cost = cost;
        this.baseFailureRate = baseFailureRate;


        this.texture = new Texture("badlogic.jpg");
        this.sprite = new Sprite(texture);
    }


}
