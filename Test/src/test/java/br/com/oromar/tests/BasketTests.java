package br.com.oromar.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.oromar.business.TaxCalculator;
import br.com.oromar.factory.ProductFactory;
import br.com.oromar.models.Basket;

public class BasketTests {

	private Basket classUnderTest;
	
	@Before
	public void setup() {
		classUnderTest = new Basket(new TaxCalculator());
	}
	
	@Test
	public void EmptyBasket() {
		assertEquals(classUnderTest.toString(), "");
	}
	
	@Test
	public void BasketWithOneExemptProduct() {
		classUnderTest.add(ProductFactory.fromString("1 book at 10.0"));
		assertEquals("1 book: 10.00\nSales Taxes: 0.00\nTotal: 10.0", classUnderTest.toString());
	}
	
	@Test
	public void BasketWithOneImportedExemptProduct() {
		classUnderTest.add(ProductFactory.fromString("1 imported book at 10.0"));
		assertEquals("1 imported book: 10.50\nSales Taxes: 0.50\nTotal: 10.5", classUnderTest.toString());
	}
	
	@Test
	public void BasketWithOneTaxedProduct() {
		classUnderTest.add(ProductFactory.fromString("1 bottle of perfume at 10.0"));
		assertEquals("1 bottle of perfume: 11.00\nSales Taxes: 1.00\nTotal: 11.0", classUnderTest.toString());
	}
	
	@Test
	public void BasketWithOneImportedTaxedProduct() {
		classUnderTest.add(ProductFactory.fromString("1 imported bottle of perfume at 10.0"));
		assertEquals("1 imported bottle of perfume: 11.50\nSales Taxes: 1.50\nTotal: 11.5", classUnderTest.toString());
	}
	
	@Test
	public void BasketWithTwoExemptProducts() {
		classUnderTest.add(ProductFactory.fromString("1 book at 10.0"));
		classUnderTest.add(ProductFactory.fromString("1 book at 10.0"));
		assertEquals("1 book: 10.00\n1 book: 10.00\nSales Taxes: 0.00\nTotal: 20.0", classUnderTest.toString());
	}
	
	@Test
	public void BasketWithTwoExemptProducts2() {
		classUnderTest.add(ProductFactory.fromString("2 books at 10.0"));
		assertEquals("2 books: 20.00\nSales Taxes: 0.00\nTotal: 20.0", classUnderTest.toString());
	}
	
	@Test
	public void BasketWithTwoImportedExemptProducts() {
		classUnderTest.add(ProductFactory.fromString("1 imported book at 10.0"));
		classUnderTest.add(ProductFactory.fromString("1 imported book at 10.0"));
		assertEquals("1 imported book: 10.50\n1 imported book: 10.50\nSales Taxes: 1.00\nTotal: 21.0", classUnderTest.toString());
	}
	
	@Test
	public void BasketWithTwoImportedExemptProducts2() {
		classUnderTest.add(ProductFactory.fromString("2 imported books at 10.0"));
		assertEquals("2 imported books: 21.00\nSales Taxes: 0.50\nTotal: 21.0", classUnderTest.toString());
	}
	
	@Test
	public void BasketWithTwoImportedTaxedProducts() {
		classUnderTest.add(ProductFactory.fromString("1 imported bottle of perfume at 10.0"));
		classUnderTest.add(ProductFactory.fromString("1 imported bottle of perfume at 10.0"));
		assertEquals("1 imported bottle of perfume: 11.50\n1 imported bottle of perfume: 11.50\nSales Taxes: 3.00\nTotal: 23.0", classUnderTest.toString());
	}
	
	@Test
	public void BasketWithTwoImportedTaxedProducts2() {
		classUnderTest.add(ProductFactory.fromString("2 imported bottle of perfume at 10.0"));
		assertEquals("2 imported bottle of perfume: 23.00\nSales Taxes: 1.50\nTotal: 23.0", classUnderTest.toString());
	}
	
	@Test
	public void TestInput1() {
		classUnderTest.add(ProductFactory.fromString("1 book at 12.49"));
		classUnderTest.add(ProductFactory.fromString("1 music CD at 14.99"));
		classUnderTest.add(ProductFactory.fromString("1 chocolate bar at 0.85"));
		
		assertEquals("1 book: 12.49\n" + 
					 "1 music CD: 16.49\n" + 
					 "1 chocolate bar: 0.85\n" + 
					 "Sales Taxes: 1.50\n" + 
					 "Total: 29.83", classUnderTest.toString());
	}
	
	@Test
	public void TestInput2() {
		classUnderTest.add(ProductFactory.fromString("1 imported box of chocolates at 10.00"));
		classUnderTest.add(ProductFactory.fromString("1 imported bottle of perfume at 47.50"));
		
		assertEquals("1 imported box of chocolates: 10.50\n" + 
					 "1 imported bottle of perfume: 54.65\n" + 
					 "Sales Taxes: 7.65\n" + 
					 "Total: 65.15", classUnderTest.toString());
	}
	
	@Test
	public void TestInput3() {
		classUnderTest.add(ProductFactory.fromString("1 imported bottle of perfume at 27.99"));
		classUnderTest.add(ProductFactory.fromString("1 bottle of perfume at 18.99"));
		classUnderTest.add(ProductFactory.fromString("1 packet of headache pills at 9.75"));
		classUnderTest.add(ProductFactory.fromString("1 imported box of chocolates at 11.25"));
		
		assertEquals("1 imported bottle of perfume: 32.19\n" + 
					 "1 bottle of perfume: 20.89\n" + 
					 "1 packet of headache pills: 9.75\n" + 
					 "1 imported box of chocolates: 11.85\n" + 
					 "Sales Taxes: 6.70\n" + 
					 "Total: 74.68", classUnderTest.toString());
	}
	
}
