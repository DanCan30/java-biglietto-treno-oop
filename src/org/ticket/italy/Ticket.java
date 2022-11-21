package org.ticket.italy;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Ticket {
	
	DecimalFormat twoDigitFormat = new DecimalFormat("0.00");
	
	private static final BigDecimal COST_PER_KM = new BigDecimal(0.21); 
	private static final BigDecimal OVER_65_DISCOUNT = new BigDecimal(0.40);
	private static final BigDecimal UNDER_18_DISCOUNT = new BigDecimal(0.20);

	private int km;
	private int age;
	
	public Ticket(int km, int age) throws Exception {
		setKm(km);
		setAge(age);
	}
	
//	Getters and setters
	public int getKm() {
		return this.km;
	}
	
	public void setKm(int km) throws Exception {
		isValidKm(km);
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) throws Exception {
		isValidAge(age);
	}
	
//	------------------------
	
	private void isValidKm(int km) throws Exception {
		
		if(km <= 0) {
			throw new Exception("Only positive values allowed.");
		}
		this.km = km;
	}
	
	private void isValidAge(int age) throws Exception {
		if(age <= 0) {
			throw new Exception("Only positive values allowed.");
		}
		this.age = age;
	}
	
	public double calculateFinalPrice() {
		
		double finalPrice = this.km * calculateDiscount(this.age);
		return finalPrice;
	}
	
	private double calculateDiscount(int age) {
		
		BigDecimal fixedCost = new BigDecimal(0);
		
		if(age >= 65) {
			fixedCost = Ticket.COST_PER_KM.subtract(Ticket.COST_PER_KM.multiply(Ticket.OVER_65_DISCOUNT));
		} else if(age <= 18) {
			fixedCost = Ticket.COST_PER_KM.subtract(Ticket.COST_PER_KM.multiply(Ticket.UNDER_18_DISCOUNT));
		} else {
			fixedCost = Ticket.COST_PER_KM;
		}
		
		return fixedCost.doubleValue();
	}
	
	@Override
	public String toString() {
		return "Age: " + getAge()
		+ "\nKm: " + getKm()
		+ " Km\nCost per km: " + twoDigitFormat.format(calculateDiscount(getAge()))
		+ "€ \nTotal cost: " + twoDigitFormat.format(calculateFinalPrice()) + "€";
	}

}
