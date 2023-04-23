package com.adminmodule.smtp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class EmailController {

    private final EmailService emailService;
    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailDetails emailDetails) {
        return ResponseEntity.ok(emailService.sendEmail(emailDetails));
    }

    @PostMapping("/send-email-with-attachment")
    public ResponseEntity<?> sendEmailWithAttachment(@RequestBody EmailDetails emailDetails) {
        return ResponseEntity.ok(emailService.sendEmailWithAttachment(emailDetails));
    }

}
