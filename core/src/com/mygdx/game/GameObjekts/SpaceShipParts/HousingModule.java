package com.mygdx.game.GameObjekts.SpaceShipParts;

public class HousingModule extends ShipModule implements Modules{


    public HousingModule(ModuleType moduleType, String name, double capacity, double cost, int index, int baseFailureRate) {
        super(moduleType, name, capacity, cost, index);
        this.capacity = capacity;
        this.cost = cost;
        this.baseFailureRate = baseFailureRate;

    }

    @Override
    public void distanceControl() {

    }

    @Override
    public void randomFailure() {

    }
}
