package hva.habitat;

import hva.Hotel;

public class Perene extends Tree {

    /**
     * Constructor to create a Perene tree.
     *
     * @param id the unique identifier of the tree
     * @param name the name of the tree
     * @param age the age of the tree
     * @param difficulty the difficulty level of maintaining the tree
     * @param _type the type of the tree
     */
    public Perene(String id, String name, int age, int difficulty, String _type) {
        super(id, name, age, difficulty, _type);
    }

    /**
     * Calculate the cleaning effort required for the tree based on the season.
     *
     * @param season the current season (0: Spring, 1: Summer, 2: Autumn, 3: Winter)
     * @return the total cleaning effort required
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
                seasonalEffort = 1;
                break;
            case 2: // Autumn
                seasonalEffort = 1;
                break;
            case 3: // Winter
                seasonalEffort = 2;
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
