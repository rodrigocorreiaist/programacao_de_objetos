package hva.app.search;

import hva.Hotel;
import hva.vaccine.Vaccination;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.UnknownVeterinarianKeyException;
import hva.employees.Employee;


//FIXME import other classes if needed


/**
 * Command to show medical acts performed by a specific veterinarian.
 *
 * @param receiver the hotel instance where the veterinarian works
 * @param VetId the unique identifier of the veterinarian
 */
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

    DoShowMedicalActsByVeterinarian(Hotel receiver) {
        super(Label.MEDICAL_ACTS_BY_VET, receiver);
        addStringField("VetId", hva.app.employee.Prompt.employeeKey());
	//FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
        String VetID = stringField("VetId");
        Employee employee = _receiver.getEmployees().get(VetID);

        if (employee.getType() != "VET") {
            throw new UnknownVeterinarianKeyException(VetID);
        }

        if (!_receiver.getEmployees().containsKey(VetID)) {
            throw new UnknownVeterinarianKeyException(VetID);
        }

        for (Vaccination v : _receiver.getVaccinations()) {
            if (v.getEmployeeID().equals(VetID)) {
                _display.popup(v.toString());
            }
        }

    }

}
