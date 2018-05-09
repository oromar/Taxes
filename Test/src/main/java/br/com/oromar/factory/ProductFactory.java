package br.com.oromar.factory;

import br.com.oromar.enums.ProductType;
import br.com.oromar.interfaces.Constants;
import br.com.oromar.models.Product;

public class ProductFactory {

	public static Product fromString(String value) {

		if (value == null) {
			throw new IllegalArgumentException("Value cannot be null.");
		}
		if (value.isEmpty()) {
			throw new IllegalArgumentException("Value cannot be empty.");
		}

		if (!value.matches(Constants.PRODUCT_INPUT_REGEX)) {
			throw new IllegalArgumentException("Value provided is not valid.");
		}

		String[] tokens = value.split(Constants.WHITESPACE);

		int index = 1;

		StringBuilder builder = new StringBuilder();

		for (int i = 1; !tokens[i].equals(Constants.AT); i++) {
			builder.append(tokens[i]).append(Constants.WHITESPACE);
			index++;
		}

		int productQuantity = Integer.parseInt(tokens[0]);
		double productPrice = Double.parseDouble(tokens[index + 1]);
		String productName = builder.toString().trim();
		
		if (productPrice <= 0.0) {
			throw new IllegalArgumentException("Price cannot be equal or less than zero(0).");
		}

		Product product = new Product(productQuantity, productName, productPrice, null);
		
		if (productName.contains("book")) {
			product.setType(ProductType.BOOK);
		} else if (productName.contains("chocolate")) {
			product.setType(ProductType.FOOD);
		} else if (productName.contains("pills")) {
			product.setType(ProductType.MEDICAL);
		} else {
			product.setType(ProductType.OTHER);
		}

		if (productName.contains(Constants.IMPORTED)) {
			product.setImported(true);
		}

		return product;
	}
}
