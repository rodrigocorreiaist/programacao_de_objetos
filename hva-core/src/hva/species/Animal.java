package hva.species;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import hva.habitat.*;
import hva.vaccine.Vaccine;
import hva.vaccine.Vaccination;

    /**
     * Class representing an Animal.
     */
    public class Animal implements Serializable {
        private final String _idAnimal;
        private String _nameAnimal = "";
        private List<String> _healthRecord = new ArrayList<>();
        private List<Vaccination> _vaccinations = new ArrayList<>();
        private String _specieID;
        private String _habitatID;

        /**
         * Constructs an Animal with the specified ID, name, and species ID.
         *
         * @param id the unique identifier of the animal
         * @param name the name of the animal
         * @param specieID the ID of the species the animal belongs to
         */
        public Animal(String id, String name, String specieID) {
            this._idAnimal = id;
            this._nameAnimal = name;
            this._specieID = specieID;
        }

        /**
         * Gets the ID of the animal.
         *
         * @return the ID of the animal
         */
        public String getId() {
            return this._idAnimal;
        }

        public String getSpecieID() {
            return this._specieID;
        }

        /**
         * Gets the ID of the animal's habitat.
         *
         * @return the ID of the animal's habitat
         */
        public String getHabitatID (){ return this._habitatID; }

        /**
         * Gets the name of the animal.
         *
         * @return the name of the animal
         */
        public String getName() {
            return this._nameAnimal;
        }

        public List<Vaccination> getVaccinations() {
            return this._vaccinations;
        }

        /**
         * Gets the health record of the animal.
         *
         * @return the health record of the animal
         */
        public List<String> getHealthRecord() {
            return this._healthRecord;
        }
        /**
         * Sets the habitat ID of the animal.
         *
         * @param habitatID the new habitat ID of the animal
         */
        public void setHabitatID(String habitatID) {
            this._habitatID = habitatID;
        }

        /**
         * Sets the name of the animal.
         *
         * @param nameAnimal the new name of the animal
         */
        public void setnameAnimal(String nameAnimal) {
            this._nameAnimal = nameAnimal;
        }


        /**
         * Returns a string representation of the animal.
         *
         * @return a string representation of the animal
         */
        @Override
        public String toString() {
            if (_healthRecord.isEmpty()) {
                return "ANIMAL|" + _idAnimal + "|" + _nameAnimal + "|" + _specieID + "|VOID|" + _habitatID;
            } else {
                String current = _healthRecord.getLast();
                return "ANIMAL|" + _idAnimal + "|" + _nameAnimal + "|" + _specieID + "|" + current + "|" + _habitatID;
            
        }
    }
}