package ma.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    @NotEmpty(message = "name must not be empty")
    private String name;
    private String category;
    private String targetGender;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date datepost = new Date(System.currentTimeMillis());
    private String description;
    private String city;
    private double coin;
    private String image;

    public ProductDTO(String name, String category, String targetGender, Date datepost, String description, String city, double coin, String image) {
        this.name = name;
        this.category = category;
        this.targetGender = targetGender;
        this.datepost = datepost;
        this.description = description;
        this.city = city;
        this.coin = coin;
        this.image = image;
    }
}
