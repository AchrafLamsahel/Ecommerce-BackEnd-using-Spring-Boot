package ma.ecommerce.presentations;

import jakarta.validation.Valid;
import ma.ecommerce.dto.ProductDTO;
import ma.ecommerce.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RestControllerAdvice
public class ProductController {
    @Autowired
    private IProductService productService;

    /**---------------------------------- Save Product ------------------------------*/
    @PostMapping(value = "/admin/create", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        productService.saveProduct(productDTO);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }

    /**-------------------------------- findAll Product --------------------------*/
    @GetMapping(value = "/client/product", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<ProductDTO> getProducts() {
        return productService.findAllProduct();
    }

    /**------------------------------- findById Product --------------------------*/
    @GetMapping(value = "/client/product/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getProductById(@PathVariable(value = "id") Long id) {
        ProductDTO productById = productService.findProductById(id);
        if (productById == null)
            return new ResponseEntity<>("Product doesn't exist", HttpStatus.OK);
        return new ResponseEntity<>(productById, HttpStatus.OK);
    }

    /**---------------------------------- update Product ----------------------------*/
    @PutMapping(value = "/admin/update/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> updateProduct(@PathVariable(name = "id") Long productId, @RequestBody ProductDTO productDTO) {
        ProductDTO productdto = productService.findProductById(productId);
        if (productdto == null)
            return new ResponseEntity<>("Product doesn't exist", HttpStatus.OK);
        productDTO.setId(productId);
        productService.saveProduct(productDTO);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

    /**---------------------------------- delete Product --------------------------*/
    @DeleteMapping(value = "/admin/delete/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> deleteProduct(@PathVariable(name = "id") Long productId) {
        ProductDTO productById = productService.findProductById(productId);
        if (productById == null)
            return new ResponseEntity<>("Product doesn't exist", HttpStatus.OK);
        productService.delete(productId);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }

    /**------------------------------- Get Product pagination --------------------------*/
    @GetMapping("/rest/pagination/{pageid}/{size}")
    public List<ProductDTO> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
        return productService.findAll(pageid, size);
    }

    @GetMapping(value = "/client/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByGender() {
        List<ProductDTO> productsByGender = productService.findByGender("Woman");
        if (productsByGender.isEmpty()) {
            return new ResponseEntity<>("No products found for the given gender", HttpStatus.OK);
        }
        return new ResponseEntity<>(productsByGender, HttpStatus.OK);
    }

}
