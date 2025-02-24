package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.search.Label;
import hva.vaccine.Vaccination;
import pt.tecnico.uilib.Display;


/**
 * Command to display wrong vaccinations in the hotel.
 *
 * @param receiver the hotel instance where the wrong vaccinations will be displayed
 */
class DoShowWrongVaccinations extends Command<Hotel> {

    DoShowWrongVaccinations(Hotel receiver) {
        super(Label.WRONG_VACCINATIONS, receiver);
	//FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {

        for (Vaccination v : _receiver.getWrongVaccinations()) {
            _display.popup(v.toString());
        }

    }

}
