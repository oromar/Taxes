package br.com.oromar.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.oromar.business.TaxCalculator;
import br.com.oromar.factory.ProductFactory;

public class TaxCalculatorTests {

	private TaxCalculator classUnderTest;

	@Before
	public void setup() {
		classUnderTest = new TaxCalculator();
	}

	@Test
	public void calculateTaxBook() {
		assertEquals(12.49, classUnderTest.calculatePrice(ProductFactory.fromString("1 book at 12.49")), 0.0);
	}

	@Test
	public void calculateTaxChocolate() {
		assertEquals(0.85, classUnderTest.calculatePrice(ProductFactory.fromString("1 chocolate bar at 0.85")), 0.0);
	}

	@Test
	public void calculateTaxMusicCD() {
		assertEquals(16.49, classUnderTest.calculatePrice(ProductFactory.fromString("1 music CD at 14.99")), 0.0);
	}

	@Test
	public void calculateTaxImportedChocolate() {
		assertEquals(10.50,
				classUnderTest.calculatePrice(ProductFactory.fromString("1 imported box of chocolates at 10.00")), 0.0);
	}

	@Test
	public void calculateTaxImportedBottleOfPerfume() {
		assertEquals(54.65,
				classUnderTest.calculatePrice(ProductFactory.fromString("1 imported bottle of perfume at 47.50")), 0.0);
	}

	@Test
	public void calculateTaxImportedBottleOfPerfume2() {
		assertEquals(32.19,
				classUnderTest.calculatePrice(ProductFactory.fromString("1 imported bottle of perfume at 27.99")), 0.0);
	}

	@Test
	public void calculateTaxBottleOfPerfume() {
		assertEquals(20.89, classUnderTest.calculatePrice(ProductFactory.fromString("1 bottle of perfume at 18.99")),
				0.0);
	}

	@Test
	public void calculateTaxPacketOfHeadachePills() {
		assertEquals(9.75,
				classUnderTest.calculatePrice(ProductFactory.fromString("1 packet of headache pills at 9.75")), 0.0);
	}

	@Test
	public void calculateTaxImportedBoxOfChocolates() {
		assertEquals(11.85,
				classUnderTest.calculatePrice(ProductFactory.fromString("1 imported box of chocolates at 11.25")), 0.0);
	}
	
	@Test
	public void calculateTaxTwoExemptProducts() {
		assertEquals((22.5),
				classUnderTest.calculatePrice(ProductFactory.fromString("2 books at 11.25")), 0.0);
	}
	
	@Test
	public void calculateTaxTwoImportedExemptProducts() {
		assertEquals(23.7,
				classUnderTest.calculatePrice(ProductFactory.fromString("2 imported books at 11.25")), 0.0);
	}
	
	@Test
	public void calculateTaxTwoTaxedtProducts() {
		assertEquals((24.8),
				classUnderTest.calculatePrice(ProductFactory.fromString("2 bottles of perfume at 11.25")), 0.0);
	}
	
	@Test
	public void calculateTaxTwoImportedTaxedProducts() {
		assertEquals(25.9,
				classUnderTest.calculatePrice(ProductFactory.fromString("2 imported bottles of perfume at 11.25")), 0.0);
	}
}
