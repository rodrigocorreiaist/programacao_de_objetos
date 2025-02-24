package hva.app.search;

import hva.Hotel;
import hva.vaccine.*;
import hva.species.Animal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.UnknownAnimalKeyException;


//FIXME import other classes if needed

/**
 * Command to show medical acts performed on a specific animal in the hotel.
 *
 * @param receiver the hotel instance where the animal is located
 * @param animalId the unique identifier of the animal
 */
class DoShowMedicalActsOnAnimal extends Command<Hotel> {

    DoShowMedicalActsOnAnimal(Hotel receiver) {
        super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
        addStringField("animalId", hva.app.animal.Prompt.animalKey());

	//FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
        String animalID = stringField("animalId");
        Animal animal = _receiver.getAnimals().get(animalID);
        if (!_receiver.getAnimals().containsKey(animalID)) {
            throw new UnknownAnimalKeyException(animalID);
        }

        for (Vaccination v : animal.getVaccinations())
                _display.popup(v.toString());
    }
}

