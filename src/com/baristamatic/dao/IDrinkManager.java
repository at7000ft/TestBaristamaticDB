package com.baristamatic.dao;

import java.util.List;

import com.baristamatic.entity.Drink;
import com.baristamatic.entity.InventoryEntry;

/**
 * <p>
 * Title: Interface IDrinkManager.
 * </p>
 * 
 * <p>
 * Description: Abstract Drink management from implementation
 * 
 * </p>
 * <p>
 * 2008
 * </p>
 * 
 * @author RHolland
 */
public interface IDrinkManager {
	public List<Drink> getAllDrinks();

	public Drink getDrink(Integer drinkNumber);

	public InventoryEntry getInventoryEntry(String name);

	public InventoryEntry decrementInventoryEntry(String name, int decrementCount);

	public List<InventoryEntry> getInventoryEntries();
	
	public boolean hasIngredientCount(Drink drink);
	
	public void adjustInventory(Drink drink);
}
