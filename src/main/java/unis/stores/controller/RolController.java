package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unis.stores.constants.Constants;
import unis.stores.entities.Rol;
import unis.stores.result.rol.CreateRolResult;
import unis.stores.result.rol.DeleteRolResult;
import unis.stores.result.rol.GetRolResult;
import unis.stores.result.rol.UpdateRolResult;
import unis.stores.services.rol.RolService;

import java.util.Map;

@CrossOrigin
@Controller
public class RolController {

    /**
     * The rol service to connect to the database
     */
    @Autowired
    private RolService rolService;

    /**
     * Create a rol in the system
     *
     * @param     body contains the information to create the rol
     * @return    returns the result of the creation action
     */
    @PostMapping("/rol")
    public ResponseEntity<Object> create(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.ROL_NAME_LABEL))
            return ResponseEntity.badRequest().body(new CreateRolResult(false, "Bad Request"));

        if (rolService.searchByName(body.get(Constants.ROL_NAME_LABEL)) != null)
            return ResponseEntity.badRequest().body(new CreateRolResult(false, "The rol already exists!"));

        Rol createdRol = rolService.createRol(body.get(Constants.ROL_NAME_LABEL));

        if (createdRol == null)
            return ResponseEntity.badRequest().body(new CreateRolResult(false, "Error creating the rol!"));
        else
            return ResponseEntity.ok().body(createdRol);
    }

    /**
     * Update a rol in the system
     *
     * @param     body contains the information to update the rol
     * @return    returns the result of the update action
     */
    @PutMapping("/rol")
    public ResponseEntity<Object> update(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.ROL_NAME_LABEL) || !body.containsKey(Constants.ROL_ID_LABEL))
            return ResponseEntity.badRequest().body(new UpdateRolResult(false, "Bad Request"));

        try {
            int rolId = Integer.parseInt(body.get(Constants.ROL_ID_LABEL));

            Rol updatedRol = rolService.updateRol(rolId, body.get(Constants.ROL_NAME_LABEL));

            if (updatedRol == null)
                return ResponseEntity.badRequest().body(new UpdateRolResult(false, "Error updating the rol"));
            else
                return ResponseEntity.ok().body(updatedRol);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateRolResult(false, e.getMessage()));
        }
    }

    /**
     * Delete a rol in the system
     *
     * @param     id the id rol we want to delete
     * @return    returns the result of the deletion action
     */
    @DeleteMapping("/rol/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new DeleteRolResult(false, "Bad Request"));

        try {
            int rolId = Integer.parseInt(id);

            if (rolService.deleteRol(rolId))
                return ResponseEntity.ok().body(new DeleteRolResult(true, "Rol deleted successfully"));
            else
                return ResponseEntity.badRequest().body(new DeleteRolResult(false, "Error deleting the rol"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DeleteRolResult(false, e.getMessage()));
        }
    }

    /**
     * Gets the system rols
     *
     * @return    returns the list of rols in the system
     */
    @GetMapping("/rol")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok().body(rolService.getRoles());
    }

    /**
     * Gets a rol
     *
     * @param     id the id of the rol we want to get
     * @return    returns the founded rol
     */
    @GetMapping("/rol/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new GetRolResult(false, "Bad Request"));

        try {
            int rolId = Integer.parseInt(id);

            Rol rol = rolService.getRolById(rolId);

            if (rol == null)
                return ResponseEntity.badRequest().body(new GetRolResult(false, "Rol doesn't exists"));
            else
                return ResponseEntity.ok().body(rol);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GetRolResult(false, e.getMessage()));
        }
    }
}
