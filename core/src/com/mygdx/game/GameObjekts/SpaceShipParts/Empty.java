package com.mygdx.game.GameObjekts.SpaceShipParts;

public class Empty extends ShipModule {

    public Empty(ModuleType moduleType, String name, double capacity, double cost, int index){
        super(moduleType, name, capacity, cost, index);
        this.moduleType = moduleType;
    }
}
