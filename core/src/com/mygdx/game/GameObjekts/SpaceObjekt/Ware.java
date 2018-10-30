package com.mygdx.game.GameObjekts.SpaceObjekt;

import com.mygdx.game.GameObjekts.SpaceShipParts.CargoType;

public class Ware {

    private CargoType cargoType;
    private double price;

    public Ware(CargoType cargoType) {
        this.cargoType = cargoType;
        this.price = 1.0;
    }

    public CargoType getCargoType() {
        return cargoType;
    }

}
