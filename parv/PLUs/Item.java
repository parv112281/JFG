package com.parv.PLUs;

import java.util.HashMap;

//import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Item {
//	private String upc;
//	private String upcModifier = "000";
//	private String description;
//	private String department;
//	private String fee = "0";
//	private String pcode = "7";
//	private String price = "0.00";
//	private String sellUnit = "1.00";
	public HashMap<String, String> itemProperties = new HashMap<>();
	
	public Item(Element elem) {
//		this.setUpc(elem.getElementsByTagName("upc").item(0)
//				.getChildNodes().item(0).getNodeValue());
//		this.setUpcModifier(elem.getElementsByTagName("upcModifier").item(0)
//				.getChildNodes().item(0).getNodeValue());
//		this.setDescription(elem.getElementsByTagName("description").item(0)
//				.getChildNodes().item(0).getNodeValue());
//		this.setDepartment(elem.getElementsByTagName("department").item(0)
//				.getChildNodes().item(0).getNodeValue());
//		this.setFee(elem.getElementsByTagName("fee").item(0)
//				.getChildNodes().item(0).getNodeValue());
//		this.setPcode(elem.getElementsByTagName("pcode").item(0)
//				.getChildNodes().item(0).getNodeValue());
//		this.setPrice(elem.getElementsByTagName("price").item(0)
//				.getChildNodes().item(0).getNodeValue());
//		this.setSellUnit(elem.getElementsByTagName("sellUnit").item(0)
//				.getChildNodes().item(0).getNodeValue());
//		
		this.itemProperties.put("upc", elem.getElementsByTagName("upc").item(0)
				.getChildNodes().item(0).getNodeValue());
		this.itemProperties.put("upcModifier", elem.getElementsByTagName("upcModifier").item(0)
				.getChildNodes().item(0).getNodeValue());
		this.itemProperties.put("description", elem.getElementsByTagName("description").item(0)
				.getChildNodes().item(0).getNodeValue());
		this.itemProperties.put("department", elem.getElementsByTagName("department").item(0)
				.getChildNodes().item(0).getNodeValue());
		this.itemProperties.put("fee", elem.getElementsByTagName("fee").item(0)
				.getChildNodes().item(0).getNodeValue());
		this.itemProperties.put("pcode", elem.getElementsByTagName("pcode").item(0)
				.getChildNodes().item(0).getNodeValue());
		this.itemProperties.put("price", elem.getElementsByTagName("price").item(0)
				.getChildNodes().item(0).getNodeValue());
		this.itemProperties.put("SellUnit", elem.getElementsByTagName("SellUnit").item(0)
				.getChildNodes().item(0).getNodeValue());
		try {
			this.itemProperties.put("taxRates", ((Node)(((Element)(elem.getElementsByTagName("taxRates")
					.item(0))).getElementsByTagName("domain:taxRate").item(0))).getAttributes()
					.getNamedItem("sysid").getNodeValue());
		} catch (Exception e) {
			this.itemProperties.put("taxRates", "false");
		}
		
		
	}
	public Item () {
		
	}
	public Item(HashMap<String, String> newItemProperties) 
	{
		newItemProperties.put("upcModifier", "000");
		newItemProperties.put("fee", "0");
		newItemProperties.put("pcode", "7");
		newItemProperties.put("SellUnit", "1.000");
		this.itemProperties = newItemProperties;
		
	}
//	public String getUpc() {
//		return upc;
//	}
//	public void setUpc(String upc) {
//		this.upc = upc;
//	}
//	public String getUpcModifier() {
//		return upcModifier;
//	}
//	public void setUpcModifier(String upcModifier) {
//		this.upcModifier = upcModifier;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public String getDepartment() {
//		return department;
//	}
//	public void setDepartment(String department) {
//		this.department = department;
//	}
//	public String getFee() {
//		return fee;
//	}
//	public void setFee(String fee) {
//		this.fee = fee;
//	}
//	public String getPcode() {
//		return pcode;
//	}
//	public void setPcode(String pcode) {
//		this.pcode = pcode;
//	}
//	public String getPrice() {
//		return price;
//	}
//	public void setPrice(String price) {
//		this.price = price;
//	}
//	public String getSellUnit() {
//		return sellUnit;
//	}
//	public void setSellUnit(String sellUnit) {
//		this.sellUnit = sellUnit;
//	}
//	public boolean getTaxable() {
//		return isTaxable;
//	}
//	public void setTaxable(boolean isTaxable) {
//		this.isTaxable = isTaxable;
//	}
	
	public HashMap<String, String> getProperties() {
		return this.itemProperties;
	}
	
}
