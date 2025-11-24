package database;

import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigManager {
    public static Connection  getConnection() throws SQLException, ParserConfigurationException, IOException, SAXException {
        String fileName = "POSApp\\src\\main\\resources\\com\\example\\posapp\\config.xml";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        //builder is like the product
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(fileName));
        document.getDocumentElement().normalize();


        Element databaseElement = (Element) document.getElementsByTagName("database").item(0);

        String urlString = databaseElement
                .getElementsByTagName("url").item(0)
                .getTextContent().trim();

        String username = databaseElement
                .getElementsByTagName("user").item(0)
                .getTextContent().trim();

        String passwordString = databaseElement
                .getElementsByTagName("password").item(0)
                .getTextContent().trim();



        Connection connection = DriverManager.getConnection(urlString, username, passwordString);
        return connection;
    }
}
