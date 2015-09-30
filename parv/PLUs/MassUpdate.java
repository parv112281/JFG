package com.parv.PLUs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MassUpdate {
	
	public static final String updateFile = "update.csv";
	
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		// Open the PLUs.xml file and create a new CrudItem object 
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document document = builder.parse(new File("PLUs.xml"));
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		
		CrudItem ci = new CrudItem(document, nodeList);

		// Open the update file
	
		FileReader fr = new FileReader(new File(updateFile));
		BufferedReader br = new BufferedReader(fr);
		
		String line = "";
		
		Scanner in = new Scanner(System.in);	
		System.out.println("Please enter the department for these items: ");
		String department = in.next();
		
		int counter = 0;
		int itemCounter = 0;
		// read in lines from file and pass them to CrudItem read method to retrieve update upc
		// change price of associated upc 
		while((line = br.readLine()) != null) {
			
			List<String> updateItem = Arrays.asList(line.split(","));
			
			if(updateItem.get(1).length() < 12)
				updateItem.set(1, "0" + updateItem.get(1));
			
			if(updateItem.get(1).equals(null))
			{
				System.out.println(line + "No PLU number.\n");
				continue;
			}
				
			Item item = ci.read(updateItem.get(1));
			
			
			if(item == null) {
				if(updateItem.get(2) != "0")
				{
					HashMap<String, String> itemProperties = new HashMap<>();
					itemProperties.put("description", updateItem.get(0));
					itemProperties.put("upc", updateItem.get(1));
					itemProperties.put("price", updateItem.get(2));
					itemProperties.put("department", department);
					if(updateItem.get(3).equals("true"))
						itemProperties.put("taxRates", "true");
					else
						itemProperties.put("taxRates", "false");
					item = new Item(itemProperties);
					ci.create(item);
					itemCounter++;
				}
				//System.out.println(line);
				
				
			}		
			else{
				float currPrice = Float.parseFloat(item.getProperties().get("price"));
				float newPrice = Float.parseFloat(updateItem.get(2));
				if(newPrice > currPrice) {
						ci.update("price", updateItem.get(2), item);
				}
				else {
					counter++;
				}
			}
		}
		
		br.close();
		ci.writeDocument();
		System.out.println(counter + " items priced too low!");	
		System.out.println(itemCounter + " new items added!");	

	}

}
