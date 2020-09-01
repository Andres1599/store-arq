package unis.stores.services.fabric;

import unis.stores.entities.Fabric;

import java.util.List;

public interface IFabricService {

    /**
     * Returns the fabrics of the system
     *
     * @return    the list of the fabrics from the database.
     */
    List<Fabric> getFabrics();
    /**
     * Returns a fabric searched by the id
     *
     * @param     id this is the id of the fabric that we are looking for
     * @return    the fabric retrieved from the database
     */
    Fabric getFabric(int id);
    /**
     * Creates a fabric in the system
     *
     * @param     name this is the name of the fabric we want to create
     * @param     ip this is the ip of the fabric we want to create
     * @param     servicePassword this is the servicePassword of the fabric we want to create
     * @return    the created fabric
     */
    Fabric createFabric(String name, String ip, String servicePassword);
    /**
     * Updates the param of a fabric
     *
     * @param     id this is the id of the fabric that we want to update
     * @param     name this is the name of the fabric we want to update
     * @param     ip this is the ip of the fabric we want to update
     * @param     servicePassword this is the servicePassword of the fabric we want to update
     * @return    the updated fabric
     */
    Fabric updateFabric(int id, String name, String ip, String servicePassword);
    /**
     * Deletes a fabric
     *
     * @param     id this is the id of the fabric that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    boolean deleteFabric(int id);
}
