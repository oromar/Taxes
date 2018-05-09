package br.com.oromar.factory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.stream.Collectors;

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

		var tokens = value.split(Constants.WHITESPACE);

		var index = 1;

		var builder = new StringBuilder();

		for (int i = 1; !tokens[i].equals(Constants.AT); i++) {
			builder.append(tokens[i]).append(Constants.WHITESPACE);
			index++;
		}

		var productQuantity = Integer.parseInt(tokens[0]);
		var productPrice = Double.parseDouble(tokens[index + 1]);
		var productName = builder.toString().trim();

		if (productPrice <= 0.0) {
			throw new IllegalArgumentException("Price cannot be equal or less than zero(0).");
		}

		var product = new Product(productQuantity, productName, productPrice, null);

		var prop = new Properties();
		try {
			prop.load(new FileReader(new File(Constants.PROPERTIES_FILE_NAME)));
		} catch (IOException e) {
			throw new RuntimeException(Constants.PROPERTIES_FILE_NAME + " file not found");
		}

		var knownBooks = prop.entrySet().stream()
				.filter(a -> a.getValue().equals(ProductType.BOOK.toString().toLowerCase()))
				.map(a -> a.getKey().toString()).collect(Collectors.toList());

		var knownMedicals = prop.entrySet().stream()
				.filter(a -> a.getValue().equals(ProductType.MEDICAL.toString().toLowerCase()))
				.map(a -> a.getKey().toString()).collect(Collectors.toList());

		var knownFoods = prop.entrySet().stream()
				.filter(a -> a.getValue().equals(ProductType.FOOD.toString().toLowerCase()))
				.map(a -> a.getKey().toString()).collect(Collectors.toList());

		if (knownBooks.stream().anyMatch(a -> productName.indexOf(a) > -1)) {
			product.setType(ProductType.BOOK);
		} else if (knownMedicals.stream().anyMatch(a -> productName.indexOf(a) > -1)) {
			product.setType(ProductType.MEDICAL);
		} else if (knownFoods.stream().anyMatch(a -> productName.indexOf(a) > -1)) {
			product.setType(ProductType.FOOD);
		} else {
			product.setType(ProductType.OTHER);
		}

		if (productName.contains(Constants.IMPORTED)) {
			product.setImported(true);
		}

		return product;
	}
}
