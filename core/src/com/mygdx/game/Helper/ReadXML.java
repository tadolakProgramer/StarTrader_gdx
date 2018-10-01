package com.mygdx.game.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.GameObjekts.SpaceObjekt.Planet;
import com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow.Captain;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow.ExperienceType;
import com.mygdx.game.GameObjekts.SpaceObjekt.SpaceShipPlayer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.GameScreen;
import com.mygdx.game.Screenns.Hud;


public class ReadXML {


    public static  Boolean setShipFromXML(SpaceShipPlayer spaceShipPlayer) {

        Element root = new XmlReader().parse(Gdx.files.internal("spaceship.xml"));

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

        Element root = new XmlReader().parse(Gdx.files.internal("captain.xml"));

        spaceShipPlayer.persosns.add(new Captain(
                root.get("name"),
                root.getInt("age"),
                root.getFloat("height"),
                root.get("gender"),
                root.getFloat("fel"),
                ExperienceType.valueOf(root.get("fet")),
                root.getFloat("pay")
                        //root.getFloat("sel"),
                        //ExperienceType.valueOf(root.get("set")
                ));
        spaceShipPlayer.housingModuleFill++;

        spaceShipPlayer.modifyFailureRate();
        return true;

    }

    public static boolean readPlanets(MyGdxGame game, GameScreen screen, Hud hud){

        Element root = new XmlReader().parse(Gdx.files.internal("cars.xml"));

        int j = root.getChildCount();
        for (int i=1; i <= j; i++) {
            Element planet = root.getChildByName("planet" + i);
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
