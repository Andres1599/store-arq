package unis.stores.result.vehicle;

import unis.stores.result.BaseResult;

public class GetVehicleResult extends BaseResult {

    public GetVehicleResult() {
    }

    public GetVehicleResult(boolean success, String message) {
        super(success, message);
    }
}
