package hva.employees;
import hva.species.Specie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing an employee.
 */
public abstract class Employee implements Serializable {
	protected final String _id;
	protected String _name;
	protected String _type;
	protected List<String> _responsibilities = new ArrayList<>();
	/**
	 * Constructs an Employee with the specified ID and name.
	 *
	 * @param id the unique identifier of the employee
	 * @param name the name of the employee
	 */
	public Employee(String id, String name) {
		this._id = id;
		this._name = name;
	}

	/**
	 * Gets the name of the employee.
	 *
	 * @return the name of the employee
	 */
	public String getName() {
		return this._name;
	}

	public String getType() {
		return this._type;
	}

	/**
	 * Gets the ID of the employee.
	 *
	 * @return the ID of the employee
	 */
	public String getID() {
		return this._id;
	}

	/**
	 * Sets the name of the employee.
	 *
	 * @param name the new name of the employee
	 */
	public void setName(String name) {
		this._name = name;
	}

	/**
	 * Calculates the satisfaction of the employee.
	 *
	 * @param e the employee whose satisfaction is to be calculated
	 * @return the satisfaction of the employee
	 */
	public abstract double satisfaction(Employee e);

	public List<String> getResponsibilities() {
		return this._responsibilities;
	}

	public void addResponsibility(String responsibilityID) {
		_responsibilities.add(responsibilityID);
	}

	public void removeResponsibility(String responsibilityID) {
		_responsibilities.remove(responsibilityID);
	}
	/**
	 * Calculates the effort of the employee.
	 *
	 * @param e the employee whose effort is to be calculated
	 * @return the effort of the employee
	 */
	public abstract double effort(Employee e);

	/**
	 * Returns a string representation of the employee.
	 *
	 * @return a string representation of the employee
	 */
	@Override
	public abstract String toString();
}
