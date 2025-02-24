package hva.app.employee;

import hva.Hotel;
import hva.employees.*;
import hva.app.exceptions.NoResponsibilityException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed


/**
 * Command to remove a responsibility from an employee in the hotel.
 *
 * @param receiver the hotel instance where the responsibility will be removed
 * @param employeeID the unique identifier of the employee
 * @param responsibilityId the unique identifier of the responsibility
 */
class DoRemoveResponsibility extends Command<Hotel> {

    DoRemoveResponsibility(Hotel receiver) {
        super(Label.REMOVE_RESPONSABILITY, receiver);
        addStringField("employeeID", hva.app.employee.Prompt.employeeKey());
        addStringField("responsibilityId", hva.app.employee.Prompt.responsibilityKey());
        //FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
        String employeeID = stringField("employeeID");
        String responsibilityId = stringField("responsibilityId");
        Employee employee = _receiver.getEmployees().get(employeeID);
        boolean present = false;
        if (employee.getType().equals("VET")) {
            if (_receiver.getSpecies().get(responsibilityId) == null)
                throw new NoResponsibilityException(employeeID, responsibilityId);
        } else {
            if (_receiver.getHabitats().get(responsibilityId) == null)
                throw new NoResponsibilityException(employeeID, responsibilityId);
        }
        // Se a responsabilidade não estava atribuída ao funcionário é lançada a excepção NoResponsabilityException.
        for (String resp : employee.getResponsibilities()) {
            if (resp.equals(responsibilityId)) {
                present = true;
                break;
            }
        }

        if (!present)
            throw new NoResponsibilityException(employeeID, responsibilityId);

        _receiver.removeResponsibilty(employee, responsibilityId);
    }

}
