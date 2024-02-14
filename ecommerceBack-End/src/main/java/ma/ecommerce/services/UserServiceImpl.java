package ma.ecommerce.services;

import ma.ecommerce.dto.RoleDTO;
import ma.ecommerce.dto.UserDTO;
import ma.ecommerce.entities.Role;
import ma.ecommerce.entities.User;
import ma.ecommerce.exceptions.BusinessException;
import ma.ecommerce.mappers.RoleMapper;
import ma.ecommerce.mappers.UserMapper;
import ma.ecommerce.repositories.ProductRepository;
import ma.ecommerce.repositories.RoleRepository;
import ma.ecommerce.repositories.UserRepository;
import ma.ecommerce.utils.UserCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements IUserService , UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void saveUser(UserDTO userDTO) {
        User user = UserMapper.dtoToUser(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        for(Role e: user.getRoles()){
            Role userRole = roleRepository.findByRole(e.getRole());
            if(userRole.getRole() == null) userRole.setRole("CLIENT");
            roles.add(userRole);
        }

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void saveRole(RoleDTO roleDTO) {
        roleRepository.save(RoleMapper.dtoToRole(roleDTO));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users=userRepository.findAll();
        return users.stream()
                .map(UserMapper::userToDTO).collect(Collectors.toList());
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return RoleMapper.listToListDTO(roleRepository.findAll());
    }

    @Override
    public boolean isEmailExist(String email) {
        List<User> list = userRepository.findByEmail(email);
        if (list != null && !list.isEmpty()) return true;
        return false;
    }

    @Override
    public UserDTO getById(Long id) {
        if (id == null) return null;
        Optional<User> user =  userRepository.findById(id);
        return UserMapper.userToDTO(user.orElse(null));
    }

    @Override
    public void remove(Long id) {
        if( id == null ) return;
        User user = userRepository.findById(id).orElse(null);
        user.getRoles().clear();
        userRepository.deleteById(id);

    }

    @Override
    public void cleanDataBase() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Override
    public RoleDTO getRoleByName(String role) {
        return RoleMapper.roleToDTO(roleRepository.findByRole(role)); // Si je metre get(0) error disparate
    }

    @Override
    public boolean existsByName(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDTO findByUsername(String username) {
        if (username == null || username.trim().equals(""))
            throw new BusinessException("login is empty !!");
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new BusinessException("No user with this login");
        UserDTO userDTO = UserMapper.userToDTO(user);
        return userDTO;    }

    @Override
    public List<UserDTO> search(String username) {
        List<User> list = userRepository.findAllByUsername(username);
        return list.stream().map(UserMapper::userToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserByCode(String email) {
        User user = userRepository.findUsersByCode(email);
        if(user != null) return UserMapper.userToDTO(user);
        else
            throw new BusinessException("No user with this Email");
    }
    @Override
    public void editUser(UserDTO userDTO){
        userRepository.save(UserMapper.dtoToUser(userDTO));
    }

    @Override
    public UserDTO resetPassword(String code, String newPassword) throws Exception {
        UserDTO userDTo = UserMapper.userToDTO(userRepository.findUsersByCode(code));
        if(userDTo != null) {
            if(userDTo.getCode().equals(code)){
                String pss= bCryptPasswordEncoder.encode(newPassword);
                userDTo.setPassword(pss);
                String newCode = UserCode.getCode();
                userDTo.setCode(newCode);
                editUser(userDTo);
            }else{
                throw new RuntimeException("Failed Code with  :  " + userDTo.getCode());
            }
        }else  {
            throw new RuntimeException("User Don' Found With Code   :  " + code);
        }
        return userDTo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserMapper.userToDTO(userRepository.findByUsername(username));
    }


}
