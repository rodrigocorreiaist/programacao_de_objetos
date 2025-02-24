package hva;
import hva.employees.Employee;
import hva.exceptions.UnavailableFileException;
import hva.species.*;
import hva.employees.*;
import hva.habitat.*;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.io.Serial;
import java.util.Arrays;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import hva.exceptions.ImportFileException;
import hva.exceptions.UnrecognizedEntryException;
import hva.vaccine.Vaccination;
import hva.vaccine.Vaccine;

//FIXME import project classes

/**
 * Class representing a hotel that manages animals, species, habitats, vaccines, trees, and employees.
 * Provides methods to register and manage these entities, as well as calculate satisfaction and handle vaccinations.
 *
 * @param _animals the map of animals in the hotel
 * @param _species the map of species in the hotel
 * @param _habitats the map of habitats in the hotel
 * @param _vaccines the map of vaccines in the hotel
 * @param _trees the map of trees in the hotel
 * @param _vaccinations the list of vaccinations performed in the hotel
 * @param _wrongVaccinations the list of wrong vaccinations performed in the hotel
 * @param _employees the map of employees in the hotel
 * @param dirty the flag indicating if the hotel has been modified since the last save
 * @param _season the current season in the hotel
 */
public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    private TreeMap<String, Animal> _animals = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private TreeMap<String, Specie> _species = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private TreeMap<String, Habitat> _habitats = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private TreeMap<String, Vaccine> _vaccines = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private TreeMap<String, Tree> _trees = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private List<Vaccination> _vaccinations = new ArrayList<>();
    private List<Vaccination> _wrongVaccinations = new ArrayList<>();
    private TreeMap<String, Employee> _employees = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private boolean dirty = false;
    private int _season = 0;

    /**
     * Registers a new animal in the hotel.
     *
     * @param animalID   the unique identifier of the animal
     * @param animalName the name of the animal
     * @param specieID   the unique identifier of the species
     * @param habitatID  the unique identifier of the habitat
     */
    public void registerAnimal(String animalID, String animalName, String specieID, String habitatID) {
        Animal animal = new Animal(animalID, animalName, specieID);
        animal.setHabitatID(habitatID);
        _animals.put(animalID, animal);
        _species.get(specieID).addAnimal(animal);
        _habitats.get(habitatID).addAnimal(animal);
        dirty();
     }

    public void add1Season() { _season++; }
    public int getSeason() { return _season; }
    public void setSeason(int number) { _season = number; }
    /**
     * Registers a new habitat in the hotel.
     *
     * @param habitatID   the unique identifier of the habitat
     * @param habitatName the name of the habitat
     * @param area        the area of the habitat
     */
    public void registerHabitat(String habitatID, String habitatName, int area) {
        _habitats.put(habitatID, new Habitat(habitatID, habitatName, area));
        dirty();
    }

    public List<Vaccination> getVaccinations() { return _vaccinations; }
    public List<Vaccination> getWrongVaccinations() { return _wrongVaccinations; }

    /**
     * Registers a new vaccine in the hotel.
     *
     * @param vaccineID   the unique identifier of the vaccine
     * @param vaccineName the name of the vaccine
     * @param species     the list of species the vaccine is effective for
     */
    public void registerVaccine(String vaccineID, String vaccineName, List<String> species) {
        _vaccines.put(vaccineID, new Vaccine(vaccineID, vaccineName, species));
        dirty();
    }


    /**
     * Registers a new employee in the hotel.
     *
     * @param id   the unique identifier of the employee
     * @param name the name of the employee
     * @param type the type of the employee (e.g., "VETERINÁRIO" or "TRATADOR")
     */
    public void registerEmployee(String id, String name, String type) {
        if (type.equals("VETERINÁRIO") || type.equals("VET")) {
            _employees.put(id, new Veterinary(id, name));
        } else {
            _employees.put(id, new Keeper(id, name));
        }
        dirty();
    }


    /**
     * Adds a new species to the hotel.
     *
     * @param specieID   the unique identifier of the species
     * @param specieName the name of the species
     */
    public void addSpecie(String specieID, String specieName) {
        _species.put(specieID, new Specie(specieID, specieName));
        dirty();
    }

    public void changeHabitatArea(String habitatID, int habitatArea){
        Habitat habitat = getHabitats().get(habitatID);
        habitat.setArea(habitatArea);
        dirty();
    }

    public Map<String, Tree> getTrees() {
        return _trees;
    }


    /**
     * Returns the map of animals in the hotel.
     *
     * @return the map of animals
     */
    public Map<String, Animal> getAnimals() {
        return _animals;
    }

    /**
     * Returns the map of employees in the hotel.
     *
     * @return the map of employees
     */
    public Map<String, Employee> getEmployees() {
        return _employees;
    }

    /**
     * Returns the map of species in the hotel.
     *
     * @return the map of species
     */
    public Map<String, Specie> getSpecies() {
        return _species;
    }

    /**
     * Returns the map of habitats in the hotel.
     *
     * @return the map of habitats
     */
    public Map<String, Habitat> getHabitats() {
        return _habitats;
    }


    /**
     * Returns the map of vaccines in the hotel.
     *
     * @return the map of vaccines
     */
    public Map<String, Vaccine> getVaccines() {
        return _vaccines;
    }


    /**
    * Get whether the hotel has been modified since it was last cleaned. The
    * hotel is cleaned when it is saved to disk.
    *
    * @return the value of the dirty flag
    */
    public boolean isDirty() {
        return this.dirty;
    }

    /**
    * Turn the dirty flag off to indicate the hotel state has been saved.
    */
    public void clean() {
        this.dirty = false;
    }

    /**
    * Turn the dirty flag on to indicate a modification has occurred since last
    * clean-up (i.e. saved).
    */
    private void dirty() {
        this.dirty = true;
    }

    public int numberAnimalsBySpecie(String specieID) {
        int count = 0;
        for (Animal animal : _animals.values()) {
            if (animal.getSpecieID().equals(specieID)) {
                count++;
            }
        }
        return count;
    }

    int getSameResponsEmployees(String respID, String type) {
        int count = 0;
        for (Employee employee : _employees.values()) {
            if (employee.getResponsibilities().contains(respID) && employee.getType().equals(type)) {
                count++;
            }
        }
        if (count > 0) count--;
        return count;
    }

    public int calculateSatisfaction(Employee employee, int season) {
        double satisfaction = 0;
        double work = 0;
        List<String> _responsibilities = employee.getResponsibilities();
        if (employee.getType().equals("VET")) {
            for (String specieId : _responsibilities) {
                int population = numberAnimalsBySpecie(specieId);
                int numVets = getSameResponsEmployees(specieId, "VET");
                if (numVets == 0) numVets = 1;
                work += population / numVets;
            }
            satisfaction = 20 - work;

        } else if (employee.getType().equals("TRT")) {
            for (String habitatId : _responsibilities) {
                Habitat habitat = getHabitats().get(habitatId);
                int population = habitat.getAnimals().size();
                int area = habitat.getArea();
                int numTrts = getSameResponsEmployees(habitatId, "TRT");
                double effortTrees = 0;
                for (Tree tree : habitat.getTrees().values()) {
                    effortTrees += tree.getCleaningEffort(season);
                }
                double trabalhoNoHabitat = area + 3 * population + effortTrees;
                if (numTrts == 0) numTrts = 1;
                work += trabalhoNoHabitat / numTrts;
            }
            satisfaction = 300 - work;
        }

        return (int) Math.round(satisfaction);
    }

    public void transferToHabitat(String animalID, String habitatID) {
        Animal animal = getAnimals().get(animalID);
        String habitatIDVelho = animal.getHabitatID();
        Habitat habitatVelho = getHabitats().get(habitatIDVelho);
        habitatVelho.getAnimals().remove(animalID);
        animal.setHabitatID(habitatID);
        Habitat habitatNovo = getHabitats().get(habitatID);
        habitatNovo.getAnimals().put(animalID, animal);
        dirty();
    }

    public void addResponsibilty(Employee employee, String responsibiltyId){
        for (String resp : employee.getResponsibilities())
            if (resp.equals(responsibiltyId)) return;
        employee.addResponsibility(responsibiltyId);
        dirty();
    }

    public void removeResponsibilty(Employee employee, String responsibiltyId){
        employee.removeResponsibility(responsibiltyId);
        dirty();
    }

    public String bioCycle(String type) {
        //0 para Primavera, 1 para Verão, 2 para Outono e 3 para Inverno.
        if (type.equals("CADUCA")) {
            if (_season == 0) return "GERARFOLHAS";
            else if (_season == 1) return "COMFOLHAS";
            else if (_season == 2) return "LARGARFOLHAS";
            else return "SEMFOLHAS";
        } else {
            if (_season == 0) return "GERARFOLHAS";
            else if (_season == 1) return "COMFOLHAS";
            else if (_season == 2) return "COMFOLHAS";
            else return "LARGARFOLHAS";
        }
        
    }

    public String addTreeToHabitat(String habitatId, String treeiD, String treeName, int treeAge, int treeDifficulty, String treeType){
        Habitat habitat = getHabitats().get(habitatId);
        if (treeType.equals("CADUCA"))
            habitat.getTrees().put(treeiD, new Caduca(treeiD, treeName, treeAge, treeDifficulty, treeType));
        else
            habitat.getTrees().put(treeiD, new Perene(treeiD, treeName, treeAge, treeDifficulty, treeType));
        return  "ÁRVORE"+"|"+ treeiD+ "|" +treeName + "|" + treeAge + "|"+ treeDifficulty + "|" + treeType+ "|"+ bioCycle(treeType);
    }


    public void changeHabitatInfluence(String habitatID, String specieID, String habitatInfluence) {
        Habitat habitat = getHabitats().get(habitatID);
        Specie specie = getSpecies().get(specieID);
        habitat.getInfluences().put(specie, habitatInfluence);
        dirty();
    }


    public int calculateSatisfaction(Animal src){
        int equals = -1;
        int differents = 0;
        Habitat _habitat = getHabitats().get(src.getHabitatID());
        String _specieID = src.getSpecieID();
        Specie _specie = getSpecies().get(src.getSpecieID());
        int area = _habitat.getArea();
        int influenceInt = 0;
        String influence = _habitat.getInfluence(_specie);
    
        if (influence != null) {
            if (influence.equals("POS")) influenceInt = 20;
            else if (influence.equals("NEG")) influenceInt = -20;
        }
    
        for (Animal animal : _habitat.getAnimals().values()) {
            if (animal.getSpecieID().equals(_specieID)) {
                equals++;
            } else {
                differents++;
            }
        }
    
        return Math.round(20 + 3 * equals - 2 * differents + area / (1 + differents + equals) + influenceInt);
    }

    public List<Specie> getVaccineSpecies(List<String> speciesID) {
        List<Specie> species = new ArrayList<>();
        for (String specieID : speciesID) {
            species.add(getSpecies().get(specieID));
        }
        return species;
    }


    public int calculateDamage(Animal animal, Vaccine vaccine) {
        int damage = 0;
        Specie _specie = getSpecies().get(animal.getSpecieID());
        for (Specie applicableSpecie : getVaccineSpecies(vaccine.getSpecies())) {
            Set<Character> animalSpecieChars = new HashSet<>();
            for (char character1 : _specie.getName().toCharArray()) {
                animalSpecieChars.add(character1);
            }

            Set<Character> vaccineSpecieChars = new HashSet<>();
            for (char character2 : applicableSpecie.getName().toCharArray()) {
                vaccineSpecieChars.add(character2);
            }
            int size = Math.max(_specie.getName().length(), applicableSpecie.getName().length());
            animalSpecieChars.retainAll(vaccineSpecieChars);
            int common = animalSpecieChars.size();
            damage = Math.max(damage, size - common);
        }
        return damage;
        //dirty();
    }

    public void updateHealth(Animal animal, Vaccine vaccine, boolean success) {
        List<String> _health = animal.getHealthRecord();
        if (success) {
            _health.add("NORMAL");
        } else {
            int damage = calculateDamage(animal, vaccine);
            if (damage == 0) {
                _health.add("CONFUSÃO");
            } else if (damage>=1 && damage<=4) {
                _health.add("ACIDENTE");
            } else if (damage>=5) {
                _health.add("ERRO");
            }
        }
        dirty();
    }

    public void vaccinateAnimal(String employeeID, String vaccineID, String animalID, String specieID, boolean success) {
        if (!success)
            _wrongVaccinations.add(new Vaccination(employeeID, vaccineID, specieID));
        _vaccinations.add(new Vaccination(employeeID, vaccineID, specieID));
        Animal animal = getAnimals().get(animalID);
        Vaccine vaccine = getVaccines().get(vaccineID);
        animal.getVaccinations().add(new Vaccination(employeeID, vaccineID, specieID));
        updateHealth(animal, vaccine, success);
        dirty();    

    }


    /**
     * Registers an entry in the hotel based on the provided fields.
     *
     * @param fields the fields of the entry
     * @throws UnrecognizedEntryException if the entry type is unrecognized
     */
    public void registerEntry(String... fields) throws UnrecognizedEntryException {
        switch (fields[0]) {
            case "ANIMAL" -> registerAnimal(fields[1], fields[2], fields[3], fields[4]);

            case "HABITAT" -> {
                if (fields.length == 5) {
                    registerHabitat(fields[1], fields[2], Integer.parseInt(fields[3]));
                    String[] splitText = fields[4].split(",");
                    for (String treeID : splitText) {
                        _habitats.get(fields[1]).getTrees().put(treeID, _trees.get(treeID));
                    }
                } else if (fields.length == 4) {
                    registerHabitat(fields[1], fields[2], Integer.parseInt(fields[3]));
                }
            }


            case "VETERINÁRIO", "TRATADOR" -> {
            if (fields.length == 3) {
                registerEmployee(fields[1], fields[2], fields[0]);
            } else if (fields.length == 4) {
                registerEmployee(fields[1], fields[2], fields[0]);
                String[] splitText = fields[3].split(",");
                for ( String resp : splitText) {
                    addResponsibilty(_employees.get(fields[1]), resp);
                }
            }

            }

                
            case "VACINA" -> {
                if (fields.length == 4) {
                    registerVaccine(fields[1], fields[2], Arrays.asList(fields[3].split(",")));
                } else if (fields.length == 3) {
                    registerVaccine(fields[1], fields[2], Collections.emptyList());
                }
            }
            case "ESPÉCIE" -> addSpecie(fields[1], fields[2]);

            // String addTreeToHabitat(String habitatId, String treeiD, String treeName, int treeAge, int treeDifficulty, String treeType)
            case "ÁRVORE" -> {
                Tree tree;
                if (fields[5].equals("CADUCA")) {
                    tree = new Caduca(fields[1], fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), fields[5]);
                } else {
                    tree = new Perene(fields[1], fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), fields[5]);
                }
                _trees.put(fields[1], tree);
            }

            default -> throw new UnrecognizedEntryException(fields[0]);
        }
    }


    /**
     * Imports data from a text file and creates domain entities.
     *
     * @param name the name of the text input file
     * @throws ImportFileException if an error occurs during file import
     */
    public void importFile(String name) throws ImportFileException {
        try (BufferedReader reader = new BufferedReader(new FileReader(name))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                try {
                    registerEntry(fields);
                } catch (UnrecognizedEntryException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e1) {
            throw new ImportFileException(name);
        }
    }


}
