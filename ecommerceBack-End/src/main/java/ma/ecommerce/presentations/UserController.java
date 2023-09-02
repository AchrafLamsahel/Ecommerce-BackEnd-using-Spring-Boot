package ma.ecommerce.presentations;

import jakarta.validation.Valid;
import ma.ecommerce.dto.RoleDTO;
import ma.ecommerce.dto.UserDTO;
import ma.ecommerce.exceprions.EmailAlreadyExist;
import ma.ecommerce.exceprions.RecordNotFoundException;
import ma.ecommerce.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RestControllerAdvice
public class UserController {
    @Autowired
    private IUserService iUserService;

    /**---------------------------------- Save User --------------------------*/
    @PostMapping(value = "/adminCreate", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) {
        if (iUserService.isEmailExist(userDTO.getEmail())) {
            throw new EmailAlreadyExist("Email already exist : " + userDTO.getEmail());
        }
        userDTO.setAccountNonExpired(true);
        userDTO.setAccountNonLocked(true);
        userDTO.setCredentialsNonExpired(true);
        /*  ***** Par Defaut CLIENT    */
        userDTO.setEnabled(true);
        List<RoleDTO> roles =new ArrayList<>();
        roles.add(new RoleDTO("CLIENT"));
        userDTO.setRoles(roles);
        iUserService.saveUser(userDTO);
        return new ResponseEntity<String>("User created with success : "+ userDTO.getEmail(),
                HttpStatus.CREATED);
    }
    /**------------------------------ Get User By Id --------------------------*/
    @GetMapping(value = "/admin/user/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") Long id) {
        UserDTO userById = iUserService.getById(id);
        if (userById == null)
            return new ResponseEntity<>("User doesn't exist", HttpStatus.OK);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    /**------------------------------ Delete User --------------------------*/
    @DeleteMapping(value = "/admin/user/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long id) {
        UserDTO userDTO = iUserService.getById(id);
        if (userDTO == null)
            throw new RecordNotFoundException("No record with id=" + id);

        iUserService.remove(id);
        return new ResponseEntity<String>("Product removed with success", HttpStatus.OK);
    }

    /**--------------------------- findAll Users qui ont le role CLIENT---------------------------*/
    @GetMapping(value = "/admin/usersClient", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<UserDTO> getAllusers() {
        return iUserService.getAllUsers().stream()
                .filter(user -> user.getRoles().stream().anyMatch(role -> role.getRole().equals("CLIENT")))
                .collect(Collectors.toList());
    }

    /**--------------------------- findAll Users qui ont le role ADMINE---------------------------*/
    @GetMapping(value = "/admin/usersAdmin", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<UserDTO> getAllusersAdmin() {
        return iUserService.getAllUsers().stream()
                .filter(user -> user.getRoles().stream().anyMatch(role -> role.getRole().equals("ADMIN")))
                .collect(Collectors.toList());
    }

}
