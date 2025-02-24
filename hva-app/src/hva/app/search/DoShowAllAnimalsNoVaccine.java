package hva.app.search;

import hva.Hotel;
import hva.habitat.Habitat;
import hva.species.Animal;
import java.util.Map; 
import java.util.StringJoiner; 
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.UnknownHabitatKeyException;

/**
 * Command to show all animals in a specific habitat in the hotel.
 *
 * @param receiver the hotel instance where the habitat is located
 * @param habitatID the unique identifier of the habitat
 */
class DoShowAllAnimalsNoVaccine extends Command<Hotel> {

    DoShowAllAnimalsNoVaccine(Hotel receiver) {
        super(Label.ALL_ANIMALS_NO_VACCINE, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        for (Animal a : _receiver.getAnimals().values()){
            if (!a.getVaccinations().isEmpty()){  
                _display.popup(a.toString());
            }
        }
    }
}