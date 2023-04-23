package com.adminmodule.smtp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Service
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String from;
    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public String sendEmail(EmailDetails emailDetails) {
        try{
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(emailDetails.getTo());
            msg.setFrom("oscs.spm@gmail.com");
            msg.setSubject(emailDetails.getSubject());
            msg.setText(emailDetails.getBody());
            javaMailSender.send(msg);
            return "Email sent successfully";
        }catch (Exception e){
            return "Email not sent " + e.getMessage() + " " + e.getStackTrace() + " " + e.getCause() + " " + e.getLocalizedMessage();
        }
    }

    @Override
    public String sendEmailWithAttachment(EmailDetails emailDetails) {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try{
            helper = new MimeMessageHelper(msg, true);
            helper.setTo(emailDetails.getTo());
            helper.setFrom(from);
            helper.setSubject(emailDetails.getSubject());
            helper.setText(emailDetails.getBody());

            FileSystemResource file
                    = new FileSystemResource(emailDetails.getAttachmentPath());
            helper.addAttachment(file.getFilename(), file);
            javaMailSender.send(msg);
            return "Email sent successfully";
        }catch (MessagingException e){
            return "Email not sent";
        }
    }
}
