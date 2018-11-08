package com.mygdx.game.GameObjekts.SpaceShipParts;

public class HousingModule extends ShipModule{


    public HousingModule(ModuleType moduleType, String name, double capacity, double cost, int baseFailureRate) {
        super(moduleType, name, capacity, cost);
        this.capacity = capacity;
        this.cost = cost;
        this.baseFailureRate = baseFailureRate;

    }
}
