package ma.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactDTO {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String emailAddress;
    @NotEmpty
    private String numberPhone;
    @NotEmpty
    private String message;

    public ContactDTO(String name, String emailAddress, String message) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.message = message;
    }

}
