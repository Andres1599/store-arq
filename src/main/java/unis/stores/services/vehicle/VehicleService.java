package unis.stores.services.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Brand;
import unis.stores.entities.Line;
import unis.stores.entities.Vehicle;
import unis.stores.repositories.BrandRepository;
import unis.stores.repositories.LineRepository;
import unis.stores.repositories.VehicleRepository;

import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    /**
     * The vehicle repository to connect to the database
     */
    @Autowired
    private VehicleRepository vehicleRepository;

    /**
     * The brand repository to connect to the database
     */
    @Autowired
    private BrandRepository brandRepository;

    /**
     * The line repository to connect to the database
     */
    @Autowired
    private LineRepository lineRepository;

    /**
     * Returns the vehicles of the system
     *
     * @return    the list of the vehicles from the database.
     */
    @Override
    public List<Vehicle> getVehicles() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    /**
     * Returns a vehicle searched by the universalCode
     *
     * @param     universalCode this is the universalCode of the vehicle that we are looking for
     * @return    the vehicle retrieved from the database
     */
    @Override
    public Vehicle getVehicle(String universalCode) {
        if (!vehicleRepository.exists(universalCode))
            return null;

        return vehicleRepository.findOne(universalCode);
    }

    /**
     * Creates a vehicle in the system
     *
     * @param     universalCode this is the universalCode of the vehicle we want to create
     * @param     brandId this is the brandId of the brand we want to update
     * @param     lineId this is the lineId of the line we want to update
     * @param     year this is the year of the vehicle we want to update
     * @return    the created vehicle
     */
    @Override
    public Vehicle createVehicle(String universalCode, int brandId, int lineId, String year) {
        if (vehicleRepository.exists(universalCode) || !brandRepository.exists(brandId) || !lineRepository.exists(lineId))
            return null;

        Vehicle newVehicle = new Vehicle();
        newVehicle.setUniversalCode(universalCode);

        Brand brand = brandRepository.findOne(brandId);
        Line line = lineRepository.findOne(lineId);

        newVehicle.setBrand(brand);
        newVehicle.setLine(line);
        newVehicle.setYear(year);

        return vehicleRepository.save(newVehicle);
    }

    /**
     * Updates the param of a vehicle
     *
     * @param     universalCode this is the universalCode of the vehicle that we want to update
     * @param     brandId this is the brandId of the brand we want to update
     * @param     lineId this is the lineId of the line we want to update
     * @param     year this is the year of the vehicle we want to update
     * @return    the updated vehicle
     */
    @Override
    public Vehicle updateVehicle(String universalCode, int brandId, int lineId, String year) {
        if (!vehicleRepository.exists(universalCode) || !brandRepository.exists(brandId) || !lineRepository.exists(lineId))
            return null;

        Vehicle updateVehicle = vehicleRepository.findOne(universalCode);

        Brand brand = brandRepository.findOne(brandId);
        Line line = lineRepository.findOne(lineId);

        updateVehicle.setBrand(brand);
        updateVehicle.setLine(line);
        updateVehicle.setYear(year);

        return vehicleRepository.save(updateVehicle);
    }

    /**
     * Deletes a vehicle
     *
     * @param     universalCode this is the universalCode of the vehicle that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    @Override
    public boolean deleteVehicle(String universalCode) {
        if (!vehicleRepository.exists(universalCode))
            return false;

        vehicleRepository.delete(universalCode);
        return true;
    }
}
