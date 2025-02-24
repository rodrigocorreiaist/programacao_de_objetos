package hva.employees;

/**
 * Class representing a Keeper, a type of Employee.
 */
public class Keeper extends Employee {
	/**
	 * Constructs a Keeper with the specified ID and name.
	 *
	 * @param id the unique identifier of the keeper
	 * @param name the name of the keeper
	 */
	public Keeper(String id, String name) {
		super(id, name);
		_type = "TRT";
	}

	/**
	 * Calculates the satisfaction of the keeper.
	 *
	 * @param e the employee whose satisfaction is to be calculated
	 * @return the satisfaction of the keeper
	 */
	@Override
	public double satisfaction(Employee e) {
		return 0.0;
	}

	/**
	 * Calculates the effort of the keeper.
	 *
	 * @param e the employee whose effort is to be calculated
	 * @return the effort of the keeper
	 */
	@Override
	public double effort(Employee e) {
		return 0.0;
	}

	/**
	 * Returns a string representation of the keeper.
	 *
	 * @return a string representation of the keeper
	 */
	@Override
	public String toString() {
		return "TRT" + "|" + _id + "|" + _name;
	}
}