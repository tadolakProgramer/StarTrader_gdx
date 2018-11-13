package com.mygdx.game.GameObjekts.SpaceShipParts;


public enum CargoType {

    FUEL(ModuleType.FUEL),
    TITAN(ModuleType.LOSE),
    WATER(ModuleType.LIQUID),
    QUICKSILVER(ModuleType.LIQUID),
    GRAFEN(ModuleType.LOSE),
    HELIUM3(ModuleType.GAS),
    PERSON(ModuleType.HOUSING_MODULE);

    private final ModuleType moduleType;

    CargoType(ModuleType moduleType) {
        this.moduleType = moduleType;
    }

    public ModuleType getModuleType() {
        return moduleType;
    }

}
