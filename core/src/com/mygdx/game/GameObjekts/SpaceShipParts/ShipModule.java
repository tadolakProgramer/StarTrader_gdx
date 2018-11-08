package com.mygdx.game.GameObjekts.SpaceShipParts;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow.ExperienceType;

import java.util.HashMap;
import java.util.Map;

public abstract class ShipModule {

    public ModuleType moduleType;

    public String name;
    public double capacity;
    public double fill;
    public double cost;
    public int baseFailureRate;
    public int failureRate; //lower i beater
    protected Texture texture;
    protected float timeToFailure;
    private CargoType cargoType;

    public Map<CargoType, Double> capacitys = new HashMap<CargoType, Double>();

    public ShipModule(ModuleType moduleType, String name, double capacity, double cost){
        this.failureRate = this.baseFailureRate;
        this.moduleType = moduleType;
        this.name = name;
    }

    public double addCargo(CargoType cargoType, double quantity){

        if (moduleType.equals(cargoType.getModuleType())) {

            if (fill + quantity <= capacity) {
                fill = fill + quantity;
                if (capacitys.size() == 0) {
                    capacitys.put(cargoType, quantity);
                    quantity = 0;
                } else {
                    for (int i = 0; i < capacitys.size(); quantity++) {
                        if (capacitys.containsKey(cargoType)) {
                            capacitys.put(cargoType, quantity + capacitys.get(cargoType));
                            break;
                        } else {
                            capacitys.put(cargoType, quantity);
                            quantity = 0;
                            break;
                        }
                    }
                }
            } else {
                quantity = quantity - (capacity - fill);
                if (capacitys.size() == 0) {
                    capacitys.put(cargoType, capacity);
                    fill = capacity;
                } else {
                    for (int z = 0; z < capacitys.size(); z++) {
                        if (capacitys.containsKey(cargoType)) {
                            capacitys.put(cargoType, capacity);
                            fill = capacity;
                            break;
                        }
                    }
                }
            }
        }
        return quantity;
    }

    public void subCargo(int i){
        if(capacity - i >= 0){
        this.fill =-i;}
    }

    public void setFailureRate(int experienceLevel) {

        this.failureRate = baseFailureRate - experienceLevel;
        if (failureRate < 1) {
            failureRate =1;
        }

    }



    public void update(float dt){

    }

    public ModuleType getModuleType() {
        return moduleType;
    }
}
