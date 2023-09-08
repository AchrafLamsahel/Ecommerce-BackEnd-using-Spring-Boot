package ma.ecommerce.services;

import ma.ecommerce.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${emailAdmin}")
    private String emailAdmin;


    @Override
    @Async
    public void sendCodeByMail(EmailDTO emailDTO) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailAdmin);
        simpleMailMessage.setTo(emailDTO.getTo());
        simpleMailMessage.setSubject("Code Active");
        simpleMailMessage.setText(emailDTO.getCode());
        javaMailSender.send(simpleMailMessage);
    }

}
