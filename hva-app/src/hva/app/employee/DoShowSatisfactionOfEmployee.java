package hva.app.employee;

import hva.Hotel;
import hva.employees.Employee;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import  hva.app.exceptions.UnknownEmployeeKeyException;


//FIXME import other classes if needed


/**
 * Command to show the satisfaction of an employee in the hotel.
 *
 * @param receiver the hotel instance where the employee's satisfaction will be shown
 * @param employeeId the unique identifier of the employee
 */
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

    DoShowSatisfactionOfEmployee(Hotel receiver) {
        super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
        addStringField("employeeId", hva.app.employee.Prompt.employeeKey());
        //FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
        String employeeId = stringField("employeeId");
        Employee employee = _receiver.getEmployees().get(employeeId);
        int season = _receiver.getSeason();
        if (!_receiver.getEmployees().containsKey(employeeId)) {
            throw new UnknownEmployeeKeyException(employeeId);
        }

        _display.popup(_receiver.calculateSatisfaction(employee, season));
    }

}
