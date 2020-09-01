package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unis.stores.constants.Constants;
import unis.stores.entities.Line;
import unis.stores.result.line.CreateLineResult;
import unis.stores.result.line.DeleteLineResult;
import unis.stores.result.line.GetLineResult;
import unis.stores.result.line.UpdateLineResult;
import unis.stores.services.line.LineService;

import java.util.Map;

@CrossOrigin
@Controller
public class LineController {

    /**
     * The line service to connect to the database
     */
    @Autowired
    private LineService lineService;

    /**
     * Create a line in the system
     *
     * @param     body contains the information to create the line
     * @return    returns the result of the creation action
     */
    @PostMapping("/line")
    public ResponseEntity<Object> create(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.LINE_NAME_LABEL))
            return ResponseEntity.badRequest().body(new CreateLineResult(false, "Bad Request", null));

        if (lineService.searchByName(body.get(Constants.LINE_NAME_LABEL)) != null)
            return ResponseEntity.badRequest().body(new CreateLineResult(false, "The line already exists", null));

        Line line = lineService.createLine(body.get(Constants.LINE_NAME_LABEL));

        if (line == null)
            return ResponseEntity.badRequest().body(new CreateLineResult(false, "Error creating the line", null));
        else
            return ResponseEntity.ok(new CreateLineResult(true, "Line created", line));
    }

    /**
     * Update a line in the system
     *
     * @param     body contains the information to update the line
     * @return    returns the result of the update action
     */
    @PutMapping("/line")
    public ResponseEntity<Object> update(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.LINE_ID_LABEL) || !body.containsKey(Constants.LINE_NAME_LABEL))
            return ResponseEntity.badRequest().body(new UpdateLineResult(false, "Bad Request", null));

        try {
            int id = Integer.parseInt(body.get(Constants.LINE_ID_LABEL));
            Line line = lineService.updateLine(id, body.get(Constants.LINE_NAME_LABEL));

            if (line == null)
                return ResponseEntity.badRequest().body(new UpdateLineResult(false, "Error updating the line", null));
            else
                return ResponseEntity.ok(new UpdateLineResult(true, "Line updated", line));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateLineResult(false, "Bad Request", null));
        }
    }

    /**
     * Delete a line in the system
     *
     * @param     id the id line we want to delete
     * @return    returns the result of the deletion action
     */
    @DeleteMapping("/line/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new DeleteLineResult(false, "Bad Request"));

        try {
            int lineId = Integer.parseInt(id);

            if (lineService.deleteLine(lineId))
                return ResponseEntity.ok(new DeleteLineResult(true, "Line deleted"));
            else
                return ResponseEntity.badRequest().body(new DeleteLineResult(false, "Error deleting the line"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DeleteLineResult(false, "Bad Request"));
        }
    }

    /**
     * Gets the system lines
     *
     * @return    returns the list of lines in the system
     */
    @GetMapping("/line")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(lineService.getLines());
    }

    /**
     * Gets a line
     *
     * @param     id the id of the line we want to get
     * @return    returns the founded line
     */
    @GetMapping("/line/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new GetLineResult(false, "Bad Request", null));

        try {
            int lineId = Integer.parseInt(id);

            Line line = lineService.getLine(lineId);

            return line == null ? ResponseEntity.badRequest().body(new GetLineResult(false, "Error getting the line", null)) :
                    ResponseEntity.ok().body(new GetLineResult(true, "", line));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GetLineResult(false, "Bad Request", null));
        }
    }
}
