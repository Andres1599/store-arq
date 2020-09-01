package unis.stores.result.line;

import unis.stores.result.BaseResult;

public class DeleteLineResult extends BaseResult {

    public DeleteLineResult() {
    }

    public DeleteLineResult(boolean success, String message) {
        super(success, message);
    }
}
