package com.mygdx.game.GameObjekts.SpaceShipParts;

public class FuelTank extends ShipModule{


    public FuelTank(ModuleType moduleType, String name, double capacity, double fill, double cost, double baseFailureRate){
        super(moduleType, name, capacity, cost);
        this.capacity = capacity;
        this.fill = fill;
        this.cost = cost;
        this.baseFailureRate = baseFailureRate;

    }


}
