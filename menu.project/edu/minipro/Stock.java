package edu.minipro;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Stock {
	
	//method for performing insertion task
	public void insertProductToDB(int data){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String fdate = dateFormat.format(date).toString();
		ArrayList<Product> list = new ArrayList<>();
		
		//write data to file
		try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new  FileOutputStream("file/db.g3")))){
			long start=System.currentTimeMillis();
			for (int i = 0; i < data; i++) {
	            if (i == 7) {
	            	list.add(i, new Product(i+1, "Name", 2.0, 5, fdate));
				}else {
					list.add(i, new Product(i+1, "Blank", 2.0, 5, fdate));
				}
			}
			oos.writeObject(list);
			long stop=System.currentTimeMillis();
			System.out.println("Writing Duration: " + Math.floor((stop-start)/1E3) + "s");
			
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//method for performing selection task
	public void selectProductFromDB(int page, int row){
		//read data from file
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("file/db.g3")))){
			long start=System.currentTimeMillis();
			ArrayList<Product> table = null;

				try {
					table = (ArrayList<Product>)ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			Collections.reverse(table);
			int s=getRanges(page, row);
			int pages=totalPages(table.size(), row);
			
			//Header of Table
//			TableShow(0, 5);
			
//			System.out.format(" +---------------+----------------------+-----------------------+-----------------------+-----------------------+%n");
//			System.out.format("||\tID\t||\tNAME\t\t||\tUNIT PRICE($)\t||\tSTOCK QUANTITY\t||\tIMPORTED DATE\t||%n");
//			System.out.format(" +---------------+----------------------+-----------------------+-----------------------+-----------------------+%n");
//			//Body of Table
//			for(int i=0; i<table.size(); i++){
//				if(i < table.size()){
//					//int id = i+1;
//					int id = table.get(i).getId();
//					String name = table.get(i).getName();
//					double unitPrice = table.get(i).getUnitPrice();
//					int sQty = table.get(i).getsQty();
//					String date = table.get(i).getiDate();
//					System.out.format("|| "+id+"\t||\t"+name+"\t||\t"+unitPrice+"\t\t||\t"+sQty+"\t\t||\t"+date+"\t||%n");
//					System.out.format(" +---------------+----------------------+-----------------------+-----------------------+-----------------------+%n");
//				}
//			}
			long stop=System.currentTimeMillis();
			System.out.println("Reading Duration: " + Math.floor((stop-start)/1E3) + "s");
			
			//Footer of Table
			System.out.println("\n\t\t\t\t\tPage: " +page+ "/" +pages+ " =||= " + "Total: " +table.size()+ "\t\t");
			
			
			//call Method tableMenu
//			tableMeu();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private int totalPages(int totalRows, int row){
		double page=Math.ceil(totalRows/row);
		return (int) page;
	}
	private int getRanges(int s, int r){
		return (s-1)*r;
	}
	//table tableShow
//public static void TableShow(Integer row, Integer col){
//		Table t = new Table(5,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.ALL);
//		CellStyle numbers = new CellStyle(HorizontalAlign.center);
//		t.setColumnWidth(0, 15, 30);
//	    t.setColumnWidth(1, 15, 30);
//	    t.setColumnWidth(2, 15, 30);
//	    t.setColumnWidth(3, 15, 30);
//	    t.setColumnWidth(4, 15, 30);
//	    t.addCell("ID", numbers);
//	    t.addCell("name", numbers);
//	    t.addCell("Unit Price", numbers);
//	    t.addCell("Quantity", numbers);
//	    t.addCell("Imported Date",numbers);
//	    for(int i=row;i<col;i++){
//	    	Table ts = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,  
//	    			ShownBorders.SURROUND);
//	    	CellStyle number = new CellStyle(HorizontalAlign.center);
//	    	ts.setColumnWidth(0, 80, 120);
//	    	ts.addCell(""+number);
//	    	ts.addCell(" ");
//	    	ts.addCell(""+number);
//	  	System.out.println(t.render());
//	    	}  
//	}
//	//Method footer
//	public static void tableMeu(){
//		Table t = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,  
//				ShownBorders.SURROUND);
//		CellStyle row = new CellStyle(HorizontalAlign.center);
//		t.setColumnWidth(0, 80, 120);
//		t.addCell("*)Display | W)rite | R)ead | U)pdate | D)elete | F)irst | P)revious | "
//				+ "L)ast",row);
//		t.addCell(" ");
//		t.addCell("S)earch | G)o to | Se)t row | Sa)ve | B)ack up | Re)store | H)elp |"
//				+ " E)xit",row);		
//		System.out.println(t.render());
//		System.out.println("options > ");
//	}
//
}

