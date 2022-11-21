package org.ticket.italy;

import java.util.Scanner;

public class TicketOffice {

	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		
		while(!valid) {
			
		
		System.out.print("Insert your age: ");
		int age = sc.nextInt();
		
		System.out.print("How long will be your trip? ");
		int km = sc.nextInt();
		
		try {
			Ticket t = new Ticket(km, age);
			System.out.println(t);
			valid = true;
			
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	} sc.close();
}
}
