package unis.stores.result.vehicle;

import unis.stores.result.BaseResult;

public class UpdateVehicleResult extends BaseResult {

    public UpdateVehicleResult() {
    }

    public UpdateVehicleResult(boolean success, String message) {
        super(success, message);
    }
}
