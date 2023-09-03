package ma.ecommerce.exceprions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {
    private String message;
    public RecordNotFoundException(String message) {
        this.message = message;
    }
}
