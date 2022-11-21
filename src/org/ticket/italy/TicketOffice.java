package org.ticket.italy;

import java.text.DecimalFormat;
import java.util.Scanner;

public class TicketOffice {

	public static void main(String[] args) throws Exception {
		
		DecimalFormat twoDigitFormat = new DecimalFormat("0.00");
		Scanner sc = new Scanner(System.in);
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
		} else if (flexStr.equals("n")) {
			flex = false;
		}
		
		try {
			Ticket t = new Ticket(km, age, flex);
			System.out.println("Ticket price: " + twoDigitFormat.format(t.calculateFinalPrice()) + "€");
			System.out.println("Flexible ticket: " + t.getFlexibleExpiration());
			System.out.println("Current date: " + t.getDate() + " - Expiration date: " + t.getExpirationDate());
			valid = true;
			
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	} sc.close();
}
}
