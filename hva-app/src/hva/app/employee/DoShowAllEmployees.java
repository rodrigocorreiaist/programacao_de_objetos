package hva.app.employee;

import hva.Hotel;
import hva.employees.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.StringJoiner;


/**
 * Command to show all employees in the hotel.
 *
 * @param receiver the hotel instance whose employees will be shown
 */
class DoShowAllEmployees extends Command<Hotel> {

    DoShowAllEmployees(Hotel receiver) {
        super(Label.SHOW_ALL_EMPLOYEES, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        StringJoiner employeesInfo = new StringJoiner("\n");
        for (Employee employee : _receiver.getEmployees().values()) {
            StringJoiner responsibilitiesInfo = new StringJoiner(",");
            for (String responsibility : employee.getResponsibilities()) {
                responsibilitiesInfo.add(responsibility);
            }
            if (responsibilitiesInfo.length() > 0) {
                employeesInfo.add(employee.getType() + "|" + employee.getID() + "|" + employee.getName() + "|" + responsibilitiesInfo.toString());
            } else {
                employeesInfo.add(employee.getType() + "|" + employee.getID() + "|" + employee.getName());
            }
        }
        _display.popup(employeesInfo.toString());
    }
}
