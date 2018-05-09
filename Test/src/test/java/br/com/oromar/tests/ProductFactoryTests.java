package br.com.oromar.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.oromar.enums.ProductType;
import br.com.oromar.factory.ProductFactory;
import br.com.oromar.models.Product;

public class ProductFactoryTests {

	@Test(expected=IllegalArgumentException.class)
	public void productFactoryNullString() {
		ProductFactory.fromString(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void productFactoryEmptyString() {
		ProductFactory.fromString(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void productFactoryWithoutQuantity() {
		ProductFactory.fromString("chocolate bar at 23.6");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void productFactoryZerotQuantity() {
		ProductFactory.fromString("0 chocolate bar at 23.6");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void productFactoryWithoutName() {
		ProductFactory.fromString("1 at 23.6");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void productFactoryWithoutAt() {
		ProductFactory.fromString("1 chocolate bar 23.6");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void productFactoryWithoutPrice() {
		ProductFactory.fromString("1 chocolate bar ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void productFactoryZeroPrice() {
		ProductFactory.fromString("1 chocolate bar at 0.0");
	}
		
	@Test(expected=IllegalArgumentException.class)
	public void productFactoryInvalidString() {
		ProductFactory.fromString("Invalid String");
	}
	
	@Test
	public void createProductValidateName() {
		Product p = ProductFactory.fromString("1 chocolate bar at 23.6");
		assertEquals("chocolate bar", p.getName());
	}
	
	@Test
	public void createProductValidatePrice() {
		Product p = ProductFactory.fromString("1 book at 28.6");
		assertEquals(28.6, p.getPrice(), 0.0);
	}
	
	@Test
	public void createProductValidateQuantity() {
		Product p = ProductFactory.fromString("56 bottle of perfume at 28.6");
		assertEquals(56, p.getQuantity());
	}
	
	@Test
	public void createFoodProduct() {
		Product p = ProductFactory.fromString("1 chocolate bar at 23.6");
		assertEquals(ProductType.FOOD, p.getType());
	}
	
	@Test
	public void createFoodProduct2() {
		Product p = ProductFactory.fromString("1 bottle of milk at 23.6");
		assertEquals(ProductType.FOOD, p.getType());
	}
	
	@Test
	public void createBookProduct() {
		Product p = ProductFactory.fromString("1 book at 6.6");
		assertEquals(ProductType.BOOK, p.getType());
	}
	
	@Test
	public void createMedicalProduct() {
		Product p = ProductFactory.fromString("1 packet of headache pills at 9.8");
		assertEquals(ProductType.MEDICAL, p.getType());
	}
	
	@Test
	public void createMedicalProduct2() {
		Product p = ProductFactory.fromString("1 packet of headache medicines at 9.8");
		assertEquals(ProductType.MEDICAL, p.getType());
	}
	
	@Test
	public void createOtherProduct() {
		Product p = ProductFactory.fromString("1 bottle of perfume at 10.5");
		assertEquals(ProductType.OTHER, p.getType());
	}
	
	@Test
	public void createImportedProduct() {
		Product p = ProductFactory.fromString("1 imported bottle of perfume at 10.5");
		assertTrue(p.isImported());
	}
}
