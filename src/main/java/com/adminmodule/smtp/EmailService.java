package com.adminmodule.smtp;

public interface EmailService {
    String sendEmail(EmailDetails emailDetails);
    String sendEmailWithAttachment(EmailDetails emailDetails);
}
