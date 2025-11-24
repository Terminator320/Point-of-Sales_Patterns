package database;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
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
        String fileName = "C:\\Users\\2480396\\Desktop\\Point-of-Sales_Patterns\\POSApp\\src\\main\\java\\config.xml";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        //builder is like the product
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(fileName));
        document.getDocumentElement().normalize();

        NodeList database = document.getElementsByTagName("database");

        Node url = database.item(0);
        Node user = database.item(1);
        Node password = database.item(2);

        System.out.println(url.getTextContent().trim());
        System.out.println(user.getTextContent().trim());

        String urlString = url.getTextContent().trim();
        String username = user.getTextContent().trim();
        String passwordString = password.getTextContent().trim();

        Connection connection = DriverManager.getConnection(urlString, username, passwordString);
        return connection;
    }
}
