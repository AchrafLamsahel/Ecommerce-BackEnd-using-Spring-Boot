package ma.ecommerce.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements UserDetails {
    private Long id;
    //@NotEmpty(message = "name must not be empty")
    private String username;
    //@NotEmpty(message = "email must not be empty")

    private String email;
   // @NotEmpty(message = "password must not be empty")

    private String userImg;
    private String password;

    public UserDTO(String username, String email, String userImg, String password) {
        this.username = username;
        this.email = email;
        this.userImg = userImg;
        this.password = password;
    }

    public UserDTO(String username, String email, String userImg, String password, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, List<RoleDTO> roles) {
        this.username = username;
        this.email = email;
        this.userImg = userImg;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.roles = roles;
    }

    private boolean accountNonExpired;

    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private List<RoleDTO> roles = new ArrayList<RoleDTO>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
