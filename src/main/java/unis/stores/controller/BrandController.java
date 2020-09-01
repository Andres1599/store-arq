package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unis.stores.constants.Constants;
import unis.stores.entities.Brand;
import unis.stores.result.brand.CreateBrandResult;
import unis.stores.result.brand.DeleteBrandResult;
import unis.stores.result.brand.GetBrandResult;
import unis.stores.result.brand.UpdateBrandResult;
import unis.stores.services.brand.BrandService;

import java.util.Map;

@CrossOrigin
@Controller
public class BrandController {

    /**
     * The brand service to connect to the database
     */
    @Autowired
    private BrandService brandService;

    /**
     * Create a brand in the system
     *
     * @param     body contains the information to create the brand
     * @return    returns the result of the creation action
     */
    @PostMapping("/brand")
    public ResponseEntity<Object> create(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.BRAND_NAME_LABEL))
            return ResponseEntity.badRequest().body(new CreateBrandResult(false, "Bad Request", null));

        if (brandService.searchByName(body.get(Constants.BRAND_NAME_LABEL)) != null)
            return ResponseEntity.badRequest().body(new CreateBrandResult(false, "The brand already exists", null));

        Brand brand = brandService.createBrand(body.get(Constants.BRAND_NAME_LABEL));

        if (brand == null)
            return ResponseEntity.badRequest().body(new CreateBrandResult(false, "Error creating the brand", null));
        else
            return ResponseEntity.ok(new CreateBrandResult(true, "Brand created", brand));
    }

    /**
     * Update a brand in the system
     *
     * @param     body contains the information to update the brand
     * @return    returns the result of the update action
     */
    @PutMapping("/brand")
    public ResponseEntity<Object> update(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.BRAND_ID_LABEL) || !body.containsKey(Constants.BRAND_NAME_LABEL))
            return ResponseEntity.badRequest().body(new UpdateBrandResult(false, "Bad Request", null));

        try {
            int id = Integer.parseInt(body.get(Constants.BRAND_ID_LABEL));
            Brand brand = brandService.updateBrand(id, body.get(Constants.BRAND_NAME_LABEL));

            if (brand == null)
                return ResponseEntity.badRequest().body(new UpdateBrandResult(false, "Error updating the brand", null));
            else
                return ResponseEntity.ok(new UpdateBrandResult(true, "Brand updated", brand));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateBrandResult(false, "Bad Request", null));
        }
    }

    /**
     * Delete a brand in the system
     *
     * @param     id the id brand we want to delete
     * @return    returns the result of the deletion action
     */
    @DeleteMapping("/brand/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new DeleteBrandResult(false, "Bad Request"));

        try {
            int brandId = Integer.parseInt(id);

            if (brandService.deleteBrand(brandId))
                return ResponseEntity.ok(new DeleteBrandResult(true, "Brand deleted"));
            else
                return ResponseEntity.badRequest().body(new DeleteBrandResult(false, "Error deleting the brand"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DeleteBrandResult(false, "Bad Request"));
        }
    }

    /**
     * Gets the system brands
     *
     * @return    returns the list of brands in the system
     */
    @GetMapping("/brand")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(brandService.getBrands());
    }

    /**
     * Gets a brand
     *
     * @param     id the id of the brand we want to get
     * @return    returns the founded brand
     */
    @GetMapping("/brand/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new GetBrandResult(false, "Bad Request", null));

        try {
            int brandId = Integer.parseInt(id);

            Brand brand = brandService.getBrand(brandId);

            return brand == null ? ResponseEntity.badRequest().body(new GetBrandResult(false, "Error getting the brand", null)) :
                    ResponseEntity.ok().body(new GetBrandResult(true, "", brand));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GetBrandResult(false, "Bad Request", null));
        }
    }
}
