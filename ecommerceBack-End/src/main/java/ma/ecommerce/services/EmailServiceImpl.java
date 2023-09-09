package ma.ecommerce.services;

import ma.ecommerce.dto.EmailDTO;
import ma.ecommerce.dto.UserDTO;
import ma.ecommerce.entities.User;
import ma.ecommerce.exceptions.BusinessException;
import ma.ecommerce.exceptions.RecordNotFoundException;
import ma.ecommerce.mappers.UserMapper;
import ma.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    @Value("${emailAdmin}")
    private String emailAdmin;


    @Override
    @Async
    public void sendCodeByMail(EmailDTO emailDTO) {
        UserDTO userDTo = UserMapper.userToDTO(userRepository.findUsersByEmail(emailDTO.getTo()));
        emailDTO.setCode("Your Password Is "+ userDTo.getPassword());
        if (userDTo.getEmail() != null){
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(emailAdmin);
            simpleMailMessage.setTo(userDTo.getEmail());
            simpleMailMessage.setSubject("Reset Your Password !");
            simpleMailMessage.setText(emailDTO.getCode());
            javaMailSender.send(simpleMailMessage);
        }else{
            throw new RuntimeException("No User with email :  " + emailDTO.getTo());
        }
    }

}
