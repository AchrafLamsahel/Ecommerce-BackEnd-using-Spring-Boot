package ma.ecommerce.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "roles_id")
    private Long id;
    //@Column(name = "role")
    private String role;

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }


}
