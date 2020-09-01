package unis.stores.result.requeststate;

import unis.stores.entities.RequestState;
import unis.stores.result.BaseResult;

public class CreateRequestStateResult extends BaseResult {

    private RequestState requestState;

    public CreateRequestStateResult() {
    }

    public CreateRequestStateResult(boolean success, String message, RequestState requestState) {
        super(success, message);
        this.requestState = requestState;
    }

    public RequestState getRequestState() {
        return requestState;
    }

    public void setRequestState(RequestState requestState) {
        this.requestState = requestState;
    }
}
