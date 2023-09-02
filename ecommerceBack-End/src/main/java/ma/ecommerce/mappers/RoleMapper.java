package ma.ecommerce.mappers;

import ma.ecommerce.dto.RoleDTO;
import ma.ecommerce.entities.Role;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {
    public static RoleDTO roleToDTO(Role role){
        if(role == null || role.getId() == null) return null;
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRole(role.getRole());
        return roleDTO;
    }
    public static Role dtoToRole(RoleDTO roleDTO){
        if( roleDTO == null) return null;
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO,role);
        return role;
    }

    public static List<Role> listDtoToListRole (List<RoleDTO> roleDTOList){
        if ( roleDTOList == null ||  roleDTOList.isEmpty() ) return null;
        return roleDTOList.stream().map(RoleMapper::dtoToRole)
                .collect(Collectors.toList());
    }
    public static List<RoleDTO> listToListDTO(List<Role> roleList){
        if (roleList == null ||roleList.isEmpty()) return null;
        return roleList.stream().map(RoleMapper::roleToDTO)
                .collect(Collectors.toList());
    }

}
