package com.parv.PLUs;

import java.io.File;
import java.util.Set;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CrudItem {
	private Document document;
	private NodeList nodelist;
	
	CrudItem(Document doc, NodeList nl) {
		this.document = doc;
		this.nodelist = nl;
	}
	
	
	/* Parameters are elem, which is the root node domain:PLUs
	 * Second parameter is the item you wish to add to this document
	 * 
	 */
	public void create (Item item) {
		Element newItem = this.document.createElement("domain:PLU");
		Set<String> keys = item.getProperties().keySet();
		for(String key : keys){
			Element property = document.createElement(key);
			if (!key.equals("taxRates")) {
				property.appendChild(document.createTextNode(item
						.getProperties().get(key)));
			}
			else {
				Element taxProperty = (Element)property.appendChild(document.createElement("domain:taxRate"));
				taxProperty.setAttribute("sysid", "1");
			}
			newItem.appendChild(property);
		}
		this.nodelist.item(1).getParentNode().appendChild(newItem);
	}
	
	public Item read(String input) {
		UPC upc = new UPC(input);
		
		if (upc.getUPC().length() < 10)
			upc.generateLongUPC();
		if (upc.getUPC().length() != 12)
			upc.addCheckDigit();
		
		for (int i = 0; i <= this.nodelist.getLength() - 1; i++) {
			Node node = this.nodelist.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;
				if (elem.getElementsByTagName("upc").item(0)
						.getChildNodes().item(0).getNodeValue()
						.equals("00" + upc.getUPC())) {
					Item item = new Item(elem);
					return item;
				}
			
			}
		}
		return null;
	}
	
	public void update(String updateField, String value, Item item) {
		for (int i = 0; i <= this.nodelist.getLength() - 1; i++) {
			Node node = this.nodelist.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;
				if (elem.getElementsByTagName("upc").item(0)
						.getChildNodes().item(0).getNodeValue()
						.equals(item.getProperties().get("upc"))) {
					if (!updateField.equals("taxRates")) {
						Node updateNode = elem
								.getElementsByTagName(updateField).item(0)
								.getFirstChild();
						updateNode.setNodeValue(value);
					}
					else {
						if (value.equals("true")) {
							Node updateNode = ((Element) elem
									.getElementsByTagName(updateField))
									.getElementsByTagName("domain:taxRate")
									.item(0);
							((Element) updateNode).setAttribute("sysid", "1");
						}
					}
				}
			}
		}
	}
	
	public void destroy (String input) {
		UPC upc = new UPC(input);
		
		if (upc.getUPC().length() < 10)
			upc.generateLongUPC();
		if (upc.getUPC().length() != 12)
			upc.addCheckDigit();
		
		
		for (int i = 0; i <= this.nodelist.getLength() - 1; i++) {
			Node node = this.nodelist.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;
				if (elem.getElementsByTagName("upc").item(0)
						.getChildNodes().item(0).getNodeValue()
						.equals("00" + upc.getUPC())) {
					System.out.println("**** In if statement ****" + "\n" + "00" + upc.getUPC());
					node.getParentNode().removeChild(elem);
				}
			}
		}
	}
	
	public void writeDocument() throws TransformerException {
		this.document.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(this.document);
        StreamResult result = new StreamResult(new File("PLUs_updated.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
        System.out.println("XML file updated successfully");
	}
}
