package hva.habitat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import hva.species.*;

/**
 * Class representing a Habitat.
 */
public class Habitat implements Serializable {
	private final String _id;
	private String _name;
	private int _area;
	private Map<String, Tree> _trees = new TreeMap<>();
	private Map<String, Animal> _animals;
	private Map<Specie, String> _influences;

	/**
	 * Constructs a Habitat with the specified ID, name, and area.
	 *
	 * @param id the unique identifier of the habitat
	 * @param name the name of the habitat
	 * @param area the area of the habitat
	 */
	public Habitat(String id, String name, int area) {
		this._id = id;
		this._name = name;
		this._area = area;
		this._animals = new TreeMap<>();
		this._influences = new TreeMap<>();
	}

	public Map<String, Tree> getTrees() { return _trees; }

	/**
	 * Sets the name of the habitat.
	 *
	 * @param name the new name of the habitat
	 */
	public void setName(String name) {
		this._name = name;
	}

	public Map<String, Animal> getAnimals() { return _animals; }

	/**
	 * Sets the area of the habitat.
	 *
	 * @param area the new area of the habitat
	 */
	public void setArea(int area) {
		this._area = area;
	}


	/**
	 * Gets the ID of the habitat.
	 *
	 * @return the ID of the habitat
	 */
	public String getId() {
		return this._id;
	}

	public Map<Specie, String> getInfluences() { return _influences; }
	public String getInfluence(Specie specie) { return _influences.get(specie); }


	/**
	 * Gets the name of the habitat.
	 *
	 * @return the name of the habitat
	 */
	public String getName() {
		return this._name;
	}

	/**
	 * Gets the area of the habitat.
	 *
	 * @return the area of the habitat
	 */
	public int getArea() {
		return this._area;
	}


	/**
	 * Adds an animal to the habitat.
	 *
	 * @param animal the animal to add to the habitat
	 */
	public void addAnimal(Animal animal) {
		_animals.put(animal.getId(), animal);
	}

	/**
	 * Returns a string representation of the habitat.
	 *
	 * @return a string representation of the habitat
	 */
	@Override
	public String toString() {
		return "HABITAT|" + _id + "|" + _name + "|" + _area + "|" + _trees.size();
	}
}