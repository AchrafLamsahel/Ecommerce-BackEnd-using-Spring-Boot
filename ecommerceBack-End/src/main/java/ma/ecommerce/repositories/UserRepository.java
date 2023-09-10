package ma.ecommerce.repositories;

import ma.ecommerce.dto.EmailDTO;
import ma.ecommerce.entities.Product;
import ma.ecommerce.entities.Role;
import ma.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findUsersByEmail(String email);
    List<User> findByEmail(String mail);
    boolean existsByUsername(String username); // JWT :New
    List<User> findAllByUsername(String username);
    User findUsersByCode(String code);



}
