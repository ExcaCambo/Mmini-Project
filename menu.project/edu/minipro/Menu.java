package edu.minipro;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {
	private static final String validLetter = "[*wWrRdDuUfFlLnNpPsSgGbBhHeE]|(se|Se)|(sa|Sa)|(di|Di)|(re|Re)", validNumber = "-?\\d+(\\.\\d+)?";
	static String error_al = "Syntax error!"; 
	private static ArrayList<Product> table = new ArrayList<>();
	private static ArrayList<Product> tableTmp = new ArrayList<>();
	
	//	Menu menu = new Menu();	
	public static void main(String[] args) {
		Menu n = new Menu();
		n.ReaderFile();
		n.restoreFileTmp("file/db.tmp");
		n.displayX();
		n.main_menu();
	}
	
	//method for control input value M,m = get from menu, T,t = get as text input 
	public void inputM(String Option) {
		boolean p = Pattern.compile("[mMtTiI]").matcher(Option).matches();		
		if (p) {
			if (Option.equals("M")||Option.equals("m")) {
				menuindex(getText(""));
			}else if (Option.equals("T")||Option.equals("t")) {
				System.out.println(getText(";"));
			}else if (Option.equals("I")||Option.equals("i")) {
				System.out.println(getText(""));
			}
		}
		else {
			System.out.println("++ Help ++\n\t You can use only M ot T.");
		}
	}
	
	public void main_menu() {
		System.out.println("\n________________ MENU ___________________\n>>");
		menuindex(getText(""));
	}
	//Menu input option
	public void menuindex(String str) {		
		if (ifletterX(str)) {
			char x = 0;
			if (str.length() > 1) {
				switch (str) {
				case "Se": case "se": setrows(); break;
				case "Sa": case "sa": save(); break;
				case "Re": case "re": restore(); break;
				}
			}else {
				x = str.charAt(0);
				switch (x) {
				case 'w': case 'W': write(); break;
				case 'r': case 'R': read(); break;
				case 'd': case 'D': delete(); break;
				case 'u': case 'U': update(); break;
				case 'f': case 'F': tofirst(); break;
				case 'l': case 'L': tolast(); break;
				case 'n': case 'N': tonext(); break;
				case 'p': case 'P': toprevious(); break;
				case 's': case 'S': search(); break;
				case 'g': case 'G': gotopage(); break;
				case 'b': case 'B': backup(); break;
				case 'h': case 'H': help(); break;
				case 'e': case 'E': exit(); break;
				case '*': case ' ': display(); break;
				}
			}
		}
		else {
			System.out.println(error_al);
		}
		main_menu();
	}
	
	//method check for number when input for menu
	public boolean ifnumber(String str) {
		boolean p = Pattern.compile(validNumber).matcher(str).matches();
		return p;
	}
	
	//method check for letter when input for menu
	public boolean ifletterX(String str) {
		boolean p = Pattern.compile(validLetter).matcher(str).matches();
		return p;
	}
	
	//method switch to method responder
	public void indexer(){
		System.out.println("INDEXER !");
	}
	//method write
	public void write() {
		System.out.println("WRITER !");
		insertX();
	}
	
	//method read
	public void read() {
		System.out.println("READER !");
	}
	
	//method update
	public void update() {
		System.out.println("UPDATER !");
		updateX(getText(""));
	}
	
	//method delete
	public void delete() {
		System.out.println("DELETER !");
		deleteX(getText(""));
	}
	
	//method toFirst
	public void tofirst() {
		System.out.println("TO FIRST !");
	}
	
	//method toLast
	public void tolast() {
		System.out.println("TO LAST !");
	}
	
	//method moveNext
	public void tonext() {
		System.out.println("TO NEXT !");
	}
	
	//method movePrevious
	public void toprevious() {
		System.out.println("TO PREVIOUS !");
	}
	
	//method search
	public void search() { 
		System.out.println("SEARCH !");
		searchResult(getText(""));
	}
	
	//method goto
	public void gotopage() {
		System.out.println("GO TO PAGE !");
	}
	
	//method setrows
	public void setrows() {
		System.out.println("SET ROW !");
	}
	
	//method display
	public void display() {
		System.out.println("DISPLAY !");
		displayX();
	}
	
	//method save file
	public void save() {
		System.out.println("SAVE !");
		WriterFile();
		new File("file/db.tmp").delete();
	}
	
	//method back file from temp
	public void backup() {
		System.out.println("BACK UP !");
		backupFile();
	}
	
	//method restore
	public void restore() {
		System.out.println("RESTORE !");
		restoreFileX();
	}
	
	//method show how to use this programe
	public void help() {
		System.out.println("HELP !");
	}
	
	//method close progame
	public void exit() {
		System.out.println("Thank !");
		WriterFile();
		System.exit(0);
	}
	
	
	// ------------- Implement Function -------------------
	
	//Find name check it has in data or not ,if it does not has return -1
		private int searchName(String search) {
			int id = 0, index = 0;
			if ((id = searchArr(search).length) > 0) {
				index = searchArr(search)[0];
			}else {
				index = -1;
			}
			return index;
		}
		
		//Get all text return string
		public String getText(String delimiter) {
			Scanner scanner = new Scanner(System.in);
			try {
				if (delimiter != "") {
					scanner.useDelimiter(delimiter);
					return scanner.next();
				}
				else {
					return scanner.next();
				} 
			} catch (Exception e) {
				getText(delimiter);
				return "";
			}
			
		}
		public double GetNumber() {
			Scanner scanner = new Scanner(System.in);
			try {
				return scanner.nextDouble();
			} catch (Exception e) {
				GetNumber();
				return 0;
			}
		}
	// Delete function, delete from array
	public void deleteX(String search) {
		int in;
		if ((in = searchName(search)) != -1) {
			System.out.println("Delete " +(in + 1)+ " > " + table.get(in).getName());
			table.remove(in);
		}else {
			System.out.println("No record found !");
		}
	}
	//Update by search name
	public void updateX(String search) {
		int in;
		String fdate = new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString();
		if ((in = searchName(search)) != -1) {
			String name = getText("");
			double unp = GetNumber();
			int qty = (int)GetNumber();
			table.set(in, new Product(in+1, name, unp, qty, fdate));
		}else {
			System.out.println("No record found !");
		}
	}
	
	//Insert into Array
	public void insertX() {
		String fdate = new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString();
		int id = (table.get(table.size()-1).getId());
		String name = getText("");
		double unp = GetNumber();
		int qty = (int)GetNumber();
		try {
			table.add(table.size(), new Product(id+1, name, unp, qty, fdate));
			tableTmp.add(new Product(id+1, name, unp, qty, fdate));
			Save2Tmp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//search all name in table
	public int[] searchArr(String search) {
		ArrayList<Integer> found = new ArrayList<>();
		for (int i = 0; i < table.size(); i++) {
			if (table.get(i).getName().equals(search.trim())) found.add(i);
		}
		int []foundIndex = found.stream().mapToInt(i -> i).toArray();
		return foundIndex;
	} 
	// Full search function
	public void searchResult(String search) {
		int inter = 0;
		if ((inter = searchArr(search).length) > 0) {
			for (int i = 0; i < inter; i++) {
				System.out.println("- Id: "+table.get(searchArr(search)[i]).getId()+ "\n- Name: " + table.get(searchArr(search)[i]).getName());
			}
		}else{
			System.out.println("Not found !");
		}
	}
	
	//Read file and insert into array list
	@SuppressWarnings("unchecked")
	public void ReaderFile() {
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("file/db.g3")))){
			table = (ArrayList<Product>)ois.readObject();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Write from array into file
	public void WriterFile() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new  FileOutputStream("file/db.g3")))){
			oos.writeObject(table);	
		} catch (Exception e) {
			System.err.println("File Not Found!");
			e.printStackTrace();
		}
	}
	
	//** Test display
	public void displayX() {	
		if (table.isEmpty()){
			System.err.println("No records !!");
		}
		else {
			int last = table.size(); 
			
			for(int i=0; i<last; i++){
				int id = table.get(i).getId();
				String name = table.get(i).getName();
				double unitPrice = table.get(i).getUnitPrice();
				int sQty = table.get(i).getsQty();
				String date = table.get(i).getiDate();
				System.out.format("|| "+id+"\t||\t"+name+"\t||\t"+unitPrice+"\t\t||\t"+sQty+"\t\t||\t"+date+"\t||%n");
				System.out.format(" +----------------------------------------------------------------------------------------------------------+%n");
			}
		}	
		System.out.println(table.size());
	}
	//Save to file tmp
	public void Save2Tmp() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new  FileOutputStream("file/db.tmp")))){
			oos.writeObject(tableTmp);	
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Load data from file tmp
	@SuppressWarnings("unchecked")
	public boolean LoadFromTmp(String path) {
		File fl = new File(path);
		if (fl.exists()) {
			try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fl)))){
				tableTmp = (ArrayList<Product>)ois.readObject();
				table.addAll(tableTmp);
				return true;
			}catch (Exception e) {
				return false;
			}
		}else {
			return false;
		}
	}
	
	//check if have unsave file or record when load app
	public void restoreFileTmp(String path) {
		File fl = new File(path);
		if (fl.exists()) {
			System.out.println("You have unsave recored!! Do you want to restore them? (Y/N))");
			String s = getText("");
			if (s.equalsIgnoreCase("Y")) {
				if (LoadFromTmp(path)) {
					System.out.println(" * Restore success ! ");
					fl.delete();
				}
				else System.out.println(" * Restore fail ! ");
			}else if(s.equalsIgnoreCase("N")){
				System.out.println(" * Restore canceled ! ");
				fl.delete();
			}	
		}
	}
	
	//restore file
	public void restoreFileX() {
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("file/db.bac")))){
			try {
				table = (ArrayList<Product>)ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// back up file
	public void backupFile() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new  FileOutputStream("file/db.bac")))){
			oos.writeObject(table);	
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
