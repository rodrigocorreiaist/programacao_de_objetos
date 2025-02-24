package hva.vaccine;

import java.io.Serializable;

/**
 * Class representing a vaccination record.
 *
 * @param employeeID the unique identifier of the employee administering the vaccine
 * @param vacID the unique identifier of the vaccine
 * @param specieID the unique identifier of the species receiving the vaccine
 */
public class Vaccination implements Serializable {
    private String employeeID;
    private String vacID;
    private String specieID;

    /**
     * Constructs a new Vaccination instance.
     *
     * @param employeeID the unique identifier of the employee administering the vaccine
     * @param vacID the unique identifier of the vaccine
     * @param specieID the unique identifier of the species receiving the vaccine
     */
    public Vaccination(String employeeID, String vacID, String specieID) {
        this.employeeID = employeeID;
        this.vacID = vacID;
        this.specieID = specieID;
    }

    /**
     * Gets the unique identifier of the employee administering the vaccine.
     *
     * @return the employee ID
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Gets the unique identifier of the vaccine.
     *
     * @return the vaccine ID
     */
    public String getVacID() {
        return vacID;
    }

    /**
     * Gets the unique identifier of the species receiving the vaccine.
     *
     * @return the species ID
     */
    public String getSpecieID() {
        return specieID;
    }

    /**
     * Sets the unique identifier of the employee administering the vaccine.
     *
     * @param employeeID the new employee ID
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Sets the unique identifier of the vaccine.
     *
     * @param vacID the new vaccine ID
     */
    public void setVacID(String vacID) {
        this.vacID = vacID;
    }

    /**
     * Sets the unique identifier of the species receiving the vaccine.
     *
     * @param specieID the new species ID
     */
    public void setSpecieID(String specieID) {
        this.specieID = specieID;
    }

    /**
     * Returns a string representation of the vaccination record.
     *
     * @return a string in the format "REGISTO-VACINA|vacID|employeeID|specieID"
     */
    @Override
    public String toString() {
        return "REGISTO-VACINA" + "|" + vacID + "|" + getEmployeeID() + "|" + specieID;
    }
}
