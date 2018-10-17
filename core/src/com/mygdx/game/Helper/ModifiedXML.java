package com.mygdx.game.Helper;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ModifiedXML {

    private final static String FILE_SPACE_SHIP = ("spaceship.xml");
    private final static String FILE_PLAYER = ("player.xml");
    private static Document doc;

    public static void setDokument(String file){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeDokument(String file){
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(file));
            transformer.transform(source, result);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Boolean writePositionToXml(float x, float y) {

            setDokument(FILE_SPACE_SHIP);

            Node ship = doc.getFirstChild();
            Node ship1 = doc.getElementsByTagName("pos").item(0);
            NamedNodeMap attr = ship1.getAttributes();
            Node PosX = attr.getNamedItem("x");
            PosX.setTextContent(Float.toString(x));
            Node PosY = attr.getNamedItem("y");
            PosY.setTextContent(Float.toString(y));

            writeDokument(FILE_SPACE_SHIP);

        return true;

    }

    public static Boolean writeTargetPositionToXml(float x, float y) {


            setDokument(FILE_SPACE_SHIP);

            Node ship = doc.getFirstChild();
            Node ship1 = doc.getElementsByTagName("target").item(0);
            NamedNodeMap attr = ship1.getAttributes();
            Node targetX = attr.getNamedItem("x");
            targetX.setTextContent(Float.toString(x));
            Node targetY = attr.getNamedItem("y");
            targetY.setTextContent(Float.toString(y));

        writeDokument(FILE_SPACE_SHIP);


        return true;

    }

    public static Boolean writeMoneyToXml(double m) {


        setDokument(FILE_PLAYER);
            Node ship = doc.getFirstChild();
            Node player = doc.getElementsByTagName("money").item(0);
            player.setTextContent(Double.toString(m));

            writeDokument(FILE_PLAYER);

        return true;

    }

    public static Boolean writePlanetCountTripToXml(int m) {

        setDokument(FILE_PLAYER);
            Node ship = doc.getFirstChild();
            Node player = doc.getElementsByTagName("planetCount").item(0);
            player.setTextContent(Integer.toString(m));

            writeDokument(FILE_PLAYER);
        return true;

    }
}
