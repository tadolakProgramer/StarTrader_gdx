package com.mygdx.game.GameObjekts.SpaceObjekt;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameObjekts.SpaceShipParts.CargoType;

public class Ware {

    private CargoType cargoType;
    private double price;

    public Ware(CargoType cargoType) {
        this.cargoType = cargoType;
        this.price = randomPrice(cargoType);
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setPrice (double price){
        this.price = price;
    }

    public double randomPrice(CargoType cargoType) {

        double rmin = 0;
        double rmax = 0;

        switch (cargoType) {
            case FUEL: {
                rmin = 5;
                rmax = 10;
                break;
            }
            case TITAN:{
                rmin = 5;
                rmax = 10;
                break;
            }
            case GRAFEN:{
                rmin = 15;
                rmax = 20;
                break;
            }
            case WATER:{
                rmin = 50;
                rmax = 60;
                break;
            }
            case HELIUM3:{
                rmin = 500;
                rmax = 510;
                break;
            }
        }
        return this.price = MathUtils.random((float) rmin, (float) rmax);
    }

    public double getPrice() {
        return price;
    }
}
