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
class DoShowAllHabitatsnoTrees extends Command<Hotel> {

    DoShowAllHabitatsnoTrees(Hotel receiver) {
        super(Label.All_Habitats_no_Trees, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        for (Habitat h : _receiver.getHabitats().values()){
            if (!h.getTrees().isEmpty()){  
                _display.popup(h.toString());
            }
        }
    }
}