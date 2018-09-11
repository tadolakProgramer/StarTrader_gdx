package com.mygdx.game.GameObjekts.SpaceShipParts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Contener extends ShipModule {


    public Contener(ModuleType moduleType, String name, double capacity, double fill, double cost, double baseFailureRate){
        super(moduleType, name, capacity, cost);
        this.moduleType = moduleType;
        this.name = name;
        this.capacity = capacity;
        this.fill = fill;
        this.cost = cost;
    }



}
