package com.mygdx.game.GameObjekts.SpaceShipParts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

public class SpaceShipEngine extends ShipModule{

    private static final int MAX_DISTANCE_NO_ERROR = 1000;


    private double SpeedEngine;
    private double ConsumptionFuel;
    private double SpeedEngineSllow;
    private double SpeedActual;
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


        this.texture = new Texture("badlogic.jpg");
        this.sprite = new Sprite(texture);

    }
}
