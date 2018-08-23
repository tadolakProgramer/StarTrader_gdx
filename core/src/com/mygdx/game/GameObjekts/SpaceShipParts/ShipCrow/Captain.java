package com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow;

public class Captain extends Person{


    public Captain(String name, int age, float height, String gender, float firstExperienceLevel, ExperienceType firstExperienceType, float pay) {
        super(name, age, height, gender, firstExperienceLevel, firstExperienceType, pay);

        this.crowType = CrowType.CAPITAN;
        this.secondExperienceLevel = 0;
        this.secondExperienceType = ExperienceType.NAVIGATE;


    }
}

