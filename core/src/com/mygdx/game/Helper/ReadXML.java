package com.mygdx.game.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow.Captain;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipCrow.ExperienceType;
import com.mygdx.game.GameObjekts.SpaceObjekt.SpaceShipPlayer;


public class ReadXML {


    public static  Boolean setShipFromXML(SpaceShipPlayer spaceShipPlayer) {

        Element root = new XmlReader().parse(Gdx.files.internal("spaceship.xml"));

        Element position = root.getChildByName("pos");
        spaceShipPlayer.setPosition(position.getFloat("x"), position.getFloat("y"));

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
}
