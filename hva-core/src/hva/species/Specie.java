package hva.species;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing a Specie.
 */
public class Specie implements Serializable, Comparable<Specie> {
    private final String _id;
    private String _name;
    private Map<String, Animal> _animals;

    /**
     * Constructs a Specie with the specified ID and name.
     *
     * @param id the unique identifier of the specie
     * @param name the name of the specie
     */
    public Specie(String id, String name) {
        this._id = id;
        this._name = name;
        this._animals = new TreeMap<>();
    }

    /**
     * Gets the name of the specie.
     *
     * @return the name of the specie
     */
    public String getName() {
        return this._name;
    }

    /**
     * Gets the id of the specie.
     *
     * @return the id of the specie
     */
    public String getID() {
        return this._id;
    }

    /**
     * Sets the name of the specie.
     *
     * @param name the new name of the specie
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * Adds an animal to the specie.
     *
     * @param animal the animal to add to the specie
     */
    public void addAnimal(Animal animal) {
        _animals.put(animal.getId(), animal);
    }

    @Override
    public int compareTo(Specie other) {
        return this._id.compareTo(other._id);
    }

    @Override
    public String toString() {
        return "Specie{" +
                "_id='" + _id + '\'' +
                ", _name='" + _name + '\'' +
                '}';
    }
}