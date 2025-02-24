package hva.app.search;

import hva.Hotel;
import hva.habitat.Habitat;
import hva.species.Animal;
import java.util.Map; 
import java.util.StringJoiner; 
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.UnknownHabitatKeyException;

//FIXME import other classes if needed

/**
 * Command to show all animals in a specific habitat in the hotel.
 *
 * @param receiver the hotel instance where the habitat is located
 * @param habitatID the unique identifier of the habitat
 */
class DoShowAnimalsInHabitat extends Command<Hotel> {

    DoShowAnimalsInHabitat(Hotel receiver) {
        super(Label.ANIMALS_IN_HABITAT, receiver);
        addStringField("habitatID", hva.app.habitat.Prompt.habitatKey());

    }

    @Override
    protected void execute() throws CommandException {
        String habitatID = stringField("habitatID");
        if (!_receiver.getHabitats().containsKey(habitatID)){
            throw new UnknownHabitatKeyException(habitatID);
        }

        Habitat habitat = _receiver.getHabitats().get(habitatID);
        Map <String, Animal> animals = habitat.getAnimals();
        StringJoiner animalsInfo = new StringJoiner("\n");

        for (Animal animal : animals.values()){
            String content = animal.toString();
            animalsInfo.add(content);
        }
        _display.popup(animalsInfo.toString());




    }

}
