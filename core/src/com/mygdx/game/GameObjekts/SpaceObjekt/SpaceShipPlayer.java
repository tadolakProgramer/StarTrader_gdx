package com.mygdx.game.GameObjekts.SpaceObjekt;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameObjekts.SpaceShipParts.CargoType;
import com.mygdx.game.GameObjekts.SpaceShipParts.Contener;
import com.mygdx.game.GameObjekts.SpaceShipParts.Empty;
import com.mygdx.game.GameObjekts.SpaceShipParts.FuelTank;
import com.mygdx.game.GameObjekts.SpaceShipParts.HousingModule;
import com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow.ExperienceType;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow.Person;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipModule;
import com.mygdx.game.GameObjekts.SpaceShipParts.SpaceShipCocpit;
import com.mygdx.game.GameObjekts.SpaceShipParts.SpaceShipEngine;
import com.mygdx.game.Helper.ReadXML;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.GameScreen;
import com.mygdx.game.Screenns.SpaceShipScreen;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.GameObjekts.SpaceShipParts.CargoType.TITAN;
import static com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType.EMPTY;
import static com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType.LOSE;
import static com.mygdx.game.MyGdxGame.GAME_SCALE;


public class SpaceShipPlayer extends SpaceObject {


    public List<ShipModule> schipModules = new ArrayList<ShipModule>();
    public List<Person> persosns = new ArrayList<Person>();

    public double fuelCapacity;
    public double fuelFill;

    public double titanCapacity = 100;
    public double titanFill;

    public double housingModuleCapacity;
    public double housingModuleFill;

    private SpaceShipCocpit spaceShipCocpit;
    private HousingModule housingModule;
    private SpaceShipEngine spaceShipEngine;
    private boolean isRun;

    private int planetNumber;

    private Vector2 moveVector;
    private float targetX;
    private float targetY;
    public String targetName;

    private double money;
    private MyGdxGame game;
    private GameScreen gameScreen;
    private int planetTripCounter;  //Do zdobywania nagród
    private double loseCapacity;
    private double loseFill;
    private double waterFill;

    public SpaceShipPlayer(final MyGdxGame game,  final GameScreen gameScreen){
        super(game);
        //this.game = game;
        this.gameScreen = gameScreen;
        setScale(GAME_SCALE);
        this.path = "aliensprite2";
        //this.setTexture(game.textureAtlas.findRegion("aliensprite2").name);
        setTexture(this.path);
        moveVector = new Vector2();
        initialize();


        this.addListener(new ClickListener(){
            @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if (isRun) {
                    gameScreen.createSpaceShipScreen();
                }
                else {
                    gameScreen.createMarketWindow();
                }

                System.out.println("KLIK");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void initialize() {

        for (int i=0; i<14; i++){
            schipModules.add(i, new Empty(EMPTY, "Empty",0,0));
        }

        ReadXML.setShipFromXML(this);
        ReadXML.readCaptain(this);

        spaceShipEngine = new SpaceShipEngine(ModuleType.SPACE_SHIP_ENGINE,"Golem",1,1,100);

        setSpaceObjectName("Tado-044");

        money = 1234.56;
    }

    public void addModule(int index, ModuleType moduleType, String  name, double capacity, double fill, double cost, double baseFailureRate) {

        if (schipModules.get(index).moduleType ==  EMPTY) {

            switch (moduleType) {
                case COKPIT: {
                    schipModules.set(index, new SpaceShipCocpit(ModuleType.COKPIT, name, capacity, fill, cost, baseFailureRate));
                    break;
                }
                case GAS: {
                    schipModules.set(index, new Contener(ModuleType.GAS, name, capacity, fill, cost, baseFailureRate));
                    break;
                }
                case LIQUID: {
                    schipModules.set(index, new Contener(ModuleType.LIQUID, name, capacity, fill, cost, baseFailureRate));
                    break;
                }
                case LOSE: {
                    schipModules.set(index, new Contener(ModuleType.LOSE, name, capacity, fill, cost, baseFailureRate));
                    addLoseCapacity(capacity);
                    break;
                }
                case SPACE_SHIP_ENGINE: {
                    schipModules.set(index, new SpaceShipEngine(ModuleType.SPACE_SHIP_ENGINE, name, capacity, cost, baseFailureRate));
                    break;
                }
                case FUEL: {
                    schipModules.set(index, new Contener(ModuleType.FUEL, name, capacity, fill, cost, baseFailureRate));
                    addFuelCapacity(capacity);
                    addFuelFill(fill);
                    break;
                }
                case HOUSING_MODULE: {
                    schipModules.set(index, new HousingModule(ModuleType.HOUSING_MODULE, name, capacity, fill, cost, baseFailureRate));
                    break;
                }
                case EMPTY: {
                    schipModules.set(index, new Empty(EMPTY, name, capacity, cost));
                    break;
                }
            }
        }
    }

    private void addLoseCapacity(double capacity) {
        this.loseCapacity = this.loseCapacity + capacity;
    }

    public void addFuelCapacity(double fuelCapacity){
        this.fuelCapacity = this.fuelCapacity + fuelCapacity;
    }

    public void subFuelCapacity(double fuelCapacity){
        this.fuelCapacity -= fuelCapacity;
    }

    public void addFuelFill(double fuel){
        if ((fuel + fuelFill) > this.fuelCapacity) {
            this.fuelFill = fuelCapacity;
        }
        else {
            this.fuelFill = this.fuelFill + fuel;
        }

    }
    public void addPerson(){

    }

    public void setStart(int planetNumber){

        this.planetNumber = planetNumber;

        targetX = gameScreen.planets.get(planetNumber).getPositionCX();
        targetY = gameScreen.planets.get(planetNumber).getPositionCY();
        targetName = gameScreen.planets.get(planetNumber).getSpaceObjectName();

        double angle = Math.atan2(targetY-positionC.y, targetX-positionC.x);
        double rotation =  Math.toDegrees(angle)+90;

        spaceShipEngine.setSpeedActual(spaceShipEngine.getSpeedEngine());
        moveVector.set((float)Math.cos(angle), (float)Math.sin(angle));

        isRun = true;

        Action startAction = Actions.parallel(
                Actions.rotateTo((float) rotation, 1.2f),
                Actions.scaleTo(0.5f,0.5f,1.2f)
                //Actions.(moveVector.x ,moveVector.y ,20f)
        );

        this.addAction(startAction);

    }

    public void update(float dt) {
        setPositionC();
        setLabelPosition();
        setActualSize();


        float distance = Vector2.dst2(targetX, targetY, positionC.x, positionC.y);
        if (distance <= 0.3*spaceShipEngine.getSpeedActual()*dt) {
            setStop();
        } else {
            if (fuelFill <= 0) {
                spaceShipEngine.setSpeedActual(1f);
                setPositionOrgin(moveVector.x * dt * spaceShipEngine.getSpeedActual() + positionC.x, moveVector.y * dt * spaceShipEngine.getSpeedActual() + positionC.y);
                fuelFill = 0;
            } else {
                float stepDistance = Vector2.dst2( positionC.x + moveVector.x,  positionC.y + moveVector.y, positionC.x, positionC.y);
                setPositionOrgin(moveVector.x * dt * spaceShipEngine.getSpeedActual() + positionC.x, moveVector.y * dt * spaceShipEngine.getSpeedActual() + positionC.y);
                fuelFill = fuelFill - stepDistance * spaceShipEngine.getConsumptionFuel() / 100;
            }
        }
    }

    private void setStop() {
        setPositionOrgin(targetX, targetY);
        spaceShipEngine.setSpeedActual(0);
        isRun = false;
        planetTripCounter++;

        Action stopAction = Actions.parallel(
                //Actions.rotateTo((float) rotation, 1.2f),
                Actions.scaleTo(0.25f,0.25f,1.2f)
        );
        this.addAction(stopAction);
    }

    public void buy(CargoType cargoType, int quantity, double cost) {

        if (checkMoney(quantity, cost) && checkFill(cargoType, quantity)) {

            subMoney(quantity * cost);
            ModuleType moduleType = cargoType.getModuleType();
            fillingModule(moduleType, quantity);
            addCargo(cargoType, quantity);
        }
    }

    private void addCargo(CargoType cargoType, int quantity) {
        switch (cargoType){
            case TITAN:{
                addTitan(quantity);
                break;}
            case FUEL:{
                addFuelFill(quantity);
                break;}
            case WATER: {
                addWater(quantity);
                break;
            }
            }
        }

    private void addWater(int quantity) {
        waterFill = waterFill + quantity;
    }



    private void fillingModule(ModuleType moduleType, int quantity) {
        switch (moduleType) {
            case LOSE: {
                loseFill = loseFill + quantity;
            }
            case FUEL:{
                addFuelFill(quantity);
            }
        }
    }

    private void addTitan(int quantity) {
        titanFill = titanFill+quantity;
    }

    private void subMoney(double v) {
        money = money - v;
    }

    private boolean checkMoney(int quantity, double cost){
        if (quantity * cost < money){
            return true;
        }
        else return false;
    }

    private boolean checkFill(CargoType cargoType, int quantity) {
        ModuleType moduleType = cargoType.getModuleType();
        switch (moduleType) {
            case LOSE: {
                if (loseFill + quantity <= loseCapacity) {
                    return true;
                }
                break;
            }
            case FUEL: {
                if (fuelFill + quantity <= fuelCapacity) {
                    return true;
                }
                    break;
                }
            }
        return false;
    }


    public void modifyFailureRate(){

        for (int p = 0; p < persosns.size(); p++){
            ExperienceType firstExperienceType =  persosns.get(p).getFirstExperienceType();
            ExperienceType secondExperienceType = persosns.get(p).getSecondExperienceType();

                ExperienceType set = secondExperienceType;

        for (int i=1; i < schipModules.size(); i++){
                ModuleType md = schipModules.get(i).moduleType;
                switch (md) {
                    case FUEL: {
                        if (firstExperienceType == ExperienceType.MECHANIKS) {
                            schipModules.get(i).setFailureRate(persosns.get(p).getFirstExperienceLevel());
                        }
                        if (secondExperienceType == ExperienceType.MECHANIKS) {
                            schipModules.get(i).setFailureRate(persosns.get(p).getSecondExperienceLevel());
                        }
                    }
                }
            }

        }

    }
//GET SET **********************************************************************


    public double getMoney() {
        return money;
    }
}
