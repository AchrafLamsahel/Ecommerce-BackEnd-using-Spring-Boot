package ma.ecommerce.mappers;

import ma.ecommerce.dto.UserDTO;
import ma.ecommerce.entities.User;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO userToDTO(User user){
        if(user == null ) return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserImg(user.getUserImg());
        userDTO.setRoles(RoleMapper.listToListDTO(user.getRoles()));
        userDTO.setEnabled(user.isEnabled());
        userDTO.setAccountNonExpired(user.isAccountNonExpired());
        userDTO.setAccountNonLocked(user.isAccountNonLocked());
        userDTO.setCredentialsNonExpired(user.isCredentialsNonExpired());
        return userDTO;
    }

    public static User DtoToUser(UserDTO userDTO){
        if(userDTO == null ) return null;
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUserImg(userDTO.getUserImg());
        user.setRoles(RoleMapper.listDtoToListRole(userDTO.getRoles()));
        user.setEnabled(userDTO.isEnabled());
        user.setAccountNonExpired(userDTO.isAccountNonExpired());
        user.setAccountNonLocked(userDTO.isAccountNonLocked());
        user.setCredentialsNonExpired(userDTO.isCredentialsNonExpired());
        return user;
    }

    public static List<User> usersDtoToUsers(List<UserDTO> userDTOList) {
        if (userDTOList == null || userDTOList.isEmpty()) return null;
        return userDTOList.stream().map(UserMapper::DtoToUser)
                .collect(Collectors.toList());
    }

    public static List<UserDTO> usersToUsersDTO(List<User> users){
        if(users == null || users.isEmpty())return null;
        return users.stream().map(UserMapper::userToDTO)
                .collect(Collectors.toList());
    }

}
