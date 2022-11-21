package org.ticket.italy;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Ticket {
	
	DecimalFormat twoDigitFormat = new DecimalFormat("0.00");
	
	private static final BigDecimal COST_PER_KM = new BigDecimal(0.21); 
	private static final BigDecimal OVER_65_DISCOUNT = new BigDecimal(0.40);
	private static final BigDecimal UNDER_18_DISCOUNT = new BigDecimal(0.20);
	private static final int NORMAL_EXPIRATION_DATE = 30;
	private static final int FLEXIBLE_EXPIRATION_DATE = 90;

	private int km;
	private int age;
	private LocalDate date;
	private boolean flexibleExpiration;
	private LocalDate expirationDate;
	
	public Ticket(int km, int age, boolean flexibleExpiration) throws Exception {
		setKm(km);
		setAge(age);
		setDate();
		setFlexibleExpiration(flexibleExpiration);
		setExpirationDate(flexibleExpiration);
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
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public void setDate() {
		this.date = LocalDate.now();
	}
	
	public boolean getFlexibleExpiration() {
		return this.flexibleExpiration;
	}
	
	public void setFlexibleExpiration(boolean flexibleExpiration) {
		this.flexibleExpiration = flexibleExpiration;
	}
	
	public LocalDate getExpirationDate() {
		return this.expirationDate;
	}
	
	public void setExpirationDate(boolean flexibleExpiration) {
		
		if (flexibleExpiration) {
			this.expirationDate = this.date.plusDays(FLEXIBLE_EXPIRATION_DATE);
		} else {
			this.expirationDate = this.date.plusDays(NORMAL_EXPIRATION_DATE);
		}
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
		
		if(this.flexibleExpiration) {
			finalPrice = finalPrice + (finalPrice * .1);
		}
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
