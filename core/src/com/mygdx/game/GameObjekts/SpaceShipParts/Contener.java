package com.mygdx.game.GameObjekts.SpaceShipParts;


import com.badlogic.gdx.graphics.Texture;

public class Contener extends ShipModule {


    public Contener(ModuleType moduleType, String name, double capacity,  double cost, int baseFailureRate){
        super(moduleType, name, capacity, cost);
        this.moduleType = moduleType;
        this.name = name;
        this.capacity = capacity;
        this.fill = fill;
        this.cost = cost;
        this.baseFailureRate = baseFailureRate;
    }

    @Override
    public void update(float dt){
        timeToFailure = + dt;
        setFailure();
    }

    private void setFailure() {

        if (timeToFailure > (11 - failureRate) * 100){

        }
    }


    public Contener build(){
        return new Contener(moduleType, name, capacity,  cost, baseFailureRate);
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
    }

}
