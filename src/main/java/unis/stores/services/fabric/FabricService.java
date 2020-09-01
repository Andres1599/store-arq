package unis.stores.services.fabric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Brand;
import unis.stores.entities.Fabric;
import unis.stores.repositories.FabricRepository;

import java.util.Date;
import java.util.List;

@Service
public class FabricService implements IFabricService {

    /**
     * The fabric repository to connect to the database
     */
    @Autowired
    private FabricRepository fabricRepository;

    /**
     * Returns the fabrics of the system
     *
     * @return    the list of the fabrics from the database.
     */
    @Override
    public List<Fabric> getFabrics() {
        return (List<Fabric>) fabricRepository.findAll();
    }

    /**
     * Returns a fabric searched by the id
     *
     * @param     id this is the id of the fabric that we are looking for
     * @return    the fabric retrieved from the database
     */
    @Override
    public Fabric getFabric(int id) {
        return fabricRepository.findOne(id);
    }

    /**
     * Creates a fabric in the system
     *
     * @param     name this is the name of the fabric we want to create
     * @param     ip this is the ip of the fabric we want to create
     * @param     servicePassword this is the servicePassword of the fabric we want to create
     * @return    the created fabric
     */
    @Override
    public Fabric createFabric(String name, String ip, String servicePassword) {
        Fabric fabric = new Fabric();
        fabric.setName(name);
        fabric.setIp(ip);
        fabric.setServicePassword(servicePassword);
        fabric.setLastDateRequest(new Date());

        return fabricRepository.save(fabric);
    }

    /**
     * Updates the param of a fabric
     *
     * @param     id this is the id of the fabric that we want to update
     * @param     name this is the name of the fabric we want to update
     * @param     ip this is the ip of the fabric we want to update
     * @param     servicePassword this is the servicePassword of the fabric we want to update
     * @return    the updated fabric
     */
    @Override
    public Fabric updateFabric(int id, String name, String ip, String servicePassword) {
        if (!fabricRepository.exists(id))
            return null;

        Fabric fabric = fabricRepository.findOne(id);
        fabric.setName(name);
        fabric.setServicePassword(servicePassword);
        fabric.setIp(ip);

        return fabricRepository.save(fabric);
    }

    /**
     * Deletes a fabric
     *
     * @param     id this is the id of the fabric that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    @Override
    public boolean deleteFabric(int id) {
        if (!fabricRepository.exists(id))
            return false;

        fabricRepository.delete(id);
        return true;
    }
}
