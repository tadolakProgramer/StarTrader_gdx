package com.mygdx.game.GameObjekts.SpaceShipParts;


public enum CargoType {

    TITAN(ModuleType.LOSE),
    WATER(ModuleType.LIQUID),
    FUEL(ModuleType.FUEL);

    private final ModuleType moduleType;

    CargoType(ModuleType moduleType) {
        this.moduleType = moduleType;
    }

    public ModuleType getModuleType() {
        return moduleType;
    }
}
