package com.mygdx.game.GameObjekts.SpaceShipParts;

public class Empty extends ShipModule {

    public Empty(ModuleType moduleType, String name, double capacity, double cost){
        super(moduleType, name, capacity, cost);
        this.moduleType = moduleType;
    }
}
