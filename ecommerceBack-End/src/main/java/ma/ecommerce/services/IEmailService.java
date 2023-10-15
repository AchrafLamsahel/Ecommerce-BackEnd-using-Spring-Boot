package ma.ecommerce.services;

import ma.ecommerce.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface IEmailService {

     boolean sendCodeByMail(String mail) ;

}
