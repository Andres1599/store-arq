package unis.stores.services.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Client;
import unis.stores.repositories.ClientRepository;
import unis.stores.repositories.SubscriptionRepository;

import java.util.Calendar;
import java.util.List;

@Service
public class ClientService implements IClientService {

    /**
     * The client repository to connect to the database
     */
    @Autowired
    private ClientRepository clientRepository;

    /**
     * The subscription repository to connect to the database
     */
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    /**
     * Returns the clients of the system
     *
     * @return    the list of the clients from the database.
     */
    @Override
    public List<Client> getClients() {
        return (List<Client>) clientRepository.findAll();
    }

    /**
     * Returns a client searched by the id
     *
     * @param     id this is the id of the client that we are looking for
     * @return    the client retrieved from the database
     */
    @Override
    public Client getClientById(int id) {
        if (!clientRepository.exists(id))
            return null;

        return clientRepository.findOne(id);
    }

    /**
     * Creates a client in the system
     *
     * @param     name this is the name of the client we want to create
     * @param     nit this is the nit of the client we want to create
     * @param     email this is the email of the client we want to create
     * @param     phone this is the phone of the client we want to create
     * @param     image this is the image of the client we want to create
     * @param     subscriptionId this is the subscriptionId of the client we want to create
     * @return    the created client
     */
    @Override
    public Client createClient(String name, String nit, String email, String phone, String image, int subscriptionId) {
        if (!subscriptionRepository.exists(subscriptionId))
            return null;

        Client newClient = new Client();

        newClient.setName(name);
        newClient.setNit(nit);
        newClient.setEmail(email);
        newClient.setPhone(phone);
        newClient.setImage(image);
        newClient.setSubscription(subscriptionRepository.findOne(subscriptionId));

        return clientRepository.save(newClient);
    }

    /**
     * Updates the param of a client
     *
     * @param     id this is the id of the client that we want to update
     * @param     name this is the name of the client we want to update
     * @param     nit this is the nit of the client we want to update
     * @param     email this is the email of the client we want to update
     * @param     phone this is the phone of the client we want to update
     * @param     image this is the image of the client we want to update
     * @param     subscriptionId this is the subscriptionId of the client we want to update
     * @return    the updated client
     */
    @Override
    public Client updateClient(int id, String name, String nit, String email, String phone, String image, int subscriptionId) {
        if (!clientRepository.exists(id) || !subscriptionRepository.exists(subscriptionId))
            return null;

        Client updateClient = clientRepository.findOne(id);

        updateClient.setName(name);
        updateClient.setNit(nit);
        updateClient.setEmail(email);
        updateClient.setPhone(phone);
        updateClient.setImage(image);
        updateClient.setSubscription(subscriptionRepository.findOne(subscriptionId));

        return clientRepository.save(updateClient);
    }

    /**
     * Deletes a client
     *
     * @param     id this is the id of the client that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    @Override
    public boolean deleteClient(int id) {
        if (!clientRepository.exists(id))
            return false;

        clientRepository.delete(id);
        return true;
    }

    /**
     * Renew the membership of a client
     *
     * @param     id this is the id of the client we want to renew its membership
     * @return    the updated client
     */
    @Override
    public Client renewMembership(int id) {
        if (clientRepository.exists(id))
            return null;

        Client client = clientRepository.findOne(id);

        Calendar c = Calendar.getInstance();
        c.setTime(client.getSubExpireDate());
        c.add(Calendar.YEAR, 1);

        client.setSubExpireDate(c.getTime());
        return clientRepository.save(client);
    }

    /**
     * Returns a client searched by the name
     *
     * @param     nit this is the nit of the client that we are looking for
     * @return    the client retrieved from the database
     */
    @Override
    public Client searchByNit(String nit) {
        return clientRepository.findByNit(nit);
    }
}
