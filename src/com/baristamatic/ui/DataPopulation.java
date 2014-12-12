package com.baristamatic.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.baristamatic.entity.Drink;
import com.baristamatic.entity.Ingredient;
import com.baristamatic.entity.InventoryEntry;
import com.baristamatic.entity.RecipeEntry;


public class DataPopulation {
	private static Integer defaultInventoryCount = 10;
	
	public static void cleanTables(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		//All RecipeEntrys are deleted when Drink is deleted
		Query q = em.createQuery ("from Drink");
		List<Drink> dresults = q.getResultList ();
		for (Drink drink : dresults) {
			em.remove(drink);
		}
		
		q = em.createQuery ("from InventoryEntry");
		List<InventoryEntry> ivresults = q.getResultList ();
		for (InventoryEntry entry : ivresults) {
			em.remove(entry);
		}
		
		q = em.createQuery ("from Ingredient");
		List<Ingredient> iresults = q.getResultList ();
		for (Ingredient ingredient : iresults) {
			em.remove(ingredient);
		}

		tx.commit();
		em.close();
	}
	
	public static void populate(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// Populate Ingredients
		Ingredient coffeeIngredient = new Ingredient();
		coffeeIngredient.setName("coffee");
		coffeeIngredient.setUnitCost(new BigDecimal(.75));
		em.persist(coffeeIngredient);
		
		Ingredient sugarIngredient = new Ingredient();
		sugarIngredient.setName("sugar");
		sugarIngredient.setUnitCost(new BigDecimal(.25));
		em.persist(sugarIngredient);
		
		Ingredient creamIngredient = new Ingredient();
		creamIngredient.setName("cream");
		creamIngredient.setUnitCost(new BigDecimal(.25));
		em.persist(creamIngredient);
		
		Ingredient decafIngredient = new Ingredient();
		decafIngredient.setName("decafCoffee");
		decafIngredient.setUnitCost(new BigDecimal(.75));
		em.persist(decafIngredient);
		
		Ingredient steamedMilkIngredient = new Ingredient();
		steamedMilkIngredient.setName("steamedMilk");
		steamedMilkIngredient.setUnitCost(new BigDecimal(.35));
		em.persist(steamedMilkIngredient);
		
		Ingredient foamedMilkIngredient = new Ingredient();
		foamedMilkIngredient.setName("foamedMilk");
		foamedMilkIngredient.setUnitCost(new BigDecimal(.35));
		em.persist(foamedMilkIngredient);
		
		Ingredient espressoIngredient = new Ingredient();
		espressoIngredient.setName("espresso");
		espressoIngredient.setUnitCost(new BigDecimal(1.10));
		em.persist(espressoIngredient);
		
		Ingredient cocoaIngredient = new Ingredient();
		cocoaIngredient.setName("cocoa");
		cocoaIngredient.setUnitCost(new BigDecimal(.90));
		em.persist(cocoaIngredient);
		
		Ingredient whippedCreamIngredient = new Ingredient();
		whippedCreamIngredient.setName("whippedCream");
		whippedCreamIngredient.setUnitCost(new BigDecimal(1.00));
		em.persist(whippedCreamIngredient);
		
		// Populate Ingredient Inventory
		InventoryEntry invEntry = new InventoryEntry();
		invEntry.setDisplayName("Coffee");
		invEntry.setIngredient(coffeeIngredient);
		invEntry.setIngredientCount(defaultInventoryCount);
		invEntry.setName(coffeeIngredient.getName());
		em.persist(invEntry);
		
		invEntry = new InventoryEntry();
		invEntry.setDisplayName("Decaf Coffee");
		invEntry.setIngredient(decafIngredient);
		invEntry.setIngredientCount(defaultInventoryCount);
		invEntry.setName(decafIngredient.getName());
		em.persist(invEntry);
		
		invEntry = new InventoryEntry();
		invEntry.setDisplayName("Sugar");
		invEntry.setIngredient(sugarIngredient);
		invEntry.setIngredientCount(defaultInventoryCount);
		invEntry.setName(sugarIngredient.getName());
		em.persist(invEntry);
		
		invEntry = new InventoryEntry();
		invEntry.setDisplayName("Cream");
		invEntry.setIngredient(creamIngredient);
		invEntry.setIngredientCount(defaultInventoryCount);
		invEntry.setName(creamIngredient.getName());
		em.persist(invEntry);
		
		invEntry = new InventoryEntry();
		invEntry.setDisplayName("SteamedMilk");
		invEntry.setIngredient(steamedMilkIngredient);
		invEntry.setIngredientCount(defaultInventoryCount);
		invEntry.setName(steamedMilkIngredient.getName());
		em.persist(invEntry);
		
		invEntry = new InventoryEntry();
		invEntry.setDisplayName("FoamedMilk");
		invEntry.setIngredient(foamedMilkIngredient);
		invEntry.setIngredientCount(defaultInventoryCount);
		invEntry.setName(foamedMilkIngredient.getName());
		em.persist(invEntry);
		
		invEntry = new InventoryEntry();
		invEntry.setDisplayName("Espresso");
		invEntry.setIngredient(espressoIngredient);
		invEntry.setIngredientCount(defaultInventoryCount);
		invEntry.setName(espressoIngredient.getName());
		em.persist(invEntry);
		
		invEntry = new InventoryEntry();
		invEntry.setDisplayName("Cocoa");
		invEntry.setIngredient(cocoaIngredient);
		invEntry.setIngredientCount(defaultInventoryCount);
		invEntry.setName(cocoaIngredient.getName());
		em.persist(invEntry);
		
		invEntry = new InventoryEntry();
		invEntry.setDisplayName("WhippedCream");
		invEntry.setIngredient(whippedCreamIngredient);
		invEntry.setIngredientCount(defaultInventoryCount);
		invEntry.setName(whippedCreamIngredient.getName());
		em.persist(invEntry);
		
		// Populate Coffee Drink
		Drink drink = new Drink();
		drink.setDrinkNumber(1);
		drink.setDisplayName("Coffee");
		drink.setName("coffee");
		
		RecipeEntry coffeeEntry = new RecipeEntry();
		coffeeEntry.setIngredientCount(3);
		coffeeEntry.setIngredient(coffeeIngredient);
		coffeeEntry.setDrink(drink);
		
		RecipeEntry sugarEntry = new RecipeEntry();
		sugarEntry.setIngredientCount(1);
		sugarEntry.setIngredient(sugarIngredient);
		sugarEntry.setDrink(drink);
		
		RecipeEntry creamEntry = new RecipeEntry();
		creamEntry.setIngredientCount(1);
		creamEntry.setIngredient(creamIngredient);
		creamEntry.setDrink(drink);
		
		List<RecipeEntry> entryList = new ArrayList<RecipeEntry>();
		entryList.add(coffeeEntry);
		entryList.add(sugarEntry);
		entryList.add(creamEntry);
		drink.setEntryList(entryList);

		em.persist(drink);
 
		
		// Populate Decaf Coffee Drink
		Drink ddrink = new Drink();
		ddrink.setDrinkNumber(2);
		ddrink.setDisplayName("Decaf Coffee");
		ddrink.setName("decafCoffee");
		
		RecipeEntry decafCoffeeEntry = new RecipeEntry();
		decafCoffeeEntry.setIngredientCount(3);
		decafCoffeeEntry.setIngredient(decafIngredient);
		decafCoffeeEntry.setDrink(ddrink);
		
		RecipeEntry dsugarEntry = new RecipeEntry();
		dsugarEntry.setIngredientCount(1);
		dsugarEntry.setIngredient(sugarIngredient);
		dsugarEntry.setDrink(drink);
		
		RecipeEntry dcreamEntry = new RecipeEntry();
		dcreamEntry.setIngredientCount(1);
		dcreamEntry.setIngredient(creamIngredient);
		dcreamEntry.setDrink(ddrink);
 
		
		List<RecipeEntry> dentryList  = new ArrayList<RecipeEntry>();
		dentryList.add(decafCoffeeEntry);
		dentryList.add(dsugarEntry);
		dentryList.add(dcreamEntry);
		ddrink.setEntryList(dentryList);

		em.persist(ddrink);	
		
		// Populate caffeLatte Drink
		Drink ldrink = new Drink();
		ldrink.setDrinkNumber(3);
		ldrink.setDisplayName("Caffe Latte");
		ldrink.setName("caffeLatte");
		
		RecipeEntry espressoEntry = new RecipeEntry();
		espressoEntry.setIngredientCount(2);
		espressoEntry.setIngredient(espressoIngredient);
		espressoEntry.setDrink(ldrink);
		
		RecipeEntry smilkEntry = new RecipeEntry();
		smilkEntry.setIngredientCount(1);
		smilkEntry.setIngredient(steamedMilkIngredient);
		smilkEntry.setDrink(ldrink);
		
		List<RecipeEntry> lentryList  = new ArrayList<RecipeEntry>();
		lentryList.add(espressoEntry);
		lentryList.add(smilkEntry);
		ldrink.setEntryList(lentryList);

		em.persist(ldrink);	
		
		// Populate caffeAmericano Drink
		Drink adrink = new Drink();
		adrink.setDrinkNumber(4);
		adrink.setDisplayName("Caffe Americano");
		adrink.setName("caffeAmericano");
		
		RecipeEntry aespressoEntry = new RecipeEntry();
		aespressoEntry.setIngredientCount(3);
		aespressoEntry.setIngredient(espressoIngredient);
		aespressoEntry.setDrink(adrink);
 
		List<RecipeEntry> aentryList  = new ArrayList<RecipeEntry>();
		aentryList.add(aespressoEntry);
		adrink.setEntryList(aentryList);

		em.persist(adrink);	
 
		// Populate caffeMocha Drink
		Drink mdrink = new Drink();
		mdrink.setDrinkNumber(5);
		mdrink.setDisplayName("Caffe Mocha");
		mdrink.setName("caffeMocha");
		
		RecipeEntry meEntry = new RecipeEntry();
		meEntry.setIngredientCount(1);
		meEntry.setIngredient(espressoIngredient);
		meEntry.setDrink(mdrink);
		
		RecipeEntry mcEntry = new RecipeEntry();
		mcEntry.setIngredientCount(1);
		mcEntry.setIngredient(cocoaIngredient);
		mcEntry.setDrink(mdrink);
		
		RecipeEntry smeEntry = new RecipeEntry();
		smeEntry.setIngredientCount(1);
		smeEntry.setIngredient(steamedMilkIngredient);
		smeEntry.setDrink(mdrink);
		
		RecipeEntry wceEntry = new RecipeEntry();
		wceEntry.setIngredientCount(1);
		wceEntry.setIngredient(whippedCreamIngredient);
		wceEntry.setDrink(mdrink);
 
		List<RecipeEntry> mentryList  = new ArrayList<RecipeEntry>();
		mentryList.add(meEntry);
		mentryList.add(mcEntry);
		mentryList.add(smeEntry);
		mentryList.add(wceEntry);
		mdrink.setEntryList(mentryList);

		em.persist(mdrink);	
		
		// Populate Cappuccio Drink
		Drink cadrink = new Drink();
		cadrink.setDrinkNumber(6);
		cadrink.setDisplayName("Cappuccio");
		cadrink.setName("cappiccino");
		
		RecipeEntry caeEntry = new RecipeEntry();
		caeEntry.setIngredientCount(2);
		caeEntry.setIngredient(espressoIngredient);
		caeEntry.setDrink(cadrink);
		
		RecipeEntry casmEntry = new RecipeEntry();
		casmEntry.setIngredientCount(1);
		casmEntry.setIngredient(steamedMilkIngredient);
		casmEntry.setDrink(cadrink);
		
		RecipeEntry cafmEntry = new RecipeEntry();
		cafmEntry.setIngredientCount(1);
		cafmEntry.setIngredient(foamedMilkIngredient);
		cafmEntry.setDrink(cadrink);
 
 
		List<RecipeEntry> caentryList  = new ArrayList<RecipeEntry>();
		caentryList.add(caeEntry);
		caentryList.add(casmEntry);
		caentryList.add(cafmEntry);
		cadrink.setEntryList(caentryList);

		em.persist(cadrink);	
		
		tx.commit();
		em.close();
	}
}
