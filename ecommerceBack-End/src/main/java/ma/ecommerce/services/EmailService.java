package ma.ecommerce.services;

import ma.ecommerce.dto.EmailDTO;
import ma.ecommerce.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    public void sendCodeByMail(String mail) throws Exception;

    UserDTO resetPassword(String code, String newPassword);
}
