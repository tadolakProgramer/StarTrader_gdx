package com.mygdx.game.GameObjekts.SpaceShipParts;

import java.util.Random;

public class SpaceShipEngine extends ShipModule{

    private static final int MAX_DISTANCE_NO_ERROR = 1000;


    private float SpeedEngine;
    private float ConsumptionFuel;
    private float SpeedEngineSllow;
    private float SpeedActual;
    private double eventDistance;
    private double TotalDistance;
    private double CurrentDistance;
    private boolean EngineError;
    private int eventType;
    //private MessageFragment mMessageFragment;

    //Activity mActivity = MainGKActivity;

    Random event = new Random();

    public SpaceShipEngine(ModuleType moduleType, String name, double capacity, double cost, double baseFailureRate) {
        super(moduleType, name, capacity, cost);
        SpeedEngine = 40;
        ConsumptionFuel = 20.50f;

    }

    public float getSpeedEngine() {
        return SpeedEngine;
    }

    public void setSpeedEngine(float speedEngine) {
        SpeedEngine = speedEngine;
    }

    public float getConsumptionFuel() {
        return ConsumptionFuel;
    }

    public void setConsumptionFuel(float consumptionFuel) {
        ConsumptionFuel = consumptionFuel;
    }

    public float getSpeedEngineSllow() {
        return SpeedEngineSllow;
    }

    public void setSpeedEngineSllow(float speedEngineSllow) {
        SpeedEngineSllow = speedEngineSllow;
    }

    public float getSpeedActual() {
        return SpeedActual;
    }

    public void setSpeedActual(float speedActual) {
        SpeedActual = speedActual;
    }
}
