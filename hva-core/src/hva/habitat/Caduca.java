package hva.habitat;

/**
 * Represents a deciduous tree in the habitat.
 */
public class Caduca extends Tree {

    /**
     * Constructs a new Caduca tree with the specified parameters.
     *
     * @param id the unique identifier of the tree
     * @param name the name of the tree
     * @param age the age of the tree
     * @param difficulty the difficulty level of maintaining the tree
     * @param type the type of the tree
     */
    public Caduca(String id, String name, int age, int difficulty, String type) {
        super(id, name, age, difficulty, type);
    }

    /**
     * Calculates the cleaning effort required for the tree based on the season.
     *
     * @param season the current season (0: Spring, 1: Summer, 2: Autumn, 3: Winter)
     * @return the cleaning effort required for the tree
     */
    @Override
    public double getCleaningEffort(int season) {
        
        // Determine the seasonal effort
        double seasonalEffort;
        switch (season) {
            case 0: // Spring
                seasonalEffort = 1;
                break;
            case 1: // Summer
                seasonalEffort = 2;
                break;
            case 2: // Autumn
                seasonalEffort = 5;
                break;
            case 3: // Winter
                seasonalEffort = 0;
                break;
            default:
                seasonalEffort = 1; // Default value
        }

        // Calculate the age factor
        double ageFactor = Math.log(getAge() + 1);

        // Calculate the total cleaning effort
        return (double)getDifficulty() * seasonalEffort * ageFactor;
    }

}
