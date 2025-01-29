//package com.example.springcrudmvc.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    // Send email
//    public void sendEmail(String to, String subject, String messageBody) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setTo(to); // Recipient
//        helper.setSubject(subject); // Email subject
//        helper.setText(messageBody, true); // Email body (true = HTML content)
//
//        mailSender.send(message); // Send the email
//    }
//}
