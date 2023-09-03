package ma.ecommerce.exceptionsHandler;

import jakarta.annotation.Nullable;
import ma.ecommerce.exceptions.BusinessException;
import ma.ecommerce.exceptions.BusinessExceptionAuthentification;
import ma.ecommerce.exceptions.EmailAlreadyExist;
import ma.ecommerce.exceptions.RecordNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
                        /**   Pour Intercepter Les Exceptions  */
    @Override
    @Nullable /** By adding the @Nullable annotation, you're specifying that the method handleMethodArgumentNotValid is allowed to return null. Keep in mind that using @Nullable is just an indication and doesn't enforce the actual behavior of the method. It's up to you to ensure that the method returns an appropriate ResponseEntity<Object> or null as per your requirement.*/
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class , BusinessException.class })
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error =  new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { RecordNotFoundException.class })
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Record Not Found", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EmailAlreadyExist.class)
    public final ResponseEntity<Object> handleEmailExistAlreadyException(EmailAlreadyExist ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Record Not Found", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = BusinessExceptionAuthentification.class)
    public final ResponseEntity<Object> BusinessExceptionAuthentification(BusinessExceptionAuthentification ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error,HttpStatus.NOT_FOUND);
    }



}
