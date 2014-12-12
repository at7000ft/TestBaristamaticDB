package com.baristamatic.ui;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.baristamatic.dao.IDrinkManager;
import com.baristamatic.entity.Drink;
import com.baristamatic.entity.InventoryEntry;

/**
 * <p>
 * Title: Main.java
 * </p>
 * <p>
 * Description: Main console interface
 * 
 * </p>
 * <p>
 * 2008
 * </p>
 * 
 * @author RHolland
 */
public class Main {
	private ApplicationContext context;
	private static EntityManagerFactory emf;
	private IDrinkManager drinkManager;
	private static final String restockCommandString = "r";
	private static final String quitCommandString = "q";
	private List<String> validDrinkNumbers;
	private static final List<String> validCommands = Arrays.asList(restockCommandString, quitCommandString);

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Main main = new Main();
		main.init();
		main.mainLoop();
	}

	/**
	 * Inits.
	 */
	private void init() {
		// Suppress Spring log messages on console
		DOMConfigurator.configure("config/log4j.xml");

		context = new FileSystemXmlApplicationContext("config/applicationContext.xml");
		emf = (EntityManagerFactory) context.getBean("entityManagerFactory");
		drinkManager = (IDrinkManager) context.getBean("drinkManager");
		initData();
	}

	/**
	 * Inits the data.
	 */
	private void initData() {
		DataPopulation.cleanTables(emf);
		DataPopulation.populate(emf);
		initValidDrinkNumbers(drinkManager.getAllDrinks());
	}

	/**
	 * Main loop.
	 */
	private void mainLoop() {
		String commandString = "";

		// ... Initialize Scanner to read from console.
		Scanner input = new Scanner(System.in);

		while (!commandString.equals(quitCommandString)) {
			showInventory();
			showMenu();
			out.println("\n");

			try {
				// Get input
				commandString = input.nextLine().trim();

				// Test for valid entry
				if (!isValidEntry(commandString)) {
					out.println("Invalid selection: " + commandString + "\n");
					continue;
				}

				// Test for quit command
				if (commandString.equals(quitCommandString)) {
					continue;
				}

				// Test for restock command
				if (commandString.equals(restockCommandString)) {
					initData();
					continue;
				}

				// If a valid drink number was entered attempt to dispense a drink
				Drink requestedDrink = drinkManager.getDrink(new Integer(commandString));
				if (requestedDrink == null) {
					out.println("Out of stock: " + commandString + "\n");
					continue;
				}
				if (drinkManager.hasIngredientCount(requestedDrink)) {
					out.println("Dispensing: " + requestedDrink.getDisplayName() + "\n");
					drinkManager.adjustInventory(requestedDrink);
				} else {
					out.println("Out of stock: " + requestedDrink.getDisplayName() + "\n");
					continue;
				}
			} catch (StringIndexOutOfBoundsException e) {
				out.println("Invalid selection: " + commandString + "\n");
				continue;
			} catch (NoSuchElementException e) {
				out.println("Invalid selection: " + commandString + "\n");
				continue;
			}
		}

		System.exit(0);
	}

	/**
	 * Show inventory.
	 */
	private void showInventory() {
		out.println("Inventory:");
		List<InventoryEntry> list = drinkManager.getInventoryEntries();
		for (InventoryEntry entry : list) {
			out.println(entry.toString());
		}
	}

	/**
	 * Show menu.
	 */
	private void showMenu() {
		out.println("\nMenu:");
		for (Drink drink : drinkManager.getAllDrinks()) {
			out.println(drink.toString(drinkManager));
		}
	}

	/**
	 * Inits the valid drink numbers.
	 * 
	 * @param drinks the drinks
	 */
	private void initValidDrinkNumbers(List<Drink> drinks) {
		validDrinkNumbers = new ArrayList<String>();
		for (Drink drink : drinks) {
			String number = drink.getDrinkNumber().toString();
			validDrinkNumbers.add(number);
		}
	}

	/**
	 * Checks if is valid entry.
	 * 
	 * @param entry the entry
	 * 
	 * @return true, if is valid entry
	 */
	private boolean isValidEntry(String entry) {
		if (entry.length() == 1 && validCommands.contains(entry)) {
			return true;
		} else if (testForDigits(entry) && validDrinkNumbers.contains(entry)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Test for digits.
	 * 
	 * @param testStr the test str
	 * 
	 * @return true, if successful
	 */
	private boolean testForDigits(String testStr) {
		boolean stat = true;
		for (int i = 0; i < testStr.length(); i++) {
			if (!Character.isDigit(testStr.charAt(i))) {
				stat = false;
				break;
			}
		}
		return stat;
	}

}
