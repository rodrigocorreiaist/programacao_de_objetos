package hva.habitat;

import java.io.Serializable;

/**
 * Abstract class representing a Tree in a habitat.
 * Implements Serializable interface.
 */
public abstract class Tree implements Serializable {
	private final String _id;
	private String _name;
	private float _years;
	private int _age;
	private int _difficulty;
	private Habitat _habitat;
	private String _type;

	/**
	 * Constructor that initializes all attributes of the Tree.
	 *
	 * @param id the unique identifier of the tree
	 * @param name the name of the tree
	 * @param age the age of the tree
	 * @param difficulty the difficulty level of maintaining the tree
	 * @param type the type of the tree
	 */
	public Tree(String id, String name, int age, int difficulty, String type) {
		this._id = id;
		this._name = name;
		this._age = age;
		this._difficulty = difficulty;
		this._type = type;
	}

	/**
	 * Abstract method to get the cleaning effort required for the tree based on the season.
	 *
	 * @param season the season for which the cleaning effort is calculated
	 * @return the cleaning effort as a double
	 */
	public abstract double getCleaningEffort(int season);

	/**
	 * Getter for the tree ID.
	 *
	 * @return the unique identifier of the tree
	 */
	public String getTreeID() {
		return this._id;
	}

	/**
	 * Getter for the type of the tree.
	 *
	 * @return the type of the tree
	 */
	public String getType() {
		return this._type;
	}

	/**
	 * Getter for the name of the tree.
	 *
	 * @return the name of the tree
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Setter for the name of the tree.
	 *
	 * @param name the new name of the tree
	 */
	public void setName(String name) {
		this._name = name;
	}

	/**
	 * Getter for the years of growth of the tree.
	 *
	 * @return the years of growth of the tree
	 */
	public float getYears() {
		return _years;
	}

	/**
	 * Setter for the years of growth of the tree.
	 *
	 * @param years the new years of growth of the tree
	 */
	public void setYears(float years) {
		this._years = years;
	}

	/**
	 * Getter for the age of the tree.
	 *
	 * @return the age of the tree
	 */
	public int getAge() {
		return _age;
	}

	/**
	 * Setter for the age of the tree.
	 *
	 * @param age the new age of the tree
	 */
	public void setAge(int age) {
		this._age = age;
	}

	/**
	 * Getter for the difficulty level of maintaining the tree.
	 *
	 * @return the difficulty level of maintaining the tree
	 */
	public int getDifficulty() {
		return _difficulty;
	}

	/**
	 * Setter for the difficulty level of maintaining the tree.
	 *
	 * @param difficulty the new difficulty level of maintaining the tree
	 */
	public void setDifficulty(int difficulty) {
		this._difficulty = difficulty;
	}

	/**
	 * Getter for the habitat of the tree.
	 *
	 * @return the habitat of the tree
	 */
	public Habitat getHabitat() {
		return _habitat;
	}

	/**
	 * Setter for the habitat of the tree.
	 *
	 * @param habitat the new habitat of the tree
	 */
	public void setHabitat(Habitat habitat) {
		this._habitat = habitat;
	}

	/**
	 * Returns a string representation of the tree.
	 *
	 * @return a string representation of the tree
	 */
	@Override
	public String toString(){
		return  "ÁRVORE"+"|"+ _id+ "|" +_name + "|" + _age + "|"+ _difficulty + "|" + _type+ "|";
	}
}
