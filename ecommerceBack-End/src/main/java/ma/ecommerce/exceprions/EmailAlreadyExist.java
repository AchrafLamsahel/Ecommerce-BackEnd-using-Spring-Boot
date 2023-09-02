package ma.ecommerce.exceprions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailAlreadyExist extends RuntimeException {
    private String message;
    public EmailAlreadyExist(String message) {
        this.message = message;
    }
}
