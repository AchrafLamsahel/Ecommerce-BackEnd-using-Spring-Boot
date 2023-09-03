package ma.ecommerce.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BusinessExceptionAuthentification extends RuntimeException {
    public BusinessExceptionAuthentification(String message, Throwable cause) {
        super(message, cause);
    }


}
