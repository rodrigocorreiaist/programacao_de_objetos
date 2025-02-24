package hva.employees;

import hva.species.Specie;

import java.util.ArrayList;

/**
 * Class representing a Veterinary, a type of Employee.
 */
public class Veterinary extends Employee {

	/**
	 * Constructs a Veterinary with the specified ID and name.
	 *
	 * @param id the unique identifier of the veterinary
	 * @param name the name of the veterinary
	 */
	public Veterinary(String id, String name) {
		super(id, name);
		_type = "VET";
	}

	/**
	 * Calculates the satisfaction of the veterinary.
	 *
	 * @param e the employee whose satisfaction is to be calculated
	 * @return the satisfaction of the veterinary
	 */
	@Override
	public double satisfaction(Employee e) {
		return 0.0;
	}

	/**
	 * Calculates the effort of the veterinary.
	 *
	 * @param e the employee whose effort is to be calculated
	 * @return the effort of the veterinary
	 */
	@Override
	public double effort(Employee e) {
		return 0.0;
	}

	/**
	 * Returns a string representation of the veterinary.
	 *
	 * @return a string representation of the veterinary
	 */
	@Override
	public String toString() {
		return "VET" + "|" + _id + "|" + _name;
	}
}