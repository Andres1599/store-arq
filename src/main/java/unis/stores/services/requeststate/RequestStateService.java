package unis.stores.services.requeststate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.RequestState;
import unis.stores.repositories.RequestStateRepository;
import unis.stores.services.http.RestService;

import java.util.List;

@Service
public class RequestStateService implements IRequestStateService {

    /**
     * The request state repository to connect to the database
     */
    @Autowired
    private RequestStateRepository requestStateRepository;

    /**
     * Returns the request states of the system
     *
     * @return    the list of the request states from the database.
     */
    @Override
    public List<RequestState> getRequestStates() {
        return (List<RequestState>) requestStateRepository.findAll();
    }

    /**
     * Returns a request state searched by the id
     *
     * @param     id this is the id of the request state that we are looking for
     * @return    the request state retrieved from the database
     */
    @Override
    public RequestState getRequestState(int id) {
        return requestStateRepository.findOne(id);
    }

    /**
     * Creates a request state in the system
     *
     * @param     name this is the name of the request state we want to create
     * @return    the created request state
     */
    @Override
    public RequestState createRequestState(String name) {
        RequestState requestState = new RequestState();
        requestState.setName(name);

        return requestStateRepository.save(requestState);
    }

    /**
     * Updates the param of a request state
     *
     * @param     id this is the id of the request state that we want to update
     * @param     name this is the name of the request state we want to update
     * @return    the updated request state
     */
    @Override
    public RequestState updateRequestState(int id, String name) {
        if (!requestStateRepository.exists(id))
            return null;

        RequestState requestState = requestStateRepository.findOne(id);
        requestState.setName(name);

        return requestStateRepository.save(requestState);
    }

    /**
     * Deletes a request state
     *
     * @param     id this is the id of the request state that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    @Override
    public boolean deleteRequestState(int id) {
        if (!requestStateRepository.exists(id))
            return false;

        requestStateRepository.delete(id);
        return true;
    }

    /**
     * Returns a request state searched by the name
     *
     * @param     name this is the name of the request state that we are looking for
     * @return    the request state retrieved from the database
     */
    @Override
    public RequestState searchByName(String name) {
        return requestStateRepository.findByName(name);
    }
}
