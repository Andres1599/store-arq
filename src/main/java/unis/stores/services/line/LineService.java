package unis.stores.services.line;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Line;
import unis.stores.repositories.LineRepository;

import java.util.List;

@Service
public class LineService implements ILineService {

    /**
     * The line repository to connect to the database
     */
    @Autowired
    private LineRepository lineRepository;

    /**
     * Returns the lines of the system
     *
     * @return    the list of the lines from the database.
     */
    @Override
    public List<Line> getLines() {
        return (List<Line>) lineRepository.findAll();
    }

    /**
     * Returns a line searched by the id
     *
     * @param     id this is the id of the line that we are looking for
     * @return    the line retrieved from the database
     */
    @Override
    public Line getLine(int id) {
        return lineRepository.findOne(id);
    }

    /**
     * Creates a line in the system
     *
     * @param     name this is the name of the line we want to create
     * @return    the created line
     */
    @Override
    public Line createLine(String name) {
        if (name == null)
            return null;

        Line line = new Line();
        line.setName(name);

        return lineRepository.save(line);
    }

    /**
     * Updates the param of a line
     *
     * @param     id this is the id of the line that we want to update
     * @param     name this is the name of the line we want to update
     * @return    the updated line
     */
    @Override
    public Line updateLine(int id, String name) {
        if (!lineRepository.exists(id))
            return null;

        Line line = lineRepository.findOne(id);
        line.setName(name);

        return lineRepository.save(line);
    }

    /**
     * Deletes a line
     *
     * @param     id this is the id of the line that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    @Override
    public boolean deleteLine(int id) {
        if (!lineRepository.exists(id))
            return false;

        lineRepository.delete(id);
        return true;
    }

    /**
     * Returns a line searched by the name
     *
     * @param     name this is the name of the line that we are looking for
     * @return    the line retrieved from the database
     */
    @Override
    public Line searchByName(String name) {
        return lineRepository.findByName(name);
    }
}
