package unis.stores.result.vehicle;

import unis.stores.result.BaseResult;

public class DeleteVehicleResult extends BaseResult {

    public DeleteVehicleResult() {
    }

    public DeleteVehicleResult(boolean success, String message) {
        super(success, message);
    }
}
