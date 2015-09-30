package com.parv.PLUs;

import java.util.ArrayList;

public class UPC {
		private String upc;
		
		public UPC(String myUpc) {
			this.upc = myUpc;
		}
		
		public ArrayList<Character> splitDigits() {
			
			int length = this.upc.length();
			ArrayList<Character> digits = new ArrayList<Character>();
			
			for(int i = 0; i < length; i++)
				digits.add(this.upc.charAt(i));
			
			return digits;
			
		}
		
		public void generateLongUPC() {
			String longUPC = "";
			
			ArrayList<Character> digits = splitDigits();
			
			int key;
	
			key = Integer.parseInt(Character.toString(digits.get(6)));
			
			switch (key) {
			case 0:
			case 1:
			case 2:
				longUPC = "0" + digits.get(1) + digits.get(2) + digits.get(6) + "0000" 
					+ digits.get(3) + digits.get(4) + digits.get(5);
				break;
			case 3:
				longUPC = "0" + digits.get(1) + digits.get(2) + digits.get(3) + "00000"
					+ digits.get(4) + digits.get(5);
				break;
			case 4:
				longUPC = "0" + digits.get(1) + digits.get(2) + digits.get(3) + digits.get(4) 
					+ "00000" + digits.get(5);
				break;
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				longUPC = "0" + digits.get(1) + digits.get(2) + digits.get(3) + digits.get(4) 
					+ digits.get(5) + "0000" + key;
				break;
			default:
				break;
			}
			
			this.upc = longUPC;
		}
		
		public void addCheckDigit() {
			String checkDigit;
			int sumEven = 0;
			int sumOdd = 0;
			
			ArrayList<Character> digits = splitDigits();
			
			for(int i = 0; i < digits.size(); i++) {
				if((i + 1) % 2 == 0)
					sumEven += Integer.parseInt(Character.toString(digits.get(i)));
				else
					sumOdd += Integer.parseInt(Character.toString(digits.get(i)));
			}
			
			int check = 10 - (sumOdd * 3 + sumEven) % 10;
			
			if (check == 10)
				checkDigit = "0";
			else
				checkDigit = Integer.toString(check);
			
			this.upc = upc + checkDigit;
		}
		
		public String getUPC() {
			return this.upc;
		}
}
