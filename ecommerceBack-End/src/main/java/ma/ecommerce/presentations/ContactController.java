package ma.ecommerce.presentations;

import jakarta.validation.Valid;
import ma.ecommerce.dto.ContactDTO;
import ma.ecommerce.services.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RestControllerAdvice
public class ContactController {

    @Autowired
    private IContactService iContactService;

    /**-------------------------saveContact() ----------------------------*/
    @PostMapping(value = "/contact", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> saveContact(@Valid @RequestBody ContactDTO contactDTO) {
        iContactService.saveContact(contactDTO);
        return new ResponseEntity<>("Your Message is saved successfully ! ", HttpStatus.OK);
    }




}
