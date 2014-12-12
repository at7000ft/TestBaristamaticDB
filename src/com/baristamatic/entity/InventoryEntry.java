package com.baristamatic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * <p>
 * Title: InventoryEntry.java
 * </p>
 * <p>
 * Description: Entity container for Inventory Ingredients, maintains unit counts.
 * 
 * </p>
 * <p>
 * 2008
 * </p>
 * 
 * @author RHolland
 */
@Entity
public class InventoryEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;
	
	@Column(nullable=false) 
	private String displayName;
	
	@Column(unique=true, nullable=false) 
	private String name;  //Same as Ingredient name
	
	//Add ingredient_id column to this table referencing Ingredient primary key
	@OneToOne
	@JoinColumn(name="ingredient_id")  //Optional but good documentation
	private Ingredient ingredient;
	
	@Column(nullable=false) 
	private Integer ingredientCount;

	/**
	 * Instantiates a new inventory entry.
	 */
	public InventoryEntry() {
		super();
	}

	public Integer getIngredientCount() {
		return ingredientCount;
	}

	public void setIngredientCount(Integer ingredientCount) {
		this.ingredientCount = ingredientCount;
	}

	/**
	 * Current inventory count.
	 * 
	 * @return the int
	 */
	public int currentInventoryCount() {
		return ingredientCount;
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
	 * Sets the ingredient.
	 * 
	 * @param ingredient the new ingredient
	 */
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	/**
	 * Gets the ingredient.
	 * 
	 * @return the ingredient
	 */
	public Ingredient getIngredient() {
		return ingredient;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return displayName + "," + ingredientCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	

}
