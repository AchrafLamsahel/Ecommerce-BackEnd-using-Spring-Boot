package ma.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO implements GrantedAuthority {
    private Long id;
    private String role;

    public RoleDTO( String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}

