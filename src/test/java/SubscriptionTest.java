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
import unis.stores.entities.Subscription;
import unis.stores.services.subscription.SubscriptionService;

@SpringBootTest(classes = MainApplicationClass.class)
@RunWith(SpringRunner.class)
@PropertySource("classpath:application-test.properties")
public class SubscriptionTest {

    @Autowired
    SubscriptionService subscriptionService;

    private Subscription testSubscription = new Subscription();

    private static final int NEW_SUBSCRIPTION_DISCOUNT = 50;

    public SubscriptionTest() {
        this.testSubscription.setName("Subscription");
        this.testSubscription.setDiscount(30);
    }

    @Before
    public void createSubscription() {
        MockitoAnnotations.initMocks(this);
        this.testSubscription = subscriptionService.createSubscription(this.testSubscription.getName(), this.testSubscription.getDiscount());

        Assertions.assertNotEquals(null, this.testSubscription);
    }

    @After
    public void deleteSubscription() {
        Assertions.assertEquals(true, subscriptionService.deleteSubscription(this.testSubscription.getId()));
    }

    @Test
    public void updateSubscription() {
        this.testSubscription.setDiscount(NEW_SUBSCRIPTION_DISCOUNT);
        this.testSubscription = subscriptionService.updateSubscription(this.testSubscription.getId(), this.testSubscription.getName(), this.testSubscription.getDiscount());

        Assertions.assertEquals(NEW_SUBSCRIPTION_DISCOUNT, this.testSubscription.getDiscount());
    }

    @Test
    public void getSubscription() {
        Assertions.assertNotEquals(null, subscriptionService.getSubscriptionById(this.testSubscription.getId()));
    }

    @Test
    public void checkNoEmpty() {
        Subscription emptySubscription = subscriptionService.createSubscription(null, 20);

        Assertions.assertEquals(null, emptySubscription);
    }
}
