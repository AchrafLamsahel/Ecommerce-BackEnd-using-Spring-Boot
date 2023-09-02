package ma.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@NoArgsConstructor
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
}
