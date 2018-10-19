package com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow;

public class Crow extends Person {

    public Crow(CrowType crowType, String name, int age, float height, String gender, ExperienceLevel firstExperienceLevel, float pay) {
        super(name, age, height, gender, firstExperienceLevel, pay);

        this.crowType = crowType;
        this.secondExperienceLevel = new ExperienceLevel(ExperienceType.NAVIGATE, 10);


    }
}
