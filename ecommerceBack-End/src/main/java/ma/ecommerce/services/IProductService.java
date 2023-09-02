package ma.ecommerce.services;

import ma.ecommerce.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IProductService {
    void saveProduct(ProductDTO productDTO);
    ProductDTO findByProductName(String product);
    List<ProductDTO> findAllProduct();
    void delete(Long id);
    List<ProductDTO> findAll(int pageId,int size);
    ProductDTO findProductById(Long id);
    List<ProductDTO> findByGender(String gender);

}
