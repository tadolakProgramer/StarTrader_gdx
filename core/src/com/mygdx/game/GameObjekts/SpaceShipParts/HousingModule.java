package com.mygdx.game.GameObjekts.SpaceShipParts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class HousingModule extends ShipModule{


    public HousingModule(ModuleType moduleType, String name, double capacity, double fill, double cost, double baseFailureRate) {
        super(moduleType, name, capacity, cost);
        this.capacity = capacity;
        this.cost = cost;
        this.baseFailureRate = baseFailureRate;

    }
}
