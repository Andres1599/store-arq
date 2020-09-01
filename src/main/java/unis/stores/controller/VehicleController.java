package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unis.stores.constants.Constants;
import unis.stores.entities.Vehicle;
import unis.stores.result.vehicle.CreateVehicleResult;
import unis.stores.result.vehicle.DeleteVehicleResult;
import unis.stores.result.vehicle.GetVehicleResult;
import unis.stores.result.vehicle.UpdateVehicleResult;
import unis.stores.services.vehicle.VehicleService;

import java.util.Map;

@CrossOrigin
@Controller
public class VehicleController {

    /**
     * The vehicle service to connect to the database
     */
    @Autowired
    private VehicleService vehicleService;

    /**
     * Create a vehicle in the system
     *
     * @param     body contains the information to create the vehicle
     * @return    returns the result of the creation action
     */
    @PostMapping("/vehicle")
    public ResponseEntity<Object> create(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.VEHICLE_ID_LABEL) || !body.containsKey(Constants.VEHICLE_BRAND_LABEL) ||
            !body.containsKey(Constants.VEHICLE_LINE_LABEL) || !body.containsKey(Constants.VEHICLE_YEAR_LABEL))
            return ResponseEntity.badRequest().body(new CreateVehicleResult(false, "Bad Request"));

        try {
            if (vehicleService.getVehicle(body.get(Constants.VEHICLE_ID_LABEL)) != null)
                return ResponseEntity.badRequest().body(new CreateVehicleResult(false, "Vehicle already exists!"));

            int vehicleId = Integer.parseInt(body.get(Constants.VEHICLE_BRAND_LABEL));
            int lineId = Integer.parseInt(body.get(Constants.VEHICLE_LINE_LABEL));

            Vehicle vehicle = vehicleService.createVehicle(body.get(Constants.VEHICLE_ID_LABEL), vehicleId,
                    lineId, body.get(Constants.VEHICLE_YEAR_LABEL));

            if (vehicle == null)
                return ResponseEntity.badRequest().body(new CreateVehicleResult(false, "Error creating the vehicle"));
            else
                return ResponseEntity.ok().body(vehicle);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CreateVehicleResult(false, e.getMessage()));
        }
    }

    /**
     * Update a vehicle in the system
     *
     * @param     body contains the information to update the vehicle
     * @return    returns the result of the update action
     */
    @PutMapping("/vehicle")
    public ResponseEntity<Object> update(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.VEHICLE_ID_LABEL) || !body.containsKey(Constants.VEHICLE_BRAND_LABEL) ||
                !body.containsKey(Constants.VEHICLE_LINE_LABEL) || !body.containsKey(Constants.VEHICLE_YEAR_LABEL))
            return ResponseEntity.badRequest().body(new UpdateVehicleResult(false, "Bad Request"));

        try {
            int vehicleId = Integer.parseInt(body.get(Constants.VEHICLE_BRAND_LABEL));
            int lineId = Integer.parseInt(body.get(Constants.VEHICLE_LINE_LABEL));

            Vehicle updatedVehicle = vehicleService.updateVehicle(body.get(Constants.VEHICLE_ID_LABEL), vehicleId,
                    lineId, body.get(Constants.VEHICLE_YEAR_LABEL));

            if (updatedVehicle == null)
                return ResponseEntity.badRequest().body(new UpdateVehicleResult(false, "Error updating the vehicle"));
            else
                return ResponseEntity.ok().body(updatedVehicle);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateVehicleResult(false, e.getMessage()));
        }
    }

    /**
     * Delete a vehicle in the system
     *
     * @param     universalCode the universalCode vehicle we want to delete
     * @return    returns the result of the deletion action
     */
    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String universalCode) {
        if (universalCode == null)
            return ResponseEntity.badRequest().body(new DeleteVehicleResult(false, "Bad Request"));

        if (vehicleService.deleteVehicle(universalCode))
            return ResponseEntity.ok().body(new DeleteVehicleResult(true, "Vehicle deleted successfully"));
        else
            return ResponseEntity.badRequest().body(new DeleteVehicleResult(false, "Error deleting the vehicle"));
    }

    /**
     * Gets the system vehicles
     *
     * @return    returns the list of vehicles in the system
     */
    @GetMapping("/vehicle")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok().body(vehicleService.getVehicles());
    }

    /**
     * Gets a vehicle
     *
     * @param     universalCode the universalCode of the vehicle we want to get
     * @return    returns the founded vehicle
     */
    @GetMapping("/vehicle/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String universalCode) {
        if (universalCode == null)
            return ResponseEntity.badRequest().body(new GetVehicleResult(false, "Bad Request"));

        Vehicle vehicle = vehicleService.getVehicle(universalCode);

        if (vehicle == null)
            return ResponseEntity.badRequest().body(new GetVehicleResult(false, "Vehicle doesn't exists"));
        else
            return ResponseEntity.ok().body(vehicle);
    }
}
