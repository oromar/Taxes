package br.com.oromar.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.oromar.business.Basket;
import br.com.oromar.business.TaxCalculator;
import br.com.oromar.factory.ProductFactory;

public class InputHandler {

	public static List<String> readInputFile(String fileName) {

		var result = new ArrayList<String>();

		var file = new File(fileName);
		
		if (!file.exists()) {
			throw new RuntimeException("File: " + fileName + " not found");
		}

		try (var reader = new BufferedReader(new FileReader(file))) {
			
			while (reader.ready()) {
				
				var line = reader.readLine();
				
				if (!line.isEmpty()) {
					result.add(line);
				}
			}
			
		} catch (FileNotFoundException e) {
			
			throw new RuntimeException("input.txt file not found.");
			
		} catch (IOException e) {
			
			throw new RuntimeException("error in read input file.");
		}		
		return result;
	}
	
	public static void main(String[] args) {
		
		var items = InputHandler.readInputFile(args[0]);
		
		var basket = new Basket(new TaxCalculator());
		
		for (var item : items) {
			basket.add(ProductFactory.fromString(item));
		}
		
		System.out.println(basket);
	}
}
