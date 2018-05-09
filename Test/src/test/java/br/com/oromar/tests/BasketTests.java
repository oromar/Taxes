package br.com.oromar.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.oromar.business.Basket;
import br.com.oromar.business.TaxCalculator;
import br.com.oromar.enums.ProductType;
import br.com.oromar.factory.ProductFactory;
import br.com.oromar.models.Product;

public class BasketTests {

	private Basket classUnderTest;
	
	@Before
	public void setup() {
		classUnderTest = new Basket(new TaxCalculator());
	}
	
	@Test
	public void createBasketWithProducts() {
		List<Product> products = new ArrayList<>();
		products.add(new Product(1, "chocolate bar", 23.5, ProductType.FOOD));
		products.add(new Product(1, "book", 10.5, ProductType.BOOK));
		products.add(new Product(1, "bottle of perfume", 3.5, ProductType.OTHER));
		Basket basket = new Basket(products, new TaxCalculator());
		assertEquals("1 chocolate bar: 23.50\n" + 
					 "1 book: 10.50\n" + 
					 "1 bottle of perfume: 3.90\n" + 
					 "Sales Taxes: 0.40\n" + 
					 "Total: 37.9", basket.toString());
	}
	
	@Test
	public void emptyBasket() {
		assertEquals(classUnderTest.toString(), "");
	}
	
	@Test
	public void basketWithOneExemptProduct() {
		classUnderTest.add(ProductFactory.fromString("1 book at 10.0"));
		assertEquals("1 book: 10.00\nSales Taxes: 0.00\nTotal: 10.0", classUnderTest.toString());
	}
	
	@Test
	public void basketWithOneImportedExemptProduct() {
		classUnderTest.add(ProductFactory.fromString("1 imported book at 10.0"));
		assertEquals("1 imported book: 10.50\nSales Taxes: 0.50\nTotal: 10.5", classUnderTest.toString());
	}
	
	@Test
	public void basketWithOneTaxedProduct() {
		classUnderTest.add(ProductFactory.fromString("1 bottle of perfume at 10.0"));
		assertEquals("1 bottle of perfume: 11.00\nSales Taxes: 1.00\nTotal: 11.0", classUnderTest.toString());
	}
	
	@Test
	public void basketWithOneImportedTaxedProduct() {
		classUnderTest.add(ProductFactory.fromString("1 imported bottle of perfume at 10.0"));
		assertEquals("1 imported bottle of perfume: 11.50\nSales Taxes: 1.50\nTotal: 11.5", classUnderTest.toString());
	}
	
	@Test
	public void basketWithTwoExemptProducts() {
		classUnderTest.add(ProductFactory.fromString("1 book at 10.0"));
		classUnderTest.add(ProductFactory.fromString("1 book at 10.0"));
		assertEquals("1 book: 10.00\n1 book: 10.00\nSales Taxes: 0.00\nTotal: 20.0", classUnderTest.toString());
	}
	
	@Test
	public void basketWithTwoExemptProducts2() {
		classUnderTest.add(ProductFactory.fromString("2 books at 10.0"));
		assertEquals("2 books: 20.00\nSales Taxes: 0.00\nTotal: 20.0", classUnderTest.toString());
	}
	
	@Test
	public void basketWithTwoImportedExemptProducts() {
		classUnderTest.add(ProductFactory.fromString("1 imported book at 10.0"));
		classUnderTest.add(ProductFactory.fromString("1 imported book at 10.0"));
		assertEquals("1 imported book: 10.50\n1 imported book: 10.50\nSales Taxes: 1.00\nTotal: 21.0", classUnderTest.toString());
	}
	
	@Test
	public void basketWithTwoImportedExemptProducts2() {
		classUnderTest.add(ProductFactory.fromString("2 imported books at 10.0"));
		assertEquals("2 imported books: 21.00\nSales Taxes: 0.50\nTotal: 21.0", classUnderTest.toString());
	}
	
	@Test
	public void basketWithTwoImportedTaxedProducts() {
		classUnderTest.add(ProductFactory.fromString("1 imported bottle of perfume at 10.0"));
		classUnderTest.add(ProductFactory.fromString("1 imported bottle of perfume at 10.0"));
		assertEquals("1 imported bottle of perfume: 11.50\n1 imported bottle of perfume: 11.50\nSales Taxes: 3.00\nTotal: 23.0", classUnderTest.toString());
	}
	
	@Test
	public void basketWithTwoImportedTaxedProducts2() {
		classUnderTest.add(ProductFactory.fromString("2 imported bottle of perfume at 10.0"));
		assertEquals("2 imported bottle of perfume: 23.00\nSales Taxes: 1.50\nTotal: 23.0", classUnderTest.toString());
	}
	
	@Test
	public void basketWithManyProducts() {
		classUnderTest.add(ProductFactory.fromString("2 imported bottle of perfume at 48.0"));
		classUnderTest.add(ProductFactory.fromString("3 bottle of perfume at 35.0"));
		classUnderTest.add(ProductFactory.fromString("5 books at 52.0"));
		classUnderTest.add(ProductFactory.fromString("2 chocolate bar at 10.0"));
		assertEquals("2 imported bottle of perfume: 110.50\n"+
					 "3 bottle of perfume: 115.50\n"+
					 "5 books: 260.00\n"+
					 "2 chocolate bar: 20.00\n"+
					 "Sales Taxes: 10.75\n"+
					 "Total: 506.0", classUnderTest.toString());
	}
	
	@Test
	public void testInput1() {
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
	public void testInput2() {
		classUnderTest.add(ProductFactory.fromString("1 imported box of chocolates at 10.00"));
		classUnderTest.add(ProductFactory.fromString("1 imported bottle of perfume at 47.50"));
		
		assertEquals("1 imported box of chocolates: 10.50\n" + 
					 "1 imported bottle of perfume: 54.65\n" + 
					 "Sales Taxes: 7.65\n" + 
					 "Total: 65.15", classUnderTest.toString());
	}
	
	@Test
	public void testInput3() {
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
