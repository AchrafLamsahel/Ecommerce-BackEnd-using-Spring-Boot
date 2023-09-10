package ma.ecommerce.services;

import ma.ecommerce.dto.EmailDTO;
import ma.ecommerce.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

     boolean sendCodeByMail(String mail) throws Exception;

}
