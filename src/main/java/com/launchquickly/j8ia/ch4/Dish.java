package com.launchquickly.j8ia.ch4;

public class Dish {

	public enum Type {
		MEAT, FISH, OTHER
	}

	private final String name;
	private final boolean vegetarian;
	private final int calories;
	private final Type type;

	public Dish(final String nm, final boolean veggie, final int cals, final Type t) {
		this.name = nm;
		this.vegetarian = veggie;
		this.calories = cals;
		this.type = t;
	}

	public int getCalories() {
		return this.calories;
	}

	public String getName() {
		return this.name;
	}

	public Type getType() {
		return this.type;
	}

	public boolean isVegetarian() {
		return this.vegetarian;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
