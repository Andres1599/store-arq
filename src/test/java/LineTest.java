import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import unis.stores.MainApplicationClass;
import unis.stores.entities.Line;
import unis.stores.services.line.LineService;

@SpringBootTest(classes = MainApplicationClass.class)
@RunWith(SpringRunner.class)
@PropertySource("classpath:application-test.properties")
public class LineTest {

    @Autowired
    LineService lineService;

    private Line testLine = new Line();

    private static final String NEW_LINE_NAME = "New Line Name";

    public LineTest() {
        this.testLine.setName("Line");
    }

    @Before
    public void createLine() {
        MockitoAnnotations.initMocks(this);
        this.testLine = lineService.createLine(this.testLine.getName());

        Assertions.assertNotEquals(null, this.testLine);
    }

    @After
    public void deleteLine() {
        Assertions.assertEquals(true, lineService.deleteLine(this.testLine.getId()));
    }

    @Test
    public void updateLine() {
        this.testLine.setName(NEW_LINE_NAME);
        this.testLine = lineService.updateLine(this.testLine.getId(), this.testLine.getName());

        Assertions.assertEquals(NEW_LINE_NAME, this.testLine.getName());
    }

    @Test
    public void getLine() {
        Assertions.assertNotEquals(null, lineService.getLine(this.testLine.getId()));
    }

    @Test
    public void checkNoEmpty() {
        Line emptyLine = lineService.createLine(null);

        Assertions.assertEquals(null, emptyLine);
    }
}
