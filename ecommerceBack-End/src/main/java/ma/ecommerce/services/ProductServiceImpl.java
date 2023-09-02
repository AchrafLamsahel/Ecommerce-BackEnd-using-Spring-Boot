package ma.ecommerce.services;

import ma.ecommerce.dto.ProductDTO;
import ma.ecommerce.entities.Product;
import ma.ecommerce.mappers.ProductMapper;
import ma.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ControllerAdvice
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(ProductDTO productDTO) {
        productRepository.save(ProductMapper.dtoToProduct(productDTO));
    }

    @Override
    public ProductDTO findByProductName(String product) {
        return ProductMapper.productToDto(productRepository.findByName(product));
    }

    @Override
    public List<ProductDTO> findAllProduct() {
        List<Product> productList= productRepository.findAll();
        return productList.stream()
                .map(ProductMapper::productToDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public List<ProductDTO> findAll(int pageId, int size) {
        Page<Product> result = productRepository.findAll(PageRequest.of(pageId, size, Sort.Direction.ASC, "name"));
        return ProductMapper.listToListDto(result.getContent());
    }

    @Override
    public ProductDTO findProductById(Long id) {
        return ProductMapper.productToDto(productRepository.findById(id).orElse(null));
    }

    @Override
    public List<ProductDTO> findByGender(String gender) {
       List<Product> productList = productRepository.findProductByTargetGenderEquals(gender);
        return productList.stream().map(ProductMapper::productToDto).collect(Collectors.toList());
    }


}
