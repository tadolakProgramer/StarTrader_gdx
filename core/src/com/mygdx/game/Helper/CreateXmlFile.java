package com.mygdx.game.Helper;


import com.mygdx.game.GameObjekts.SpaceObjekt.Planet;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.GameScreen;
import com.mygdx.game.Screenns.Hud.Hud;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.util.List;

public class CreateXmlFile {


    public static void crateSpace(MyGdxGame game, GameScreen screen, Hud hud) {

        List<Planet> planets; //= new ArrayList<Planet>();

        CreateSpace createSpace1 = new CreateSpace();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("Planets");
            doc.appendChild(rootElement);
            planets = createSpace1.Create(game, hud);

            for (int z=1;z<=planets.size()-1;z++) {


                            String path = planets.get(z).getPath();

                            // supercars element
                            Element planeta = doc.createElement("planet");
                            rootElement.appendChild(planeta);

                            Node nanme = doc.createElement("name");
                            nanme.appendChild(doc.createTextNode(planets.get(z).getSpaceObjectName()));
                            planeta.appendChild(nanme);

                            Node ntexture = doc.createElement("texture");
                            ntexture.appendChild(doc.createTextNode(path));
                            planeta.appendChild(ntexture);

                            // Create a new Node with the given tag name
                            Node nposx = doc.createElement("posx");
                            float x = planets.get(z).getX();
                            nposx.appendChild(doc.createTextNode(Float.toString(x)));
                            planeta.appendChild(nposx);

                            Node nposy = doc.createElement("posy");
                            float y = planets.get(z).getY();
                            nposy.appendChild(doc.createTextNode(Float.toString(y)));
                            planeta.appendChild(nposy);

                            Node nrot = doc.createElement("rot");
                            float rRotation = planets.get(z).getRotationSpeed();
                            nrot.appendChild(doc.createTextNode(Float.toString(rRotation)));
                            planeta.appendChild(nrot);

                Node nTitan = doc.createElement("Titan");
                double rTitan = planets.get(z).getPriceTitan();
                nTitan.appendChild(doc.createTextNode(Double.toString(rTitan)));
                planeta.appendChild(nTitan);

                Node nRgafen = doc.createElement("Grafen");
                double rGrafen = planets.get(z).getPriceGrafen();
                nRgafen.appendChild(doc.createTextNode(Double.toString(rGrafen)));
                planeta.appendChild(nRgafen);

                Node nWoter = doc.createElement("Woter");
                double rWoter = planets.get(z).getPriceWoter();
                nWoter.appendChild(doc.createTextNode(Double.toString(rWoter)));
                nWoter.setTextContent("123");
                planeta.appendChild(nWoter);

                Node nFuell = doc.createElement("Fuell");
                double rFuell = planets.get(z).getPriceFuell();
                nFuell.appendChild(doc.createTextNode(Double.toString(rFuell)));
                planeta.appendChild(nFuell);

            }


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File((MyGdxGame.FILE_PLANETS)));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
