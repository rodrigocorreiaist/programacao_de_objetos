package hva.app.vaccine;

import hva.Hotel;
import hva.vaccine.Vaccine;
import hva.vaccine.Vaccination;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.StringJoiner;
//FIXME import other classes if needed


/**
 * Command to show all vaccines available in the hotel.
 *
 * @param receiver the hotel instance from which the vaccines will be retrieved
 */
class DoShowAllVaccines extends Command<Hotel> {

    DoShowAllVaccines(Hotel receiver) {
        super(Label.SHOW_ALL_VACCINES, receiver);
	//FIXME add command fields if needed
    }


    @Override
    protected final void execute() {
        StringJoiner vaccinesInfo = new StringJoiner("\n");
        for (Vaccine vaccine : _receiver.getVaccines().values()) {
            int count = 0;
            for (Vaccination v : _receiver.getVaccinations()) {
                if (v.getVacID().equals(vaccine.getid())) {
                    count++;
                }
            }
            vaccinesInfo.add(vaccine.toString(count));
        }
        _display.popup(vaccinesInfo.toString());
    }
}
