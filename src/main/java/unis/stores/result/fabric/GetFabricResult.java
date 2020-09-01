package unis.stores.result.fabric;

import unis.stores.entities.Fabric;
import unis.stores.result.BaseResult;

public class GetFabricResult extends BaseResult {

    private Fabric fabric;

    public GetFabricResult() {
    }

    public GetFabricResult(boolean success, String message, Fabric fabric) {
        super(success, message);
        this.fabric = fabric;
    }

    public Fabric getFabric() {
        return fabric;
    }

    public void setFabric(Fabric fabric) {
        this.fabric = fabric;
    }
}
