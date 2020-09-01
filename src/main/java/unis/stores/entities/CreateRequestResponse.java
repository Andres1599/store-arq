package unis.stores.entities;

import java.util.Date;

public class CreateRequestResponse {

    private boolean ok;
    private Date data;

    public CreateRequestResponse() {
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
