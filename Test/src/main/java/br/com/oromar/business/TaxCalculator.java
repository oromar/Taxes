package br.com.oromar.business;

import java.util.Arrays;
import java.util.List;

import br.com.oromar.interfaces.PriceCalculator;
import br.com.oromar.models.Product;
import br.com.oromar.types.ProductType;

public class TaxCalculator implements PriceCalculator {

	private static double TAX_RATE = 0.10; 		//10%
	private static double IMPORT_RATE = 0.05; 	//5%

	private static List<ProductType> PRODUCTS_EXEMPT = Arrays
			.asList(new ProductType[] { ProductType.BOOK, ProductType.MEDICAL, ProductType.FOOD });

	public double calculatePrice(Product product) {

		double price = product.getPrice() + calculateTax(product);

		return product.getQuantity() * Math.round(price * 100.0) / 100.0;
	}
	
	public double calculateTax(Product product) {
		
		double tax = product.getPrice() * (PRODUCTS_EXEMPT.contains(product.getType()) ? 0.0 : TAX_RATE);

		if (product.isImported()) {
			tax += product.getPrice() * IMPORT_RATE;
		}

		tax = Math.ceil(tax * 20.0) / 20.0;

		return tax;
	}
}
