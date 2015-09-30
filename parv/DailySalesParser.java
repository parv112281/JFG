package com.parv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DailySalesParser {
	
	static List<String> catList;
	static List<String> mopSalesList;
	static List<String> fuelTypes;
	File listsDataFile;
	ArrayList<String> data = new ArrayList<>();
	File dailySalesFile;

	public DailySalesParser(File dailySales, File listsData) {
		this.dailySalesFile = dailySales;
		this.listsDataFile = listsData;
	}
	public DailySalesParser() {
		this(new File("C:/Users/Parv/Desktop/Store Projects/HTML Parsing/HTML Parsing/Daily.htm"), 
				new File("C:/Users/Parv/Desktop/Store Projects/HTML Parsing/HTML Parsing/Test.txt"));
	}
	public DailySalesParser(File dailySales) {
		this(dailySales, new File("C:/Users/Parv/Desktop/Store Projects/HTML Parsing/HTML Parsing/Test.txt"));
		
	}
	
	
	public static void main(String[] args){
	 DailySalesParser dsp = new DailySalesParser(null, new File("C:/Users/Parv/Desktop/Store Projects/HTML Parsing/HTML Parsing/Test.txt"));
	 dsp.initializeLists();
	 
	 try {
		dsp.extractData();
	} catch (IOException e) {
		e.printStackTrace();
	}
	 
	}

	private void extractData() throws IOException {
		ArrayList<String> list = new ArrayList<>();
		
		File file = new File("Daily.htm");
		Document doc = Jsoup.parse(file, "UTF-8");
				
		Elements content = doc.getElementsByTag("td");
		
		for (Element elem : content) {
			list.add(elem.text());
		}
		
		String date = (list.get(list.indexOf("Close Period") + 2)).substring(0, 10);
		data.add(date);
		
		extractCatData(list);
		extractFuelData(list);
		extractMOPSalesData(list);
		
		System.out.println(data.toString());
	}
	
	private void extractMOPSalesData(ArrayList<String> list) {
		int index = 0;
		for (String moptype : mopSalesList) {
			index = list.indexOf(moptype);
			if(index == -1)
				data.add("");
			else {
				String amt = (list.get(index + 4)).replace(",", "");
				data.add(amt);
			}
		}
	}
	private void extractFuelData(ArrayList<String> list) {
		int lastIndex = 0;
		for (String fueltype : fuelTypes ) {
			int index = list.indexOf(fueltype);
			
			if(index == -1) 
				data.add(", ");
			else {
				String gallons = list.get(index + 4);
				String amount = list.get(index + 6);
				data.add(gallons.replace(",", ""));
				data.add(amount.replace(",", ""));
				lastIndex = index;
			}
		}
		data.add(list.get(lastIndex + 12).replace(",", ""));
		data.add(list.get(lastIndex + 14).replace(",", ""));
	}
	
	private void extractCatData(ArrayList<String> list) {
		int index;
		for (String cat : catList) {
			index = list.indexOf(cat);
			
			if(index == -1) 
				data.add("");
			else { 
				String catAmount = list.get(index + 8);
				data.add(catAmount.replace(",", ""));
				
			}
		}
		data.add(list.get(list.indexOf("Totals") + 6));
	}
	
	private void initializeLists(){
		try {
			FileReader fr = new FileReader(listsDataFile);
			BufferedReader bf = new BufferedReader(fr);
			
			String line = "";
			
			line = bf.readLine();
			catList = Arrays.asList(line.split(","));
			
			line = bf.readLine();
			fuelTypes = Arrays.asList(line.split(","));
			
			line = bf.readLine();
			mopSalesList = Arrays.asList(line.split(","));
			
			bf.close();
			
//			System.out.println("" + catList.toString() + "\n" + mopSalesList.toString() + "\n" + fuelTypes.toString());
			
			
//			while((line = bf.readLine()) != null) {
//				System.out.println(line);
//			} 
//			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

}
