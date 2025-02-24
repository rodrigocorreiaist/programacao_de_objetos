package hva.app.employee;

import hva.Hotel;
import hva.employees.*;
import hva.app.exceptions.NoResponsibilityException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Command to add a responsibility to an employee in the hotel.
 *
 * @param receiver the hotel instance where the responsibility will be added
 * @param employeeId the unique identifier of the employee
 * @param responsibilityId the unique identifier of the responsibility
 */
class DoAddResponsibility extends Command<Hotel> {

    DoAddResponsibility(Hotel receiver) {
        super(Label.ADD_RESPONSABILITY, receiver);
        addStringField("employeeId", hva.app.employee.Prompt.employeeKey());
        addStringField("responsibilityId", hva.app.employee.Prompt.responsibilityKey());
    }

    @Override
    protected void execute() throws CommandException {
        String employeeId = stringField("employeeId");
        String responsibilityId = stringField("responsibilityId");
        Employee employee = _receiver.getEmployees().get(employeeId);

        if (!_receiver.getEmployees().containsKey(employeeId)) {
            throw new hva.app.exceptions.UnknownEmployeeKeyException(employeeId);
        }

        if (employee.getType().equals("VET")) {
            if (_receiver.getSpecies().get(responsibilityId) == null)
                throw new NoResponsibilityException(employeeId, responsibilityId);
        } else {
            if (_receiver.getHabitats().get(responsibilityId) == null)
                throw new NoResponsibilityException(employeeId, responsibilityId);
        }
        _receiver.addResponsibilty(employee, responsibilityId);

    }

}
