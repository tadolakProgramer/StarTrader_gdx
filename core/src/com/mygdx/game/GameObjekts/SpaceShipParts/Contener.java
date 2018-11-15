package com.mygdx.game.GameObjekts.SpaceShipParts;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Contener extends ShipModule {

    private double currentDistance;
    private static final int MAX_DISTANCE_NO_ERROR = 1000;

    public Contener(ModuleType moduleType, String name, double capacity,  double cost, int index, int baseFailureRate){
        super(moduleType, name, capacity, cost, index);
        this.moduleType = moduleType;
        this.name = name;
        this.capacity = capacity;
        this.cost = cost;
        this.baseFailureRate = baseFailureRate;
        this.failureRate = baseFailureRate;
    }

    @Override
    public void update(float dt){
        timeToFailure = + dt;
        setFailure();
    }


    private void setFailure() {
        distanceControl();
    }

    public void addDistance(double distance){
        currentDistance = currentDistance + distance;
    }

    private void distanceControl() {

        if ((failureRate > 0) && (!isModuleError())){
            if (currentDistance > MAX_DISTANCE_NO_ERROR / failureRate) {
                currentDistance = 0;
                if (MathUtils.random(1, 20) < failureRate) {
                    randomFailure();
                } else {
                    moduleError = false;
                }
            }

        }
    }

    private void randomFailure() {
        int error = MathUtils.random(0, 100) + experienceLevel;
        System.out.println("LosowanieModule: " + error + " "+this.moduleType+ " "+ index);

        if (error < 8) {
            for (int i = 0; i < CargoType.values().length; i++) {
                if (capacitys.containsKey(CargoType.values()[i])) {
                    capacitys.put((CargoType.values()[i]), capacitys.get(CargoType.values()[i]) * 0.9f);
                    textEror = "You lost " + (10 - error) + "%" + CargoType.values()[i];
                    setError();
                    break;
                }
            }
        }
    }


    private void setError() {
        errorIsRead = false;
        moduleError = true;
    }




    public Contener build(){
        return new Contener(moduleType, name, capacity,  cost, index, baseFailureRate);
    }

    public static class Builder{

        private ModuleType moduleType;
        private String name;
        private double capacity;
        private double fill;
        private double cost;
        private double baseFailureRate;
        private double failureRate;
        private Texture texture;
        private int index;

        public Builder(){

        }

        public Builder setModuleType(ModuleType moduleType) {
            this.moduleType = moduleType;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCapacity(double capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder setFill(double fill) {
            this.fill = fill;
            return this;
        }

        public Builder setCost(double cost) {
            this.cost = cost;
            return this;
        }

        public Builder setBaseFailureRate(double baseFailureRate) {
            this.baseFailureRate = baseFailureRate;
            return this;
        }

        public Builder setFailureRate(double failureRate) {
            this.failureRate = failureRate;
            return this;
        }

        public Builder setTexture(Texture texture) {
            this.texture = texture;
            return this;
        }

        public Builder setIndex(int index) {
            this.index = index;
            return this;
        }
    }

}
