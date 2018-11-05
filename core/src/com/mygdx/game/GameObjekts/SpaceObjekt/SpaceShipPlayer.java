package com.mygdx.game.GameObjekts.SpaceObjekt;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameObjekts.SpaceShipParts.CargoType;
import com.mygdx.game.GameObjekts.SpaceShipParts.Contener;
import com.mygdx.game.GameObjekts.SpaceShipParts.Empty;
import com.mygdx.game.GameObjekts.SpaceShipParts.HousingModule;
import com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow.ExperienceLevel;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow.ExperienceType;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow.Person;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipModule;
import com.mygdx.game.GameObjekts.SpaceShipParts.SpaceShipCocpit;
import com.mygdx.game.GameObjekts.SpaceShipParts.SpaceShipEngine;
import com.mygdx.game.Helper.ModifiedXML;
import com.mygdx.game.Helper.ReadXML;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.GameScreen;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.badlogic.gdx.math.MathUtils;

import static com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType.EMPTY;
import static com.mygdx.game.MyGdxGame.GAME_SCALE;



public class SpaceShipPlayer extends SpaceObject {


    public List<ShipModule> schipModules = new ArrayList<ShipModule>();
    public List<Person> persosns = new ArrayList<Person>();
    public Map<ExperienceType, Float> elMap = new HashMap<ExperienceType, Float>();

    public double fuelCapacity;
    public double fuelFill;

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

    //test
    private float navigationFactor;
    private boolean korekt;

    public SpaceShipPlayer(final MyGdxGame game, final GameScreen gameScreen) {
        super(game);
        //this.game = game;
        this.gameScreen = gameScreen;
        setScale(GAME_SCALE);
        this.path = "aliensprite2";
        setTexture(this.path);
        moveVector = new Vector2();
        initialize();


        this.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if (isRun) {
                    gameScreen.createSpaceShipScreen();
                } else {
                    gameScreen.createMarketWindow();
                }

                System.out.println("KLIK");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void initialize() {

        for (int i = 0; i < 14; i++) {
            schipModules.add(i, new Empty(EMPTY, "Empty", 0, 0));
        }

        ReadXML.setShipFromXML(this);

        ReadXML.readCaptain(this);

        spaceShipEngine = new SpaceShipEngine(ModuleType.SPACE_SHIP_ENGINE, "Golem", 1, 1, 100);

        setSpaceObjectName("Tado-044");

        setMoney();
        setPlanetTripCounter();
    }

    public void addModule(int index, ModuleType moduleType, String name, double capacity, double fill, double cost, double baseFailureRate) {

        if (schipModules.get(index).moduleType == EMPTY) {

            switch (moduleType) {
                case COKPIT: {
                    schipModules.set(index, new SpaceShipCocpit(ModuleType.COKPIT, name, capacity, fill, cost, baseFailureRate));
                    //Contener contener = new Contener.Builder().setBaseFailureRate()
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
                    addHousingCapacity(capacity);
                    break;
                }
                case EMPTY: {
                    schipModules.set(index, new Empty(EMPTY, name, capacity, cost));
                    break;
                }
            }
        }
    }

    private void addHousingCapacity(double capacity) {
        this.housingModuleCapacity = this.housingModuleCapacity + capacity;
    }

    private void addLoseCapacity(double capacity) {
        this.loseCapacity = this.loseCapacity + capacity;
    }

    public void addFuelCapacity(double fuelCapacity) {
        this.fuelCapacity = this.fuelCapacity + fuelCapacity;
    }

    public void subFuelCapacity(double fuelCapacity) {
        this.fuelCapacity -= fuelCapacity;
    }

    public void addFuelFill(double fuel) {
        if ((fuel + fuelFill) > this.fuelCapacity) {
            this.fuelFill = fuelCapacity;
        } else {
            this.fuelFill = this.fuelFill + fuel;
        }

    }

    public void addPerson() {

    }

    public void setStart(int planetNumber) {

        this.planetNumber = planetNumber;

        korekt =false;

        targetX = gameScreen.planets.get(planetNumber).getPositionCX();
        targetY = gameScreen.planets.get(planetNumber).getPositionCY();

        navigationFactor = 100 - elMap.get(ExperienceType.NAVIGATE)*10;

        float targetX_temp = MathUtils.random(-navigationFactor, navigationFactor) + targetX;
        float targetY_temp = MathUtils.random(-navigationFactor, navigationFactor) + targetY;

        targetX = targetX_temp;
        targetY = targetY_temp;

        ModifiedXML.writeTargetPositionToXml(targetX, targetY);

        targetName = gameScreen.planets.get(planetNumber).getSpaceObjectName();

        double angle = Math.atan2(targetY - positionC.y, targetX - positionC.x);
        double rotation = Math.toDegrees(angle) + 90;

        spaceShipEngine.setSpeedActual(spaceShipEngine.getSpeedEngine());
        moveVector.set((float) Math.cos(angle), (float) Math.sin(angle));

        isRun = true;

        Action startAction = Actions.parallel(
                Actions.rotateTo((float) rotation, 1.2f),
                Actions.scaleTo(0.5f, 0.5f, 1.2f)
                //Actions.(moveVector.x ,moveVector.y ,20f)
        );

        this.addAction(startAction);

    }

    private void setNewTarget(){

        if (!korekt) {

            targetX = gameScreen.planets.get(planetNumber).getPositionCX();
            targetY = gameScreen.planets.get(planetNumber).getPositionCY();
            double angle = Math.atan2(targetY - positionC.y, targetX - positionC.x);
            double rotation = Math.toDegrees(angle) + 90;
            moveVector.set((float) Math.cos(angle), (float) Math.sin(angle));
            korekt = true;
            Action startAction = Actions.parallel(
                    Actions.rotateTo((float) rotation, 1.2f),
                    //Actions.scaleTo(0.5f, 0.5f, 1.2f)
                    Actions.moveBy(moveVector.x ,moveVector.y ,20f)
            );

            this.addAction(startAction);
        }
    }

    public void update(float dt) {
        setPositionC();
        setLabelPosition();
        setActualSize();
        setNewPosition(dt);
        updateShipModule(dt);
    }

    private void updateShipModule(float dt) {

        for (int i=0; i < schipModules.size(); i++){
            schipModules.get(i).update(dt);
        }


    }

    private void setNewPosition(float dt){

        float distance = Vector2.dst(targetX, targetY, positionC.x, positionC.y);
        float stepDistance = Vector2.dst(positionC.x + moveVector.x, positionC.y + moveVector.y, positionC.x, positionC.y);

        //System.out.println("Distance: "+ distance);

        if (isRun) {
            System.out.println("Distance: " + distance + " SD: "+ stepDistance  + " L: "+  (4 * stepDistance * spaceShipEngine.getSpeedActual() * dt));
            if (distance <= stepDistance ) {

                setStop();
            } else {
                if (distance < navigationFactor * 10){setNewTarget();}
                if (fuelFill <= 0) {
                    spaceShipEngine.setSpeedActual(spaceShipEngine.getSpeedEngineSlow());
                    //spaceShipEngine.addDistance(Vector2.dst( positionC.x, moveVector.x * dt * spaceShipEngine.getSpeedActual() + positionC.x, positionC.y, moveVector.y * dt * spaceShipEngine.getSpeedActual() + positionC.y ));
                    setPositionOrgin(moveVector.x * dt * spaceShipEngine.getSpeedActual() + positionC.x, moveVector.y * dt * spaceShipEngine.getSpeedActual() + positionC.y);
                    fuelFill = 0;
                } else {
                    //System.out.println("Distance: "+ stepDistance*dt*spaceShipEngine.getSpeedActual());
                    spaceShipEngine.addDistance(stepDistance);
                    setPositionOrgin(moveVector.x * dt * spaceShipEngine.getSpeedActual() + positionC.x, moveVector.y * dt * spaceShipEngine.getSpeedActual() + positionC.y);
                    fuelFill = fuelFill - stepDistance * spaceShipEngine.getConsumptionFuel() / 100;
                }
            }
        }

    }


    private void setStop() {
        setPositionOrgin(targetX, targetY);
        spaceShipEngine.setSpeedActual(0);
        isRun = false;
        planetTripCounter++;

        ModifiedXML.writePositionToXml(this.getPositionCX(), this.getPositionCY());
        ModifiedXML.writePlanetCountTripToXml(planetTripCounter);

        Action stopAction = Actions.parallel(
                Actions.rotateTo((float) 180, 1.2f),
                Actions.scaleTo(0.25f, 0.25f, 1.2f)
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

    public void sell(CargoType cargoType, int quantity, double cost) {
        addMoney(quantity * cost);
        ModuleType moduleType = cargoType.getModuleType();
        addCargo(cargoType, quantity * (-1));
        fillingModule(moduleType, quantity * (-1));
    }

    private void addCargo(CargoType cargoType, int quantity) {
        switch (cargoType) {
            case TITAN: {
                addTitan(quantity);
                break;
            }
            /*case FUEL:{
                addFuelFill(quantity);
                break;}*/
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
                break;
            }
            case FUEL: {
                addFuelFill(quantity);
                break;
            }
        }
    }

    private void addTitan(int quantity) {
        titanFill = titanFill + quantity;
    }

    private void subMoney(double v) {
        money = money - v;
        ModifiedXML.writeMoneyToXml(money);
    }

    private void addMoney(double v) {
        money = money + v;
        ModifiedXML.writeMoneyToXml(money);
    }

    private boolean checkMoney(int quantity, double cost) {
        return quantity * cost < money;
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

    public void addExperience(ExperienceLevel ex) {


        if (elMap.size() == 0) {
            elMap.put(ex.getExperienceType(),ex.getLevel());

        } else {
            for (int i = 0; i < elMap.size(); i++) {

                if (elMap.containsKey(ex.getExperienceType())){

                    if (elMap.get(ex.getExperienceType()) <= ex.getLevel()) {
                        elMap.put(ex.getExperienceType(),ex.getLevel());
                        break;
                    }
                }
                else {
                    elMap.put(ex.getExperienceType(),ex.getLevel());
                    break;
                }
            }
        }
    }


    public void modifyFailureRate() {

    for (int i = 1; i < schipModules.size(); i++) {
                ModuleType md = schipModules.get(i).moduleType;
                switch (md) {
                    case FUEL: {
                        if (elMap.containsKey(ExperienceType.MECHANIKS)) {
                            schipModules.get(i).setFailureRate(elMap.get(ExperienceType.MECHANIKS));
                        }
                }
            }

        }

    }


//GET SET **********************************************************************


    public double getMoney() {
        return money;
    }

    public void setMoney() {
        money = ReadXML.readPlayerMoney();
    }

    private void setPlanetTripCounter() {
        planetTripCounter = ReadXML.readPlayerPlanetCount();
    }


}
