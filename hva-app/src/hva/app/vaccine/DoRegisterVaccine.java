package hva.app.vaccine;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.app.exceptions.DuplicateVaccineKeyException;
import hva.app.exceptions.UnknownSpeciesKeyException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//FIXME import other classes if needed


/**
 * Command to register a new vaccine in the hotel system.
 *
 * @param receiver the hotel instance where the vaccine will be registered
 * @param vaccineID the unique identifier of the vaccine
 * @param vaccineName the name of the vaccine
 * @param speciesKeys a comma-separated list of species keys that the vaccine is effective against
 */
class DoRegisterVaccine extends Command<Hotel> {

    DoRegisterVaccine(Hotel receiver) {
        super(Label.REGISTER_VACCINE, receiver);
        addStringField("vaccineID", Prompt.vaccineKey());
        addStringField("vaccineName", Prompt.vaccineName());
        addStringField("speciesKeys", Prompt.listOfSpeciesKeys());
    }

    @Override
    protected final void execute() throws CommandException {
        String vaccineID = stringField("vaccineID");
        String vaccineName = stringField("vaccineName");
        String speciesKeys = stringField("speciesKeys");


        String[] array = speciesKeys.split(",");
        List<String> speciesList = Arrays.asList(array);
        speciesList.sort(String.CASE_INSENSITIVE_ORDER);

        if (_receiver.getVaccines().containsKey(vaccineID)) {
            throw new DuplicateVaccineKeyException(vaccineID);
        }

        for (String speciesKey : speciesList) {
            if (!_receiver.getSpecies().containsKey(speciesKey)) {
                throw new UnknownSpeciesKeyException(speciesKey);
            }
        }

        _receiver.registerVaccine(vaccineID, vaccineName, speciesList);

    }
}