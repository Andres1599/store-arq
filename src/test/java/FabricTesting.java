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
import unis.stores.entities.Fabric;
import unis.stores.services.fabric.FabricService;

@SpringBootTest(classes = MainApplicationClass.class)
@RunWith(SpringRunner.class)
@PropertySource("classpath:application-test.properties")
public class FabricTesting {

    @Autowired
    FabricService fabricService;

    private Fabric testFabric = new Fabric();

    private static final String NEW_FABRIC_NAME = "New Fabric Name";

    public FabricTesting() {
        this.testFabric.setName("New Fabric");
        this.testFabric.setIp("localhost");
        this.testFabric.setServicePassword("password-fabric1");
    }

    @Before
    public void createFabric() {
        MockitoAnnotations.initMocks(this);
        this.testFabric = fabricService.createFabric(this.testFabric.getName(), this.testFabric.getIp(),
                this.testFabric.getServicePassword());

        Assertions.assertNotEquals(null, this.testFabric);
    }

    @After
    public void deleteFabric() {
        Assertions.assertEquals(true, fabricService.deleteFabric(this.testFabric.getId()));
    }

    @Test
    public void updateFabric() {
        this.testFabric.setName(NEW_FABRIC_NAME);
        this.testFabric = fabricService.updateFabric(this.testFabric.getId(), this.testFabric.getName(), this.testFabric.getIp(),
                this.testFabric.getServicePassword());

        Assertions.assertEquals(NEW_FABRIC_NAME, this.testFabric.getName());
    }

    @Test
    public void getFabric() {
        Assertions.assertNotEquals(null, fabricService.getFabric(this.testFabric.getId()));
    }

    @Test
    public void checkNoEmpty() {
        Fabric emptyFabric = fabricService.createFabric(null, "ip", "password");

        Assertions.assertEquals(null, emptyFabric);
    }
}