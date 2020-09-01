package unis.stores.services.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Brand;
import unis.stores.repositories.BrandRepository;

import java.util.List;

@Service
public class BrandService implements IBrandService {

    /**
     * The brand repository to connect to the database
     */
    @Autowired
    private BrandRepository brandRepository;


    /**
     * Returns the brands of the system
     *
     * @return    the list of the brands from the database.
     */
    @Override
    public List<Brand> getBrands() {
        return (List<Brand>) brandRepository.findAll();
    }

    /**
     * Returns a brand searched by the id
     *
     * @param     id this is the id of the brand that we are looking for
     * @return    the brand retrieved from the database
     */
    @Override
    public Brand getBrand(int id) {
        return brandRepository.findOne(id);
    }

    /**
     * Creates a brand in the system
     *
     * @param     name this is the name of the brand we want to create
     * @return    the created brand
     */
    @Override
    public Brand createBrand(String name) {

        if (name == null)
            return null;

        Brand brand = new Brand();
        brand.setName(name);

        return brandRepository.save(brand);
    }

    /**
     * Updates the param of a brand
     *
     * @param     id this is the id of the brand that we want to update
     * @param     name this is the name of the brand we want to update
     * @return    the updated brand
     */
    @Override
    public Brand updateBrand(int id, String name) {
        if (!brandRepository.exists(id))
            return null;

        Brand brand = brandRepository.findOne(id);
        brand.setName(name);

        return brandRepository.save(brand);
    }

    /**
     * Deletes a brand
     *
     * @param     id this is the id of the brand that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    @Override
    public boolean deleteBrand(int id) {
        if (!brandRepository.exists(id))
            return false;

        brandRepository.delete(id);
        return true;
    }

    /**
     * Returns a brand searched by the name
     *
     * @param     name this is the name of the brand that we are looking for
     * @return    the brand retrieved from the database
     */
    @Override
    public Brand searchByName(String name) {
        return brandRepository.findByName(name);
    }
}
