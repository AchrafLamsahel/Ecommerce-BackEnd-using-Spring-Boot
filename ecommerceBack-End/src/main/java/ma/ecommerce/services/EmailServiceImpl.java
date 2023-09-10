package ma.ecommerce.services;

import ma.ecommerce.dto.UserDTO;
import ma.ecommerce.mappers.UserMapper;
import ma.ecommerce.repositories.UserRepository;
import ma.ecommerce.util.UserCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IUserService userService;
    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${emailAdmin}")
    private String emailAdmin;

    @Override
    @Async
    public boolean sendCodeByMail(String emailDTO) {
        UserDTO userDTo = UserMapper.userToDTO(userRepository.findUsersByEmail(emailDTO));
        if (userDTo.getEmail() != null){
            String newCode = UserCode.getCode();
            userDTo.setCode(newCode);
            userService.editUser(userDTo);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(emailAdmin);
            simpleMailMessage.setTo(userDTo.getEmail());
            simpleMailMessage.setSubject("Validation Account");
            simpleMailMessage.setText("Le Code de Validation Is : "+ userDTo.getCode());
            javaMailSender.send(simpleMailMessage);
            return true;
        }else{
            throw new RuntimeException("No User with email :  " + emailDTO);
        }
    }
    /**
    @Override
    public UserDTO resetPassword(String code, String newPassword){
        UserDTO userDTo = UserMapper.userToDTO(userRepository.findUsersByCode(code));
        if(userDTo != null) {
            if(userDTo.getCode().equals(code)){
                String pss= bCryptPasswordEncoder.encode(newPassword);
                userDTo.setPassword(pss);
                String newCode = UserCode.getCode();
                userDTo.setCode(newCode);
                userService.editUser(userDTo);
            }else{
                throw new RuntimeException("Failed Code with  :  " + userDTo.getCode());
            }
        }else  {
            throw new RuntimeException("User Don' Found With Code   :  " + userDTo.getCode());
        }
        return userDTo;
    }
    */

}
