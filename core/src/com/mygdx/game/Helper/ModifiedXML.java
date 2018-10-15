package com.mygdx.game.Helper;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ModifiedXML {

    public static Boolean setPosition(float x, float y) {

        try {
            String file  = ("spaceship.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            Node ship = doc.getFirstChild();
            Node ship1 = doc.getElementsByTagName("pos").item(0);
            NamedNodeMap attr = ship1.getAttributes();
            Node PosX = attr.getNamedItem("x");
            PosX.setTextContent(Float.toString(x));
            Node PosY = attr.getNamedItem("y");
            PosY.setTextContent(Float.toString(y));


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(file));
            transformer.transform(source, result);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }
}
