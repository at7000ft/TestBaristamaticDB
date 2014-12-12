
package com.baristamatic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * <p> Title: RecipeEntry.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> 2008</p>
 * @author RHolland
 *
 *
 */

@Entity
public class RecipeEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false) 
	private Integer ingredientCount;
	/*
	 * ingredient_id is foreign key column generated in RecipeEntry table
	 * referencing Ingredient table.
	 */
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "ingredient_id")  //Optional but good documentation
	private Ingredient ingredient;
	
	@ManyToOne
	@JoinColumn(name = "drink_id")
	private Drink drink;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIngredientCount() {
		return ingredientCount;
	}

	public void setIngredientCount(Integer ingredientCount) {
		this.ingredientCount = ingredientCount;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}
	/*
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("RecipeEntry: " + ingredientCount + "  ," + ingredient.getName());
		return sb.toString();
	}
}
