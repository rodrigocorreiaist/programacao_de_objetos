package hva.vaccine;

import hva.species.*;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.StringJoiner;
import java.util.TreeMap;

/**
 * Class representing a Vaccine.
 */
public class Vaccine implements Serializable {
    private final String _id;
    private String _name = "";
    private List<String> _species;  // IDs of the species that can take the vaccine

    /**
     * Constructs a Vaccine with the specified ID, name, and list of species IDs.
     *
     * @param id the unique identifier of the vaccine
     * @param name the name of the vaccine
     * @param species the list of species IDs that can take the vaccine
     */
    public Vaccine(String id, String name, List<String> species) {
        _id = id;
        _name = name;
        _species = species;
    }

    /**
     * Gets the ID of the vaccine.
     *
     * @return the ID of the vaccine
     */
    public String getid() {
        return this._id;
    }

    /**
     * Gets the name of the vaccine.
     *x
     * @return the name of the vaccine
     */
    public String getname() {
        return this._name;
    }

    /**
     * Gets the list of species IDs that can take the vaccine.
     *
     * @return the list of species IDs
     */
    public List<String> getSpecies() {
        return this._species;
    }


    /**
     * Sets the name of the vaccine.
     *
     * @param name the new name of the vaccine
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * Sets the list of species IDs that can take the vaccine.
     *
     * @param species the new list of species IDs
     */
    public void setSpecies(List<String> species) {
        this._species = species;
    }

    /**
     * Returns a string representation of the vaccine.
     * The format is: VACCINE|id|name|0 or VACCINE|id|name|0|species1,species2,...
     *
     * @return a string representation of the vaccine
     */
    public String toString(int timesUsed) {
        if (_species.isEmpty())
            return "VACINA|" + _id + "|" + _name + "|" + "0";
        else {
            StringJoiner speciesJoiner = new StringJoiner(",");
            _species.sort(String.CASE_INSENSITIVE_ORDER);
            for (String s : _species)
                speciesJoiner.add(s);
            if (!speciesJoiner.toString().isEmpty())
                return "VACINA|" + _id + "|" + _name + "|" + timesUsed +"|" + speciesJoiner.toString();
            else
                return "VACINA|" + _id + "|" + _name + "|" + timesUsed;
        }
    }

}
