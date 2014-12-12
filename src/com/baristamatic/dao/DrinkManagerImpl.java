

package com.baristamatic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baristamatic.entity.Drink;
import com.baristamatic.entity.Ingredient;
import com.baristamatic.entity.InventoryEntry;
import com.baristamatic.entity.RecipeEntry;

/**
 * <p>
 * Title: DrinkManagerImpl.java
 * </p>
 * <p>
 * Description:
 * 
 * </p>
 * <p>
 * 2008
 * </p>
 * 
 * @author RHolland
 */

// Apply Exception translation
@Repository
// Cause all methods in this class to get a transaction when called, trans is started before method runs and committed after exit
@Transactional
public class DrinkManagerImpl implements IDrinkManager {

	/*
	 * Request the injection of a shared EntityManager, a shared, thread-safe proxy for the actual transactional
	 * EntityManager. the @PersistenceContext annotation has an optional attribute type, which defaults to
	 * PersistenceContextType.TRANSACTION. This default is what is needed to receive a "shared EntityManager" proxy.
	 */
	@PersistenceContext
	private EntityManager em;

	/*
	 * @see com.baristamatic.dao.IDrinkManager#decrementInventoryEntry(java.lang.String, int)
	 */
	public InventoryEntry decrementInventoryEntry(String name, int decrementCount) {
		Query query = em.createQuery("from InventoryEntry as p where p.name = :name");
		query.setParameter("name", name);
		List<InventoryEntry> list = query.getResultList();
		InventoryEntry entry = list.get(0);
		int currentCount = entry.getIngredientCount();
		int newCount = currentCount - decrementCount;
		entry.setIngredientCount(newCount);
		return em.merge(entry);
	}

	/*
	 * @see com.baristamatic.dao.IDrinkManager#getAllDrinks()
	 */
	public List<Drink> getAllDrinks() {
		Query query = em.createQuery("from Drink"); // OR "select x from Drink x"
		return query.getResultList();
	}

	/*
	 * @see com.baristamatic.dao.IDrinkManager#getDrink(java.lang.Integer)
	 */
	public Drink getDrink(Integer drinkNumber) {
		return em.find(Drink.class, drinkNumber);
	}

	/*
	 * @see com.baristamatic.dao.IDrinkManager#getInventoryEntries()
	 */
	public List<InventoryEntry> getInventoryEntries() {
		Query query = em.createQuery("from InventoryEntry");
		return query.getResultList();
	}

	/*
	 * @see com.baristamatic.dao.IDrinkManager#getInventoryEntry(java.lang.String)
	 */
	public InventoryEntry getInventoryEntry(String name) {
		Query query = em.createQuery("from InventoryEntry as p where p.name = :name");
		query.setParameter("name", name);
		List<InventoryEntry> list = query.getResultList();
		return list.get(0);
	}

	/* 
	 * @see com.baristamatic.dao.IDrinkManager#adjustInventory(com.baristamatic.entity.Drink)
	 */
	public void adjustInventory(Drink drink) {
		List<RecipeEntry> entries = drink.getEntryList();
		for (RecipeEntry recipeEntry : entries) {
			int decrementCount = recipeEntry.getIngredientCount();
			String name = recipeEntry.getIngredient().getName();
			decrementInventoryEntry(name, decrementCount);
		}
	}

	/*
	 * @see com.baristamatic.dao.IDrinkManager#hasIngredientCount(com.baristamatic.dao.IDrinkManager, java.util.List)
	 */
	public boolean hasIngredientCount(Drink drink) {
		List<RecipeEntry> entries = drink.getEntryList();
		for (RecipeEntry recipeEntry : entries) {
			Ingredient ingredient = recipeEntry.getIngredient();
			int requiredCount = recipeEntry.getIngredientCount();
			InventoryEntry entry = getInventoryEntry(ingredient.getName());
			int availableCount = entry.getIngredientCount();
			if (availableCount < requiredCount) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the em.
	 * 
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * Sets the em.
	 * 
	 * @param em the new em
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}
}
