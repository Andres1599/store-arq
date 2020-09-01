package unis.stores.services.client;

import unis.stores.entities.Client;

import java.util.List;

public interface IClientService {

    /**
     * Returns the clients of the system
     *
     * @return    the list of the clients from the database.
     */
    List<Client> getClients();
    /**
     * Returns a client searched by the id
     *
     * @param     id this is the id of the client that we are looking for
     * @return    the client retrieved from the database
     */
    Client getClientById(int id);
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
    Client createClient(String name, String nit, String email, String phone, String image, int subscriptionId);
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
    Client updateClient(int id, String name, String nit, String email, String phone, String image, int subscriptionId);
    /**
     * Deletes a client
     *
     * @param     id this is the id of the client that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    boolean deleteClient(int id);
    /**
     * Renew the membership of a client
     *
     * @param     id this is the id of the client we want to renew its membership
     * @return    the updated client
     */
    Client renewMembership(int id);
    /**
     * Returns a client searched by the name
     *
     * @param     nit this is the nit of the client that we are looking for
     * @return    the client retrieved from the database
     */
    Client searchByNit(String nit);
}
