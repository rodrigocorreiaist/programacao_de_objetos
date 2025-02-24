package hva.app.vaccine;

import hva.Hotel;
import hva.vaccine.Vaccination;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME import other classes if needed

/**
 * Command to show all vaccinations in the hotel.
 *
 * @param receiver the hotel instance from which vaccinations will be retrieved
 */
class DoShowVaccinations extends Command<Hotel> {

    DoShowVaccinations(Hotel receiver) {
        super(Label.SHOW_VACCINATIONS, receiver);

    }

    @Override
    protected final void execute() {
        for (Vaccination v : _receiver.getVaccinations()) {
            _display.popup(v.toString());
        }
    }
}
