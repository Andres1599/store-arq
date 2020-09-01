package unis.stores.services.request;

import unis.stores.entities.Request;

import java.util.List;

public interface IRequestService {

    /**
     * Returns the requests of the system
     *
     * @return    the list of the requests from the database.
     */
    List<Request> getRequests();
    /**
     * Returns a request searched by the id
     *
     * @param     id this is the id of the request that we are looking for
     * @return    the request retrieved from the database
     */
    Request getRequest(int id);
    /**
     * Creates a request in the system
     *
     * @param     request this is the object of the request we want to create
     * @return    the created request
     */
    Request createRequest(Request request);
    /**
     * Updates the request state of a request
     *
     * @param     id this is the id of the request that we want to update
     * @param     requestStateId this is the id of the request state we want to put to the request
     * @return    the updated request
     */
    Request updateRequestState(int id, int requestStateId);
    /**
     * Receives a request and updates the stock automatically
     *
     * @param     id this is the id of the request we are receiving
     * @return    the request updated
     */
    Request receiveRequest(int id);
}
