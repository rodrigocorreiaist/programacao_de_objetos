package hva.app.habitat;

import hva.Hotel;
import hva.app.exceptions.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed


/**
 * Command to change the area of a habitat in the hotel.
 *
 * @param receiver the hotel instance where the habitat area will be changed
 * @param habitatID the unique identifier of the habitat
 * @param habitatArea the new area of the habitat
 */
class DoChangeHabitatArea extends Command<Hotel> {

    DoChangeHabitatArea(Hotel receiver) {
        super(Label.CHANGE_HABITAT_AREA, receiver);
        addStringField("habitatID", hva.app.habitat.Prompt.habitatKey());
        addIntegerField("habitatArea", hva.app.habitat.Prompt.habitatArea());
        //FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
        String habitatID = stringField("habitatID");
        int habitatArea = integerField("habitatArea");
        if (!_receiver.getHabitats().containsKey(habitatID)){
            throw new UnknownHabitatKeyException(habitatID);
        }
        _receiver.changeHabitatArea(habitatID, habitatArea);
    }

}
    