package unis.stores.services.requeststate;

import unis.stores.entities.RequestState;

import java.util.List;

public interface IRequestStateService {

    /**
     * Returns the request states of the system
     *
     * @return    the list of the request states from the database.
     */
    List<RequestState> getRequestStates();
    /**
     * Returns a request state searched by the id
     *
     * @param     id this is the id of the request state that we are looking for
     * @return    the request state retrieved from the database
     */
    RequestState getRequestState(int id);
    /**
     * Creates a request state in the system
     *
     * @param     name this is the name of the request state we want to create
     * @return    the created request state
     */
    RequestState createRequestState(String name);
    /**
     * Updates the param of a request state
     *
     * @param     id this is the id of the request state that we want to update
     * @param     name this is the name of the request state we want to update
     * @return    the updated request state
     */
    RequestState updateRequestState(int id, String name);
    /**
     * Deletes a request state
     *
     * @param     id this is the id of the request state that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    boolean deleteRequestState(int id);
    /**
     * Returns a request state searched by the name
     *
     * @param     name this is the name of the request state that we are looking for
     * @return    the request state retrieved from the database
     */
    RequestState searchByName(String name);
}
