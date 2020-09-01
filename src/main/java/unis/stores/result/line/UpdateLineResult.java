package unis.stores.result.line;

import unis.stores.entities.Line;
import unis.stores.result.BaseResult;

public class UpdateLineResult extends BaseResult {

    private Line line;

    public UpdateLineResult() {
    }

    public UpdateLineResult(boolean success, String message, Line line) {
        super(success, message);
        this.line = line;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }
}
