package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import unis.stores.entities.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, String> {

}
