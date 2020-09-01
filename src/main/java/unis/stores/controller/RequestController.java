package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unis.stores.entities.Request;
import unis.stores.result.request.CreateRequestResult;
import unis.stores.result.request.GetRequestResult;
import unis.stores.result.request.UpdateRequestStateResult;
import unis.stores.services.request.RequestService;

@CrossOrigin
@Controller
public class RequestController {

    /**
     * The request service to connect to the database
     */
    @Autowired
    private RequestService requestService;

    /**
     * Gets the system requests
     *
     * @return    returns the list of requests in the system
     */
    @GetMapping("/request")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok().body(requestService.getRequests());
    }

    /**
     * Gets a request
     *
     * @param     id the id of the request we want to get
     * @return    returns the founded request
     */
    @GetMapping("/request/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new GetRequestResult(false, "Bad Request", null));

        try {
            int requestId = Integer.parseInt(id);

            Request request = requestService.getRequest(requestId);

            if (request == null)
                return ResponseEntity.badRequest().body(new GetRequestResult(false, "Request doesn't exists", null));
            else
                return ResponseEntity.ok().body(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GetRequestResult(false, e.getMessage(), null));
        }
    }

    /**
     * Create a request in the system
     *
     * @param     request contains the information to create the request
     * @return    returns the result of the creation action
     */
    @PostMapping("/request")
    public ResponseEntity<Object> create(@RequestBody Request request) {
        if (request.getFabric() == null || request.getProductRequests() == null || request.getProductRequests().size() == 0)
            return ResponseEntity.badRequest().body(new CreateRequestResult(false, "Bad Request", null));

        Request createdRequest = requestService.createRequest(request);

        if (createdRequest == null)
            return  ResponseEntity.badRequest().body(new CreateRequestResult(false, "Error creating the request", null));
        else
            return ResponseEntity.ok(new CreateRequestResult(true, "Request created", createdRequest));
    }

    /**
     * Cancel a sale request in the system
     *
     * @param     id is the id of the request we want to cancel
     * @return    returns the result of the cancel action
     */
    @PutMapping("/request/cancel/{id}")
    public ResponseEntity<Object> cancelRequest(@PathVariable("id") String id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Bad Request", null));
        }
        try {
            int requestId = Integer.parseInt(id);
            Request updatedRequest = requestService.updateRequestState(requestId, 3);

            if (updatedRequest == null)
                return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Error updating the request", null));
            else
                return ResponseEntity.ok(new UpdateRequestStateResult(false, "Request updated", updatedRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Bad Request", null));
        }
    }

    /**
     * Receive a sale request in the system
     *
     * @param     id is the id of the request we want to receive
     * @return    returns the result of the receive action
     */
    @PutMapping("/request/receive/{id}")
    public ResponseEntity<Object> receiveRequest(@PathVariable("id") String id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Bad Request", null));
        }
        try {
            int requestId = Integer.parseInt(id);
            Request updatedRequest = requestService.receiveRequest(requestId);

            if (updatedRequest == null)
                return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Error updating the request", null));
            else
                return ResponseEntity.ok(new UpdateRequestStateResult(false, "Request updated", updatedRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Bad Request", null));
        }
    }
}
