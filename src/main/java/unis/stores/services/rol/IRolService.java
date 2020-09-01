package unis.stores.services.rol;

import unis.stores.entities.Rol;

import java.util.List;

public interface IRolService {
    /**
     * Returns the rols of the system
     *
     * @return    the list of the rols from the database.
     */
    List<Rol> getRoles();
    /**
     * Returns a rol searched by the id
     *
     * @param     id this is the id of the rol that we are looking for
     * @return    the rol retrieved from the database
     */
    Rol getRolById(int id);
    /**
     * Creates a rol in the system
     *
     * @param     name this is the name of the rol we want to create
     * @return    the created rol
     */
    Rol createRol(String name);
    /**
     * Updates the param of a rol
     *
     * @param     id this is the id of the rol that we want to update
     * @param     name this is the name of the rol we want to update
     * @return    the updated rol
     */
    Rol updateRol(int id, String name);
    /**
     * Deletes a rol
     *
     * @param     id this is the id of the rol that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    boolean deleteRol(int id);
    /**
     * Returns a rol searched by the name
     *
     * @param     name this is the name of the rol that we are looking for
     * @return    the rol retrieved from the database
     */
    Rol searchByName(String name);
}
