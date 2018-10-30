package com.mygdx.game.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.GameObjekts.SpaceObjekt.Planet;
import com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow.Crow;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow.CrowType;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow.ExperienceLevel;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow.ExperienceType;
import com.mygdx.game.GameObjekts.SpaceObjekt.SpaceShipPlayer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.GameScreen;
import com.mygdx.game.Screenns.Hud.Hud;


public class ReadXML {

    public static double readPlayerMoney(){

        Element root = new XmlReader().parse(Gdx.files.internal(MyGdxGame.FILE_PLAYER));
        return root.getFloat("money");
    }

    public static int readPlayerPlanetCount(){

        Element root = new XmlReader().parse(Gdx.files.internal(MyGdxGame.FILE_PLAYER));
        return root.getInt("planetCount");
    }


    public static  Boolean setShipFromXML(SpaceShipPlayer spaceShipPlayer) {

        Element root = new XmlReader().parse(Gdx.files.internal(MyGdxGame.FILE_SPACE_SHIP));

        Element position = root.getChildByName("pos");
        spaceShipPlayer.setPositionOrgin(position.getFloat("x"), position.getFloat("y"));

            for (int k=0; k<14; k++) {
                Element Slot = root.getChildByName("SLOT" + k);

                String TEXT = (Slot.get("ModuleType"));
                if (!TEXT.equals("Empty")){
                    spaceShipPlayer.addModule( k, ModuleType.valueOf(Slot.get("ModuleType")),
                            Slot.get("Name"), Slot.getFloat("Capacity"),
                            Slot.getFloat("Fill"), Slot.getFloat("Cost"),
                            Slot.getFloat("BaseFailureRate"));
                    }
                    else spaceShipPlayer.addModule(k, ModuleType.EMPTY,"Empty", 0,0,0,0);
                }

        return true;
    }

    public static boolean readCaptain(SpaceShipPlayer spaceShipPlayer){

        Element root = new XmlReader().parse(Gdx.files.internal("crows.xml"));

        int personCount = root.getChildCount();

        for (int i=0; personCount>i; i++) {

            Element person = root.getChild(i);

            ExperienceLevel ex1 = new ExperienceLevel(ExperienceType.valueOf(person.get("fet")), person.getFloat("fel"));
            ExperienceLevel ex2 = new ExperienceLevel(ExperienceType.valueOf(person.get("set")), person.getFloat("sel"));

            spaceShipPlayer.persosns.add( new Crow(
                    CrowType.valueOf(root.getChild(i).getName()),
                    person.get("name"),
                    person.getInt("age"),
                    person.getFloat("height"),
                    person.get("gender"),
                    new ExperienceLevel(ExperienceType.valueOf(person.get("fet")), person.getFloat("fel")),
                    new ExperienceLevel(ExperienceType.valueOf(person.get("set")), person.getFloat("sel")),
                    person.getFloat("pay")
            ));

            spaceShipPlayer.addExperience(ex1);
            spaceShipPlayer.addExperience(ex2);
            spaceShipPlayer.housingModuleFill++;
            spaceShipPlayer.modifyFailureRate();
        }

        return true;

    }

    public static boolean readPlanets(MyGdxGame game, GameScreen screen, Hud hud){

        Element root = new XmlReader().parse(Gdx.files.internal(MyGdxGame.FILE_PLANETS));

        int j = root.getChildCount();
        for (int i=0; i < j; i++) {
            Element planet = root.getChild(i);
            screen.planets.add(new Planet
                    (game, hud,
                            planet.getFloat("posx"),
                            planet.getFloat("posy"),
                            (planet.get("texture")),
                            (planet.get("name")),
                            planet.getFloat("rot"),
                            planet.getFloat("Titan"),
                            planet.getFloat("Grafen"),
                            planet.getFloat("Woter"),
                            planet.getFloat("Fuell")));
        }
        return true;
    }
}
