package com.practice.file_management_sys.service;

public interface MailService {
    void sendSimpleEmail(String to, String subject, String content);
    void sendHtmlMail(String to, String subject, String content);
    void sendFileMail(String to, String subject, String content, String filepath);
    void sendTemplateMail(String to, String subject);
}
