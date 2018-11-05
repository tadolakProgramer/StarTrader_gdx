package com.mygdx.game.GameObjekts.SpaceShipParts;


import com.badlogic.gdx.math.MathUtils;

public class SpaceShipEngine extends ShipModule{

    private static final int MAX_DISTANCE_NO_ERROR = 10000;

    private float speedEngine;
    private float consumptionFuel;
    private float speedEngineSlow;
    private float speedActual;
    private double eventDistance;
    private double totalDistance;
    private double currentDistance;
    private boolean engineError;
    private int eventType;


    public SpaceShipEngine(ModuleType moduleType, String name, double capacity, double cost, int baseFailureRate) {
        super(moduleType, name, capacity, cost);
        speedEngine = 40;
        speedEngineSlow = 2;
        consumptionFuel = 20.50f;
        this. baseFailureRate = baseFailureRate;
    }

    public void update(float dt) {
        distanceControl();
    }

    private void distanceControl() {

        if (failureRate > 0) {
            if (totalDistance > MAX_DISTANCE_NO_ERROR / failureRate) {
                System.out.println("Losowanie: ");
                if (MathUtils.random(1, 20) < failureRate) {
                    engineError = true;
                } else {
                    engineError = false;
                }
            }
        }
    }

    public void addDistance(double distance){
        this.totalDistance = this.totalDistance + distance;
    }

    public float getSpeedEngine() {
        return speedEngine;
    }

    public void setSpeedEngine(float speedEngine) {
        this.speedEngine = speedEngine;
    }

    public float getConsumptionFuel() {
        return consumptionFuel;
    }

    public void setConsumptionFuel(float consumptionFuel) {
        this.consumptionFuel = consumptionFuel;
    }

    public float getSpeedEngineSlow() {
        return speedEngineSlow;
    }

    public void setSpeedEngineSlow(float speedEngineSlow) {
        this.speedEngineSlow = speedEngineSlow;
    }

    public float getSpeedActual() {
        return speedActual;
    }

    public void setSpeedActual(float speedActual) {
        this.speedActual = speedActual;
    }
}
