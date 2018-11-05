package com.mygdx.game.GameObjekts.SpaceShipParts;

import com.badlogic.gdx.graphics.Texture;

public abstract class ShipModule {

    public ModuleType moduleType;

    public String name;
    public double capacity;
    public double fill;
    public double cost;
    public double baseFailureRate;
    public double failureRate;
    protected Texture texture;
    protected float timeToFailure;

    public ShipModule(ModuleType moduleType, String name, double capacity, double cost){
        this.failureRate = this.baseFailureRate;
        this.moduleType = moduleType;
        this.name = name;
    }

    public void addCargo(double i){
        if(capacity + i<= capacity){
        this.fill =+i;}
    }

    public void subCargo(double i){
        if(capacity - i >= 0){
        this.fill =-i;}
    }

    public void setFailureRate(double experienceLevel) {

        this.failureRate = baseFailureRate - (baseFailureRate * experienceLevel/100);

    }

    public void update(float dt){

    }
}
