package br.com.oromar.interfaces;

public interface PriceCalculator {
	
	double calculatePrice(Taxable taxable);
	double calculateTax(Taxable taxable);	
}
