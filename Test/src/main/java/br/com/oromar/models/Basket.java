package br.com.oromar.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.oromar.interfaces.PriceCalculator;

public class Basket {

	private List<Product> products;
	private PriceCalculator calculator;

	public Basket(PriceCalculator calculator) {
		super();
		this.calculator = calculator;
		this.products = new ArrayList<>();
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

		StringBuilder builder = new StringBuilder();

		if (products != null && !products.isEmpty()) {

			for (Product p : products) {
				builder.append(p.getQuantity());
				builder.append(" ");
				builder.append(p.getName());
				builder.append(": ");
				builder.append(BigDecimal.valueOf(calculator.calculatePrice(p)).setScale(2));
				builder.append("\n");
			}
			builder.append("Sales Taxes: ");
			builder.append(BigDecimal.valueOf(getSalesTaxes()).setScale(2));
			builder.append("\n");
			builder.append("Total: ");
			builder.append(Math.round(getTotal()*100)/100.0);
		}

		return builder.toString();
	}

}
