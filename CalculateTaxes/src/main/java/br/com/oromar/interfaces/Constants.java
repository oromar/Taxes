package br.com.oromar.interfaces;

import java.util.Arrays;
import java.util.List;

import br.com.oromar.enums.ProductType;

public interface Constants {
	
	String AT = "at";
	
	String COLON = ":";
	
	String IMPORTED = "imported";
	
	String NEW_LINE = "\n";
	
	String PRODUCT_INPUT_REGEX = "^[1-9]+ ([a-zA-z]+ ?)+ at [0-9]+\\.[0-9]+$";
	
	List<ProductType> PRODUCTS_EXEMPT = Arrays
			.asList(new ProductType[] { ProductType.BOOK, ProductType.MEDICAL, ProductType.FOOD });

	String PROPERTIES_FILE_NAME = "knowntypes.properties";

	String SALES_TAXES_LABEL = "Sales Taxes: ";

	String TOTAL_LABEL = "Total: ";

	String WHITESPACE = " ";
}
