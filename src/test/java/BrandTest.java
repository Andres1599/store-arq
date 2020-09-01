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
import unis.stores.entities.Brand;
import unis.stores.entities.Product;
import unis.stores.entities.Rol;
import unis.stores.entities.User;
import unis.stores.services.brand.BrandService;

@SpringBootTest(classes = MainApplicationClass.class)
@RunWith(SpringRunner.class)
@PropertySource("classpath:application-test.properties")
public class BrandTest {

    @Autowired
    BrandService brandService;

    private Brand testBrand = new Brand();

    private static final String NEW_BRAND_NAME = "New Brand Name";

    public BrandTest() {
        this.testBrand.setName("Brand");
    }

    @Before
    public void createBrand() {
        MockitoAnnotations.initMocks(this);
        this.testBrand = brandService.createBrand(this.testBrand.getName());

        Assertions.assertNotEquals(null, this.testBrand);
    }

    @After
    public void deleteBrand() {
        Assertions.assertEquals(true, brandService.deleteBrand(this.testBrand.getId()));
    }

    @Test
    public void updateBrand() {
        this.testBrand.setName(NEW_BRAND_NAME);
        this.testBrand = brandService.updateBrand(this.testBrand.getId(), this.testBrand.getName());

        Assertions.assertEquals(NEW_BRAND_NAME, this.testBrand.getName());
    }

    @Test
    public void getBrand() {
        Assertions.assertNotEquals(null, brandService.getBrand(this.testBrand.getId()));
    }

    @Test
    public void checkNoEmpty() {
        Brand emptyBrand = brandService.createBrand(null);

        Assertions.assertEquals(null, emptyBrand);
    }
}
