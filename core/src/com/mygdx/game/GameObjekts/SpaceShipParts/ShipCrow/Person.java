package com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow;

public abstract class Person {

    protected CrowType crowType;
    private String name;
    private int age;
    private float height;
    private String gender;
    private float firstExperienceLevel;
    private ExperienceType firstExperienceType;
    protected float secondExperienceLevel;
    protected ExperienceType secondExperienceType;
    private float pay;

    public Person(String name, int age, float height, String gender, float firstExperienceLevel, ExperienceType firstExperienceType, float pay) {
        this.crowType = crowType;
        this.name = name;
        this.age = age;
        this.height = height;
        this.gender = gender;
        this.firstExperienceLevel = firstExperienceLevel;
        this.firstExperienceType = firstExperienceType;
        this.secondExperienceLevel = secondExperienceLevel;
        this.secondExperienceType = secondExperienceType;
        this.pay = pay;
    }

    public Person(CrowType crowType, String name, int age, float height, String gender, float firstExperienceLevel, ExperienceType firstExperienceType, float secondExperienceLevel, ExperienceType secondExperienceType, float pay) {
        this.crowType = crowType;
        this.name = name;
        this.age = age;
        this.height = height;
        this.gender = gender;
        this.firstExperienceLevel = firstExperienceLevel;
        this.firstExperienceType = firstExperienceType;
        this.secondExperienceLevel = secondExperienceLevel;
        this.secondExperienceType = secondExperienceType;
        this.pay = pay;
    }

    public float getFirstExperienceLevel() {
        return firstExperienceLevel;
    }

    public ExperienceType getFirstExperienceType() {
        return firstExperienceType;
    }

    public float getSecondExperienceLevel() {
        return secondExperienceLevel;
    }

    public ExperienceType getSecondExperienceType() {
        return secondExperienceType;
    }
}
