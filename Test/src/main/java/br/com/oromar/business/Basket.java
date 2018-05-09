package br.com.oromar.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.oromar.interfaces.Constants;
import br.com.oromar.interfaces.PriceCalculator;
import br.com.oromar.models.Product;

public class Basket {

	private List<Product> products;
	private PriceCalculator calculator;

	public Basket(PriceCalculator calculator) {
		super();
		this.calculator = calculator;
		this.products = new ArrayList<>();
	}
	
	public Basket(List<Product> products, PriceCalculator calculator) {
		this(calculator);
		this.products = products;
	}

	public void add(Product product) {
		products.add(product);
	}
	
	public double getTotal() {
		return products.stream().mapToDouble(a -> (a.getPrice() + calculator.calculateTax(a)) * a.getQuantity()).sum();
	}
	
	public double getSalesTaxes() {
		return products.stream().mapToDouble(a -> calculator.calculateTax(a)).sum();
	}
	
	@Override
	public String toString() {

		var builder = new StringBuilder();

		if (products != null && !products.isEmpty()) {

			for (Product p : products) {
				builder.append(p.getQuantity());
				builder.append(Constants.WHITESPACE);
				builder.append(p.getName());
				builder.append(Constants.COLON);
				builder.append(Constants.WHITESPACE);
				builder.append(BigDecimal.valueOf(calculator.calculatePrice(p)).setScale(2));
				builder.append(Constants.NEW_LINE);
			}
			builder.append(Constants.SALES_TAXES_LABEL);
			builder.append(BigDecimal.valueOf(getSalesTaxes()).setScale(2));
			builder.append(Constants.NEW_LINE);
			builder.append(Constants.TOTAL_LABEL);
			builder.append(Math.round(getTotal()*100)/100.0);
		}

		return builder.toString();
	}

}
