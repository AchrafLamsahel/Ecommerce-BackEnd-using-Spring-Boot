package ma.ecommerce.repositories;

import ma.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    List<Product> findProductByTargetGenderEquals(String gender);

}
