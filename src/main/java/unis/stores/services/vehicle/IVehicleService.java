package unis.stores.services.vehicle;

import unis.stores.entities.Vehicle;

import java.util.List;

public interface IVehicleService {

    /**
     * Returns the vehicles of the system
     *
     * @return    the list of the vehicles from the database.
     */
    List<Vehicle> getVehicles();
    /**
     * Returns a vehicle searched by the universalCode
     *
     * @param     universalCode this is the universalCode of the vehicle that we are looking for
     * @return    the vehicle retrieved from the database
     */
    Vehicle getVehicle(String universalCode);
    /**
     * Creates a vehicle in the system
     *
     * @param     universalCode this is the universalCode of the vehicle we want to create
     * @param     brand this is the brandId of the brand we want to update
     * @param     line this is the lineId of the line we want to update
     * @param     year this is the year of the vehicle we want to update
     * @return    the created vehicle
     */
    Vehicle createVehicle(String universalCode, int brand, int line, String year);
    /**
     * Updates the param of a vehicle
     *
     * @param     universalCode this is the universalCode of the vehicle that we want to update
     * @param     brand this is the brandId of the brand we want to update
     * @param     line this is the lineId of the line we want to update
     * @param     year this is the year of the vehicle we want to update
     * @return    the updated vehicle
     */
    Vehicle updateVehicle(String universalCode, int brand, int line, String year);
    /**
     * Deletes a vehicle
     *
     * @param     universalCode this is the universalCode of the vehicle that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    boolean deleteVehicle(String universalCode);
}
