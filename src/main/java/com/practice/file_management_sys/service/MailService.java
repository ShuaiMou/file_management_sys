package com.practice.file_management_sys.service;

public interface MailService {
    void sendVerificationCodeEmail(String to);
    void sendWelcomeEmail(String to, String subject, String content);
}
