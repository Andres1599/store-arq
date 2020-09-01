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
import unis.stores.entities.Product;
import unis.stores.entities.Rol;
import unis.stores.entities.User;
import unis.stores.services.user.UserService;

@SpringBootTest(classes = MainApplicationClass.class)
@RunWith(SpringRunner.class)
@PropertySource("classpath:application-test.properties")
public class UnitTesting {

    @Autowired
    UserService userService;

    private User testUser = new User();
    private Rol testRol;

    private static final String NEW_USER_NAME = "ChangeUserName";

    public UnitTesting() {
        this.testUser.setFirstName("User");
        this.testUser.setLastName("Test");
        this.testUser.setUsername("userTest");
        this.testUser.setPassword("password");

        this.testRol = new Rol();
        this.testRol.setId(1);

        this.testUser.setRol(this.testRol);
    }

    @Before
    public void createUser() {
        MockitoAnnotations.initMocks(this);
        this.testUser = userService.createUser(this.testUser.getFirstName(), this.testUser.getLastName(), this.testUser.getUsername(),
                this.testUser.getPassword(), this.testUser.getRol().getId());

        Assertions.assertNotEquals(null, this.testUser);
    }

    @After
    public void deleteUser() {
        Assertions.assertEquals(true, userService.deleteUser(this.testUser.getId()));
    }

    @Test
    public void updateUser() {
        this.testUser.setFirstName("ChangeUserName");
        this.testUser = userService.updateUser(this.testUser.getId(), this.testUser.getFirstName(), this.testUser.getLastName(),
                this.testUser.getUsername());

        Assertions.assertEquals(NEW_USER_NAME, this.testUser.getFirstName());
    }

    @Test
    public void getUser() {
        Assertions.assertNotEquals(null, userService.getUserById(this.testUser.getId()));
    }

    @Test
    public void checkPrice() {
        Product product = new Product();
        product.setPrice(150.50);
        Assertions.assertEquals(product.getSalePrice(), 285.95);
    }

    @Test
    public void checkPriceWithoutIVA() {
        Product product = new Product();
        product.setPrice(100);
        Assertions.assertEquals(product.getValueWithoutIVA(), 169.64);
    }

    @Test
    public void checkNoEmpty() {
        User emptyUser = userService.createUser(null, this.testUser.getLastName(), this.testUser.getUsername(),
                this.testUser.getPassword(), this.testUser.getRol().getId());

        Assertions.assertEquals(null, emptyUser);
    }
}
