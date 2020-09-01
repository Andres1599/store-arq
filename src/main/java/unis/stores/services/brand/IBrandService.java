package unis.stores.services.brand;

import unis.stores.entities.Brand;

import java.util.List;

public interface IBrandService {

    /**
     * Returns the brands of the system
     *
     * @return    the list of the brands from the database.
     */
    List<Brand> getBrands();
    /**
     * Returns a brand searched by the id
     *
     * @param     id this is the id of the brand that we are looking for
     * @return    the brand retrieved from the database
     */
    Brand getBrand(int id);
    /**
     * Creates a brand in the system
     *
     * @param     name this is the name of the brand we want to create
     * @return    the created brand
     */
    Brand createBrand(String name);
    /**
     * Updates the param of a brand
     *
     * @param     id this is the id of the brand that we want to update
     * @param     name this is the name of the brand we want to update
     * @return    the updated brand
     */
    Brand updateBrand(int id, String name);
    /**
     * Deletes a brand
     *
     * @param     id this is the id of the brand that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    boolean deleteBrand(int id);
    /**
     * Returns a brand searched by the name
     *
     * @param     name this is the name of the brand that we are looking for
     * @return    the brand retrieved from the database
     */
    Brand searchByName(String name);
}
