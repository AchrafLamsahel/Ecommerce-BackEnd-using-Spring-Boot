package ma.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String userImg;
    private boolean accountNonExpired;
    private  boolean accountNonLocked;
    private  boolean credentialsNonExpired;
    private  boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER  , cascade = { CascadeType.PERSIST,CascadeType.REMOVE})
    List <Role> roles ;
    private String code;

    public User(String username, String email, String password, String userImg, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, List<Role> roles, String code) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userImg = userImg;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.roles = roles;
        this.code = code;
    }
}
