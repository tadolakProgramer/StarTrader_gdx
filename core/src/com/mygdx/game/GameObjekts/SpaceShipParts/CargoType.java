package com.mygdx.game.GameObjekts.SpaceShipParts;


public enum CargoType {

    TITAN(ModuleType.LOSE),
    WOTER(ModuleType.LIQUID);

    private final ModuleType moduleType;

    CargoType(ModuleType moduleType) {
        this.moduleType = moduleType;
    }

    public ModuleType getModuleType() {
        return moduleType;
    }
}
