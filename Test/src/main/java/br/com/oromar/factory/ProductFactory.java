package br.com.oromar.factory;

import org.apache.commons.math3.exception.NullArgumentException;

import br.com.oromar.models.Product;
import br.com.oromar.types.ProductType;

public class ProductFactory {

	private static String AT = "at";
	private static String IMPORTED = "imported";
	private static String PRODUCT_INPUT_REGEX = "^[0-9]+ ([a-zA-z]+ ?)+ at [0-9]+\\.[0-9]+$";
	
	public static Product fromString(String value) {

		if (value == null) {
			throw new NullArgumentException();
		}
		if (!value.matches(PRODUCT_INPUT_REGEX)) {
			throw new IllegalArgumentException("Value provided is not valid");
		}

		String[] tokens = value.split(" ");

		int index = 1;

		StringBuilder builder = new StringBuilder();

		for (int i = 1; !tokens[i].equals(AT); i++) {
			builder.append(tokens[i]).append(" ");
			index++;
		}

		String productName = builder.toString().trim();

		Product product = new Product(Integer.parseInt(tokens[0]), productName, Double.parseDouble(tokens[index + 1]),
				null);
		;

		if (productName.contains("book")) {
			product.setType(ProductType.BOOK);
		} else if (productName.contains("chocolate")) {
			product.setType(ProductType.FOOD);
		} else if (productName.contains("pills")) {
			product.setType(ProductType.MEDICAL);
		} else {
			product.setType(ProductType.OTHER);
		}

		if (productName.contains(IMPORTED)) {
			product.setImported(true);
		}

		return product;
	}
}
