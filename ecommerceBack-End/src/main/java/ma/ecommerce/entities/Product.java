package ma.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String category;
    private String targetGender;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date datepost = new Date(System.currentTimeMillis());
    private String description;
    private String city;
    private double coin;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String image;
}
