package hva;

import java.io.*;
import hva.exceptions.*;

/**
 * Class that represents the hotel application.
 */
public class HotelManager {
    
    private String _filename = "";

    private Hotel _hotel = new Hotel();


    /**
     * Gets the current hotel.
     *
     * @return the current hotel
     */
    public Hotel getHotel(){
        return _hotel;
    }

    public void advanceSeason() {
        Hotel hotel = getHotel();
        if (hotel.getSeason()  == 3)
            hotel.setSeason(0);
        else
            hotel.add1Season();
        // Por implementar a logica das arvores
    }

    public boolean isDirty() {
        return _hotel.isDirty();
    }

    /**
     * Saves the serialized application's state into the file associated with the current network.
     *
     * @throws FileNotFoundException if the file cannot be created or opened
     * @throws MissingFileAssociationException if the current network does not have a file
     * @throws IOException if there is an error while serializing the state of the network to disk
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        if (_filename == null || _filename.isEmpty())
            throw new MissingFileAssociationException();
        if (_hotel.isDirty()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)))) {
                oos.writeObject(_hotel);
                _hotel.clean();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Saves the serialized application's state into the specified file.
     *
     * @param filename the name of the file to save the state to
     * @throws FileNotFoundException if the file cannot be created or opened
     * @throws MissingFileAssociationException if the current network does not have a file
     * @throws IOException if there is an error while serializing the state of the network to disk
     */
    public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
        _filename = filename;
        save();
    }

    /**
     * Loads the serialized application's state from the specified file.
     *
     * @param filename the name of the file containing the serialized application's state to load
     * @throws UnavailableFileException if the specified file does not exist or there is an error while processing the file
     */
    public void load(String filename) throws UnavailableFileException {
        _filename = filename;
        try (ObjectInputStream ois =new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename))) ){
            _hotel = (Hotel) ois.readObject();
            _hotel.clean();
        } catch (IOException | ClassNotFoundException e) {
            throw new UnavailableFileException(filename);
        }
    }

    /**
     * Imports data from a text file and creates domain entities.
     *
     * @param name the name of the text input file
     * @throws ImportFileException if there is an error during file import
     */
    public void importFile(String filename) throws ImportFileException{
        try {
            this._hotel.importFile(filename);
        } catch (Exception e){
            throw new ImportFileException(filename, e);
        }
    }
    


}
