package hva.app.vaccine;

import hva.Hotel;
import hva.employees.Employee;
import hva.species.Animal;
import hva.species.Specie;
import hva.vaccine.Vaccine;
import hva.app.exceptions.UnknownVeterinarianKeyException;
import hva.app.exceptions.VeterinarianNotAuthorizedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

/**
 * Command to vaccinate an animal in the hotel.
 *
 * @param vaccineID   the unique identifier of the vaccine
 * @param employeeID  the unique identifier of the veterinarian
 * @param animalID    the unique identifier of the animal
 */

class DoVaccinateAnimal extends Command<Hotel> {

    DoVaccinateAnimal(Hotel receiver) {
        super(Label.VACCINATE_ANIMAL, receiver);
        addStringField("vaccineID", hva.app.vaccine.Prompt.vaccineKey());
        addStringField("employeeID", hva.app.vaccine.Prompt.veterinarianKey());
        addStringField("animalID", hva.app.animal.Prompt.animalKey());
        //FIXME add command fields if needed
    }

    @Override
    protected final void execute() throws CommandException {
        boolean success = true;
        String vaccineID = stringField("vaccineID");
        String employeeID = stringField("employeeID");
        String animalID = stringField("animalID");
        Employee employee = _receiver.getEmployees().get(employeeID);
        Animal animal = _receiver.getAnimals().get(animalID);
        Specie specie = _receiver.getSpecies().get(animal.getSpecieID());
        String specieID = animal.getSpecieID();
        Vaccine vaccine = _receiver.getVaccines().get(vaccineID);

        if (!employee.getType().equals("VET")) {
            throw new UnknownVeterinarianKeyException(employeeID);
        }

        if (!_receiver.getVaccines().containsKey(vaccineID)) {
            throw new hva.app.exceptions.UnknownVaccineKeyException(vaccineID);
        }

        if (!employee.getResponsibilities().contains(specieID)) {
            throw new VeterinarianNotAuthorizedException(employeeID, specieID);
        }

        if (!vaccine.getSpecies().contains(specieID)) {
            _display.popup(hva.app.vaccine.Message.wrongVaccine(vaccineID, animalID));
            success = false;
        }
        
        _receiver.vaccinateAnimal(employeeID, vaccineID, animalID, specieID, success);
    }

}
