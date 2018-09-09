package com.mygdx.game.GameObjekts.SpaceObjekt;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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

import static com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType.EMPTY;
import static com.mygdx.game.MyGdxGame.GAME_SCALE;


public class SpaceShipPlayer extends SpaceObject {

    private double positionX;
    private double positionY;

    public List<ShipModule> schipModules = new ArrayList<ShipModule>();
    public List<Person> persosns = new ArrayList<Person>();

    public double fuelCapacity;
    public double fuelFill;
    public double housingModuleCapacity;
    public double housingModuleFill;

    private SpaceShipCocpit spaceShipCocpit;
    private HousingModule housingModule;
    private SpaceShipEngine spaceShipEngine;

    private int planetNumber;

    private Vector2 moveVector;
    private float targetX;
    private float targetY;

    private double money;
    private MyGdxGame game;
    private GameScreen gameScreen;

    public SpaceShipPlayer(final MyGdxGame game, GameScreen gameScreen){
        super(game);
        this.game = game;
        this.gameScreen = gameScreen;
        setTexture(game.textureAtlas.findRegion("aliensprite2").name);
        setScale(GAME_SCALE);
        moveVector = new Vector2();
        initialize();


        this.addListener(new ClickListener(){
            @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                game.setScreen(new SpaceShipScreen(game));

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
                case CONTENER: {
                    schipModules.set(index, new Contener(ModuleType.CONTENER, name, capacity, fill, cost, baseFailureRate));
                    break;
                }
                case SPACE_SHIP_ENGINE: {
                    schipModules.set(index, new SpaceShipEngine(ModuleType.SPACE_SHIP_ENGINE, name, capacity, cost, baseFailureRate));
                    break;
                }
                case FUEL: {
                    schipModules.set(index, new FuelTank(ModuleType.FUEL, name, capacity, fill, cost, baseFailureRate));
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

    public void addFuelCapacity(double fuelCapacity){
        this.fuelCapacity =+ fuelCapacity;
    }

    public void subFuelCapacity(double fuelCapacity){
        this.fuelCapacity -= fuelCapacity;
    }

    public void addFuelFill(double fuel){
        if (fuelCapacity < fuelCapacity + fuel) {
            this.fuelFill =+ fuel;
        }
        else {
            this.fuelFill = fuelCapacity;
        }

    }
    public void addPerson(){

    }

    public void setStart(int planetNumber){

        this.planetNumber = planetNumber;

        targetX = gameScreen.planets.get(planetNumber).getPositionCX();
        targetY = gameScreen.planets.get(planetNumber).getPositionCY();

        double angle = Math.atan2(targetY-positionC.y, targetX-positionC.x);
        double rotation =  Math.toDegrees(angle)+90;

        spaceShipEngine.setSpeedActual(spaceShipEngine.getSpeedEngine());
        moveVector.set((float)Math.cos(angle), (float)Math.sin(angle));

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

        Action stopAction = Actions.parallel(
                //Actions.rotateTo((float) rotation, 1.2f),
                Actions.scaleTo(0.25f,0.25f,1.2f)
        );
        this.addAction(stopAction);
    }


    public void modifyFailureRate(){

        for (int p=0; p < persosns.size(); p++){
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

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getMoney() {
        return money;
    }
}
