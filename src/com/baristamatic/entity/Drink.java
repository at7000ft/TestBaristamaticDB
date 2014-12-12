package com.baristamatic.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Column;

import com.baristamatic.dao.IDrinkManager;

/**
 * <p>
 * Title: Drink.java
 * </p>
 * <p>
 * Description: Entity representing a drink including it's RecipeEntry List recipe, name and number.
 * 
 * </p>
 * <p>
 * 2008
 * </p>
 * 
 * @author RHolland
 */
@Entity
public class Drink {
	@Id
	protected Integer drinkNumber;

	@Column(unique=true, nullable=false) 
	protected String name;

	@Column(nullable = false)
	protected String displayName;

	@Column(nullable = false)
	@OneToMany(mappedBy = "drink", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private List<RecipeEntry> entryList = new ArrayList<RecipeEntry>();

	/**
	 * Gets the cost.
	 * 
	 * @return the cost
	 */
	public BigDecimal getCost() {
		BigDecimal totalCost = new BigDecimal(0);
		for (RecipeEntry entry : entryList) {
			Ingredient ingredient = entry.getIngredient();
			BigDecimal ingredientCost = ingredient.getUnitCost().multiply(new BigDecimal(entry.getIngredientCount()));
			totalCost = totalCost.add(ingredientCost);
		}
		return totalCost.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the display name.
	 * 
	 * @return the display name
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the display name.
	 * 
	 * @param displayName the new display name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Gets the drink number.
	 * 
	 * @return the drink number
	 */
	public Integer getDrinkNumber() {
		return drinkNumber;
	}

	/**
	 * Sets the drink number.
	 * 
	 * @param drinkNumber the new drink number
	 */
	public void setDrinkNumber(Integer drinkNumber) {
		this.drinkNumber = drinkNumber;
	}

	public List<RecipeEntry> getEntryList() {
		return entryList;
	}

	public void setEntryList(List<RecipeEntry> entryList) {
		this.entryList = entryList;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	public String detailToString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nDrink: " + drinkNumber.toString() + "," + displayName + " Cost - $" + getCost());
		for (RecipeEntry entry : entryList) {
			sb.append("\n\t");
			sb.append(entry.toString());
		}
		return sb.toString();
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	public String toString(IDrinkManager drinkManager) {
		return drinkNumber.toString() + "," + displayName + ",$" + getCost() + ","
					+ drinkManager.hasIngredientCount(this);

	}

}
