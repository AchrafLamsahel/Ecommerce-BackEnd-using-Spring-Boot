package ma.ecommerce.services;

import ma.ecommerce.dto.ContactDTO;
import ma.ecommerce.mappers.ContactMapper;
import ma.ecommerce.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements IContactService{
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void saveContact(ContactDTO contactDTO) {
        contactRepository.save(ContactMapper.dtoToContact(contactDTO));
    }

}
