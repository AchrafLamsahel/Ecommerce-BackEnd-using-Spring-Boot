package ma.ecommerce.services;

import ma.ecommerce.dto.RoleDTO;
import ma.ecommerce.dto.UserDTO;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface IUserService {

    void saveUser(UserDTO userDTO);
    void saveRole(RoleDTO roleDTO);
    List<UserDTO>  getAllUsers();
    List<RoleDTO> getAllRoles();
    boolean isEmailExist(String email);
    UserDTO getById(Long id);
    void remove(Long id);
    void cleanDataBase();
    RoleDTO getRoleByName(String role);
    boolean existsByName(String username); // New
    UserDTO findByUsername(String username); // New

    List<UserDTO> search(String username);

}
