package hva.app.employee;

import hva.Hotel;
import hva.app.employee.Prompt;
import hva.employees.Employee;
import hva.employees.Keeper;
import hva.employees.Veterinary;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.DuplicateEmployeeKeyException;
//FIXME import other classes if needed


/**
 * Command to register a new employee in the hotel.
 *
 * @param receiver the hotel instance where the employee will be registered
 * @param employeeID the unique identifier of the employee
 * @param employeeName the name of the employee
 * @param employeeType the type of the employee (VET or TRT)
 */
class DoRegisterEmployee extends Command<Hotel> {

    DoRegisterEmployee(Hotel receiver) {
        super(Label.REGISTER_EMPLOYEE, receiver);
        addStringField("employeeID", Prompt.employeeKey());
        addStringField("employeeName", Prompt.employeeName());

    }


    @Override
    protected void execute() throws CommandException {
        String employeeID = stringField("employeeID");
        String employeeName = stringField("employeeName");
        String employeeType;

        do {
            employeeType = Form.requestString(Prompt.employeeType());
        } while (!employeeType.equals("VET") && !employeeType.equals("TRT"));
        
        // Check if employee with the same ID already exists
        if (_receiver.getEmployees().containsKey(employeeID)) {
            throw new DuplicateEmployeeKeyException(employeeID);
        }

        

        _receiver.registerEmployee(employeeID, employeeName, employeeType);
    }
}