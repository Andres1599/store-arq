package unis.stores.result.user;

public class UpdateUserPasswordResult {

    private boolean success;
    private String message;

    public UpdateUserPasswordResult() {
    }

    public UpdateUserPasswordResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
