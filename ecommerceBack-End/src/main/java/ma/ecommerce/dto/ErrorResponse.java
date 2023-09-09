package ma.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "error") /** Java ---> ficher xml(Marshalling) spring boot >= 3.0 Ajoute dependance Jax-B 2.0 */
@Getter
@Setter
public class ErrorResponse {
    private String message;
    private List<String> details;
    public ErrorResponse(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }


}
