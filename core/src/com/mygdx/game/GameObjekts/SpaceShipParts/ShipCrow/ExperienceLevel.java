package com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow;

public class ExperienceLevel {

    private ExperienceType experienceType;
    private float level;

    public ExperienceLevel(ExperienceType experienceType, float level) {
        this.experienceType = experienceType;
        this.level = level;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public ExperienceType getExperienceType() {
        return experienceType;
    }
}
