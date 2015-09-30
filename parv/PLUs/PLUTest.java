package com.parv.PLUs;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PLUTest {

	public static void main(String[] args) throws ParserConfigurationException,
	SAXException, IOException, TransformerException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document document = builder.parse(new File("PLUs.xml"));
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		
		CrudItem ci = new CrudItem(document, nodeList);
		
		
		
		ConsoleUI cui = new ConsoleUI(ci);
		cui.mainMenu();
		
		ci.writeDocument();
		
		System.out.println("All Done!");
	}
}