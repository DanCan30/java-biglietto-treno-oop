package org.ticket.italy;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class TicketOffice {

	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		FileWriter writer = new FileWriter("./ticketlist.txt", true);
		boolean valid = false;
		
		while(!valid) {
			
		
		System.out.print("Insert your age: ");
		int age = sc.nextInt();
		
		System.out.print("How long will be your trip? ");
		int km = sc.nextInt();
		
		System.out.print("Buy flexible ticket? (y/n, Default n) ");
		String flexStr = sc.next();
		boolean flex = false;
		
		if(flexStr.equals("y")) {
			flex = true;
		}
		
		try {
			Ticket t = new Ticket(km, age, flex);
			writer.write(t.toString());
			valid = true;
			
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			writer.close();
		}
	} sc.close();
	
	Scanner fileSc = null;
	
	try {

		File file = new File("./ticketlist.txt");
		
		fileSc = new Scanner(file);
		
		while (fileSc.hasNextLine()) {
			String line = fileSc.nextLine();
			System.out.println(line);
		} 
	} catch (Exception e){
		
		System.err.println(e.getMessage());
	} finally {
		
		fileSc.close();		
	}
	
}
}
