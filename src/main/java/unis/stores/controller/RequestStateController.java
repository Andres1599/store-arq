package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unis.stores.constants.Constants;
import unis.stores.entities.RequestState;
import unis.stores.result.requeststate.CreateRequestStateResult;
import unis.stores.result.requeststate.DeleteRequestStateResult;
import unis.stores.result.requeststate.GetRequestStateResult;
import unis.stores.result.requeststate.UpdateRequestStateResult;
import unis.stores.services.requeststate.RequestStateService;

import java.util.Map;

@CrossOrigin
@Controller
public class RequestStateController {

    /**
     * The request state service to connect to the database
     */
    @Autowired
    private RequestStateService requestStateService;

    /**
     * Create a request state in the system
     *
     * @param     body contains the information to create the request state
     * @return    returns the result of the creation action
     */
    @PostMapping("/request/state")
    public ResponseEntity<Object> create(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.REQUEST_STATE_NAME_LABEL))
            return ResponseEntity.badRequest().body(new CreateRequestStateResult(false, "Bad Request", null));

        if (requestStateService.searchByName(body.get(Constants.REQUEST_STATE_NAME_LABEL)) != null)
            return ResponseEntity.badRequest().body(new CreateRequestStateResult(false, "The request state already exists", null));

        RequestState requeststate = requestStateService.createRequestState(body.get(Constants.REQUEST_STATE_NAME_LABEL));

        if (requeststate == null)
            return ResponseEntity.badRequest().body(new CreateRequestStateResult(false, "Error creating the request state", null));
        else
            return ResponseEntity.ok(new CreateRequestStateResult(true, "RequestState created", requeststate));
    }

    /**
     * Update a request state in the system
     *
     * @param     body contains the information to update the request state
     * @return    returns the result of the update action
     */
    @PutMapping("/request/state")
    public ResponseEntity<Object> update(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.REQUEST_STATE_ID_LABEL) || !body.containsKey(Constants.REQUEST_STATE_NAME_LABEL))
            return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Bad Request", null));

        try {
            int id = Integer.parseInt(body.get(Constants.REQUEST_STATE_ID_LABEL));
            RequestState requeststate = requestStateService.updateRequestState(id, body.get(Constants.REQUEST_STATE_NAME_LABEL));

            if (requeststate == null)
                return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Error updating the request state", null));
            else
                return ResponseEntity.ok(new UpdateRequestStateResult(true, "RequestState updated", requeststate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Bad Request", null));
        }
    }

    /**
     * Delete a request state in the system
     *
     * @param     id the id request state we want to delete
     * @return    returns the result of the deletion action
     */
    @DeleteMapping("/request/state/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new DeleteRequestStateResult(false, "Bad Request"));

        try {
            int requestStateId = Integer.parseInt(id);

            if (requestStateService.deleteRequestState(requestStateId))
                return ResponseEntity.ok(new DeleteRequestStateResult(true, "RequestState deleted"));
            else
                return ResponseEntity.badRequest().body(new DeleteRequestStateResult(false, "Error deleting the request state"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DeleteRequestStateResult(false, "Bad Request"));
        }
    }

    /**
     * Gets the system request states
     *
     * @return    returns the list of request states in the system
     */
    @GetMapping("/request/state")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(requestStateService.getRequestStates());
    }

    /**
     * Gets a request state
     *
     * @param     id the id of the request state we want to get
     * @return    returns the founded request state
     */
    @GetMapping("/request/state/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new GetRequestStateResult(false, "Bad Request", null));

        try {
            int requestStateId = Integer.parseInt(id);

            RequestState requeststate = requestStateService.getRequestState(requestStateId);

            return requeststate == null ? ResponseEntity.badRequest().body(new GetRequestStateResult(false, "Error getting the request state", null)) :
                    ResponseEntity.ok().body(new GetRequestStateResult(true, "", requeststate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GetRequestStateResult(false, "Bad Request", null));
        }
    }
}
