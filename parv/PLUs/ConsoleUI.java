package com.parv.PLUs;

import java.util.Scanner;
import java.util.Set;

public class ConsoleUI {
	
	private Scanner in = new Scanner(System.in);
	private CrudItem ci;
	
	public ConsoleUI(CrudItem citem) {
		this.ci = citem;
	}
	
	// Main menu, lets users choose between CRUD operations and exiting the app
	public void mainMenu() {
		boolean flag = true;
		do {
			// Display main menu
			System.out.println("1. Display a particular item's details.");
			System.out.println("2. Update an item.");
			System.out.println("3. Create a new item.");
			System.out.println("4. Delete an item.");
			System.out.println("5. Exit the program.");
			System.out
					.print("\n\n\nPlease enter a number from the choices above(1-5): ");
			
			String input = this.in.next();
			switch (input) {
			case "1":
				readItemUI();
				break;
			case "2":
				updateItemUI();
				break;
			case "3":
				createItemUI();
				break;
			case "4":
				deleteItemUI();
				break;
			case "5":
				flag = false;
				break;
			default:
				System.out.println("\n***** Invalid entry *****");
				this.mainMenu();
				break;
			}
		} while (flag);
	}
	
	
	
	private void deleteItemUI() {
		System.out.print("Please enter the UPC of the item you would like to delete: ");
		String input = this.in.next();
		
		ci.destroy(input);
		
	}



	private void createItemUI() {
		System.out.println("\nPlease enter the details of the item you would like to create: ");
		
		System.out.print("Please enter the upc of the new item: ");
		String upc = in.next();
		
		System.out.print("Please enter the upc modifier of the new item: ");
		String upcModifier = in.next();
		in.nextLine();
		
		System.out.print("Please enter the description of the new item: ");
		String description = in.nextLine();
		
		System.out.print("Please enter the department of the new item: ");
		String department = in.next();
		
		System.out.print("Please enter the fee of the new item: ");
		String fee = in.next();
		
		System.out.print("Please enter the product code of the new item: ");
		String pcode = in.next();
		
		System.out.print("Please enter the price of the new item: ");
		String price = in.next();
		
		System.out.print("Please enter the sell unit of the new item: ");
		String sellUnit = in.next();
		
		System.out.print("Is this item taxable(y  or n): ");
		String input = in.next();
		
		String isTaxable;
		
		if (input.equals("y")) isTaxable = "true";
		else isTaxable = "false";
		
		Item item = new Item();
		
		UPC newupc = new UPC(upc);
		if (newupc.getUPC().length() < 10)
			newupc.generateLongUPC();
		if (newupc.getUPC().length() != 12)
			newupc.addCheckDigit();
		
		
		item.getProperties().put("upc", "00" + newupc.getUPC());
		item.getProperties().put("upcModifier", upcModifier);
		item.getProperties().put("description", description);
		item.getProperties().put("department", department);
		item.getProperties().put("fee", fee);
		item.getProperties().put("pcode", pcode);
		item.getProperties().put("price", price);
		item.getProperties().put("SellUnit", sellUnit);
		item.getProperties().put("taxRates", isTaxable);
		
		ci.create(item);
	}



	private void updateItemUI() {
		System.out.println("Please enter the upc of the item you wish to update: ");
		String upc = in.next();
		Item item = ci.read(upc);
		
		boolean flag = true;
		String updateField = null;
		
		do{
		System.out.println("Please choose which field you would like to update(0-9): ");
		System.out.println("1. UPC");
		System.out.println("2. UPC Modifier");
		System.out.println("3. Description");
		System.out.println("4. Department");
		System.out.println("5. Fee");
		System.out.println("6. Product Code");
		System.out.println("7. Price");
		System.out.println("8. Sell Unit");
		System.out.println("9. Taxable");
		System.out.println("0. Done updating");
		
		String input = in.next();
		
		if (input.equals("0")) break;
		
		System.out.println("Please input the new value for this field: ");
		String value = in.next();
		
		
		switch(input) {
		case "1":
			updateField = "upc";
			break;
		case "2":
			updateField = "upcModifier";
			break;
		case "3":
			updateField = "description";
			break;
		case "4":
			updateField = "department";
			break;
		case "5":
			updateField = "fee";
			break;
		case "6":
			updateField = "pcode";
			break;
		case "7":
			updateField = "price";
			break;
		case "8":
			updateField = "sellUnit";
			break;
		case "9":
			updateField = "taxRates";
			break;
		default:
			System.out.println("Invalid Input, please try again!");
		}
		
		this.ci.update(updateField, value, item);
		
		} while (flag);
	}



	private void readItemUI() {
		System.out.print("Please scan or input the upc for the item you would like to view: ");
		String input = in.next();
		
		Item item = ci.read(input);
		
		try {
			Set<String> keys = item.getProperties().keySet();
			
			for (String key : keys) {
				System.out.println(key + " : " + item.getProperties().get(key));
			}
		} catch (Exception e) {
			System.out.println("Item not found.");
		}
	}


}
