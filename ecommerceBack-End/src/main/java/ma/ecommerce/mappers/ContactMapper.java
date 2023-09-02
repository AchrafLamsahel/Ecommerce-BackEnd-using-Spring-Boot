package ma.ecommerce.mappers;

import ma.ecommerce.dto.ContactDTO;
import ma.ecommerce.entities.Contact;
import java.util.List;
import java.util.stream.Collectors;

public class ContactMapper {
    public static ContactDTO contactToDTO(Contact contact){
        if(contact == null ) return null;
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setName(contact.getName());
        contactDTO.setEmailAddress(contact.getEmailAddress());
        contactDTO.setNumberPhone(contact.getNumberPhone());
        contactDTO.setMessage(contact.getMessage());
        return contactDTO;
    }

    public static Contact dtoToContact(ContactDTO contactDTO){
        if(contactDTO == null ) return null;
        Contact contact = new Contact();
        contact.setId(contactDTO.getId());
        contact.setName(contactDTO.getName());
        contact.setEmailAddress(contactDTO.getEmailAddress());
        contact.setNumberPhone(contactDTO.getNumberPhone());
        contact.setMessage(contactDTO.getMessage());
        return contact;
    }

    public static List<Contact> contactsDtoToContacts(List<ContactDTO> contactDTOList) {
        if (contactDTOList == null || contactDTOList.isEmpty()) return null;
        return contactDTOList.stream().map(ContactMapper::dtoToContact)
                .collect(Collectors.toList());
    }

    public static List<ContactDTO> contactsToContactsDTO(List<Contact> contactList){
        if(contactList == null || contactList.isEmpty())return null;
        return contactList.stream().map(ContactMapper::contactToDTO)
                .collect(Collectors.toList());
    }
}
