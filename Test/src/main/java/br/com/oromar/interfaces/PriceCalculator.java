package br.com.oromar.interfaces;

import br.com.oromar.models.Product;

public interface PriceCalculator {
	
	double calculatePrice(Product product);
	double calculateTax(Product product);
	
}
