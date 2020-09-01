package unis.stores.result.request;

import unis.stores.entities.Request;
import unis.stores.result.BaseResult;

public class UpdateRequestStateResult extends BaseResult {

    private Request request;

    public UpdateRequestStateResult() {
    }

    public UpdateRequestStateResult(boolean success, String message, Request request) {
        super(success, message);
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
