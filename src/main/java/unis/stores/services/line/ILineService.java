package unis.stores.services.line;

import unis.stores.entities.Line;

import java.util.List;

public interface ILineService {

    /**
     * Returns the lines of the system
     *
     * @return    the list of the lines from the database.
     */
    List<Line> getLines();
    /**
     * Returns a line searched by the id
     *
     * @param     id this is the id of the line that we are looking for
     * @return    the line retrieved from the database
     */
    Line getLine(int id);
    /**
     * Creates a line in the system
     *
     * @param     name this is the name of the line we want to create
     * @return    the created line
     */
    Line createLine(String name);
    /**
     * Updates the param of a line
     *
     * @param     id this is the id of the line that we want to update
     * @param     name this is the name of the line we want to update
     * @return    the updated line
     */
    Line updateLine(int id, String name);
    /**
     * Deletes a line
     *
     * @param     id this is the id of the line that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    boolean deleteLine(int id);
    /**
     * Returns a line searched by the name
     *
     * @param     name this is the name of the line that we are looking for
     * @return    the line retrieved from the database
     */
    Line searchByName(String name);
}
