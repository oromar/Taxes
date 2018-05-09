package br.com.oromar.interfaces;

import br.com.oromar.enums.ProductType;

public interface Taxable {

	int getQuantity();
	double getPrice();
	boolean isImported();	
	ProductType getType();
}
