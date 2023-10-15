package ma.ecommerce.presentations;

import ma.ecommerce.exceptions.RecordNotFoundException;
import ma.ecommerce.services.IEmailService;
import ma.ecommerce.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RestControllerAdvice
public class EmailController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IEmailService emailService;

    @PostMapping(value = "/codeActivate/{email}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> sendCodeActivationToEmail(@PathVariable(value = "email")   String email) throws RecordNotFoundException {
        boolean result=emailService.sendCodeByMail(email);
        if (!result) {
            throw new RecordNotFoundException("Email don't found : " + email);
        }
        return new ResponseEntity<String>("Activate Code send successfully : "+ email,HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/resetPassword/{code}/{password}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> resetPassword(@RequestBody @PathVariable(value = "code") String code,
                                                @PathVariable(value = "password") String password) throws Exception {
        if (iUserService.resetPassword(code,password) == null) {
            throw new RecordNotFoundException("Code d'Activation don't found : " + code);
        }
        return new ResponseEntity<String>("Password successfully updated",HttpStatus.ACCEPTED);
    }


}
