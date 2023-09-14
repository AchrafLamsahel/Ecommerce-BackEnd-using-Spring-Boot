package ma.ecommerce.services;

import org.springframework.stereotype.Service;

@Service
public interface IEmailService {

     boolean sendCodeByMail(String mail) throws Exception;

}
