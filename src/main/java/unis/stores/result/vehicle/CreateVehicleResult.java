package unis.stores.result.vehicle;

public class CreateVehicleResult {

    private boolean success;
    private String message;

    public CreateVehicleResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public CreateVehicleResult() {
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
