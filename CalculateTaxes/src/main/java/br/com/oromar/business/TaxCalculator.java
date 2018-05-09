package br.com.oromar.business;

import br.com.oromar.interfaces.Taxable;
import br.com.oromar.interfaces.Constants;
import br.com.oromar.interfaces.PriceCalculator;

public class TaxCalculator implements PriceCalculator {

	private static double TAX_RATE = 0.10; 		//10%
	private static double IMPORT_RATE = 0.05; 	//5%

	public double calculatePrice(Taxable taxable) {

		var price = taxable.getPrice() + calculateTax(taxable);

		return taxable.getQuantity() * Math.round(price * 100.0) / 100.0;
	}
	
	public double calculateTax(Taxable taxable) {
		
		var tax = taxable.getPrice() * (Constants.PRODUCTS_EXEMPT.contains(taxable.getType()) ? 0.0 : TAX_RATE);

		if (taxable.isImported()) {
			tax += taxable.getPrice() * IMPORT_RATE;
		}

		tax = Math.ceil(tax * 20.0) / 20.0;

		return tax;
	}
}
