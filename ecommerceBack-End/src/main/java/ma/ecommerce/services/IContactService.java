package ma.ecommerce.services;

import ma.ecommerce.dto.ContactDTO;
import org.springframework.stereotype.Service;

@Service
public interface IContactService {
    void saveContact(ContactDTO contactDTO);
}
