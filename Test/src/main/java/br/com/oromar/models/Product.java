package br.com.oromar.models;

import br.com.oromar.types.ProductType;

public class Product {

	private String name;
	private int quantity;
	private ProductType type;
	private double price;
	private boolean imported;

	public Product(int quantity, String name, double price, ProductType type) {
		super();
		this.quantity = quantity;
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}	
}
