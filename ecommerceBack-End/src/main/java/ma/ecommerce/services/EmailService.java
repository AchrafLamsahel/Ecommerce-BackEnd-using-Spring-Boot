package ma.ecommerce.services;

import ma.ecommerce.dto.EmailDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    public void sendCodeByMail(EmailDTO mail) throws Exception;

}
