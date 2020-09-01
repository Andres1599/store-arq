package unis.stores.result.client;

public class DeleteClientResult {

    private boolean success;
    private String messagge;

    public DeleteClientResult(boolean success, String messagge) {
        this.success = success;
        this.messagge = messagge;
    }

    public DeleteClientResult() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessagge() {
        return messagge;
    }

    public void setMessagge(String messagge) {
        this.messagge = messagge;
    }
}
