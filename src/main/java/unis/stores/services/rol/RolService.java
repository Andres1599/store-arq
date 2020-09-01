package unis.stores.services.rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Rol;
import unis.stores.repositories.RolRepository;

import java.util.List;

@Service
public class RolService implements IRolService {

    /**
     * The rol repository to connect to the database
     */
    @Autowired
    private RolRepository rolRepository;

    /**
     * Returns the rols of the system
     *
     * @return    the list of the rols from the database.
     */
    @Override
    public List<Rol> getRoles() {
        return (List<Rol>) rolRepository.findAll();
    }

    /**
     * Returns a rol searched by the id
     *
     * @param     id this is the id of the rol that we are looking for
     * @return    the rol retrieved from the database
     */
    @Override
    public Rol getRolById(int id) {
        if (!rolRepository.exists(id))
            return null;

        return rolRepository.findOne(id);
    }

    /**
     * Creates a rol in the system
     *
     * @param     name this is the name of the rol we want to create
     * @return    the created rol
     */
    @Override
    public Rol createRol(String name) {
        Rol newRol = new Rol();
        newRol.setName(name);

        return rolRepository.save(newRol);
    }

    /**
     * Updates the param of a rol
     *
     * @param     id this is the id of the rol that we want to update
     * @param     name this is the name of the rol we want to update
     * @return    the updated rol
     */
    @Override
    public Rol updateRol(int id, String name) {
        if (!rolRepository.exists(id))
            return null;

        Rol updateRol = rolRepository.findOne(id);
        updateRol.setName(name);

        return  rolRepository.save(updateRol);
    }

    /**
     * Deletes a rol
     *
     * @param     id this is the id of the rol that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    @Override
    public boolean deleteRol(int id) {
        if (!rolRepository.exists(id)) {
            return false;
        }

        rolRepository.delete(id);
        return true;
    }

    /**
     * Returns a rol searched by the name
     *
     * @param     name this is the name of the rol that we are looking for
     * @return    the rol retrieved from the database
     */
    @Override
    public Rol searchByName(String name) {
        return rolRepository.findByName(name);
    }
}
