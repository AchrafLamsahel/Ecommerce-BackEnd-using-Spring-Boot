package ma.ecommerce.mappers;

import ma.ecommerce.dto.ProductDTO;
import ma.ecommerce.entities.Product;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static Product dtoToProduct(ProductDTO productDTO){
        if (productDTO == null ) return null;
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setTargetGender(productDTO.getTargetGender());
        product.setDatepost(productDTO.getDatepost());
        product.setDescription(productDTO.getDescription());
        product.setCity(productDTO.getCity());
        product.setCoin(productDTO.getCoin());
        product.setImage(productDTO.getImage());
        return product;
    }

    public static ProductDTO productToDto(Product product){
        if (product == null ) return null;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategory(product.getCategory());
        productDTO.setTargetGender(product.getTargetGender());
        productDTO.setDatepost(product.getDatepost());
        productDTO.setDescription(product.getDescription());
        productDTO.setCity(product.getCity());
        productDTO.setCoin(product.getCoin());
        productDTO.setImage(product.getImage());
        return productDTO;
    }

    public static List<Product> listDtoToList(List<ProductDTO> productDTOList){
        if ( productDTOList==null || productDTOList.isEmpty()) return null;
        return productDTOList.stream().map(ProductMapper::dtoToProduct)
                .collect(Collectors.toList());
    }

    public static List<ProductDTO> listToListDto(List<Product> productList){
        if( productList==null || productList.isEmpty()) return null;
        return productList.stream().map(ProductMapper::productToDto)
                .collect(Collectors.toList());
    }
}
