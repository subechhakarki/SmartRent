package Controller;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MailService {
    
    private static final Properties config = new Properties();

    static {
        // Default configurations
        config.put("mail.smtp.host", "localhost");
        config.put("mail.smtp.port", "1025");
        config.put("mail.smtp.auth", "false");
        config.put("mail.smtp.starttls.enable", "false");
        config.put("mail.from.email", "noreply@smartrent.com");
        config.put("mail.smtp.username", "");
        config.put("mail.smtp.password", "");

        // Try loading from properties file
        File configFile = new File("mail_config.properties");
        if (configFile.exists()) {
            try (FileInputStream in = new FileInputStream(configFile)) {
                config.load(in);
                System.out.println("SMTP configuration loaded successfully from mail_config.properties.");
            } catch (IOException e) {
                System.err.println("Could not load SMTP properties, using default localhost settings: " + e.getMessage());
            }
        } else {
            System.out.println("mail_config.properties not found, using default localhost SMTP settings.");
        }
    }
    
    public static boolean sendOTP(String recipientEmail, String otpCode) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", config.getProperty("mail.smtp.host", "localhost"));
        properties.put("mail.smtp.port", config.getProperty("mail.smtp.port", "1025"));
        properties.put("mail.smtp.auth", config.getProperty("mail.smtp.auth", "false"));
        properties.put("mail.smtp.starttls.enable", config.getProperty("mail.smtp.starttls.enable", "false"));
        
        // Setup session
        Session session;
        boolean auth = "true".equalsIgnoreCase(config.getProperty("mail.smtp.auth", "false"));
        
        if (auth) {
            final String username = config.getProperty("mail.smtp.username");
            final String password = config.getProperty("mail.smtp.password");
            
            session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        } else {
            session = Session.getInstance(properties);
        }
        
        try {
            Message message = new MimeMessage(session);
            String fromEmail = config.getProperty("mail.from.email", "noreply@smartrent.com");
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipientEmail)
            );
            message.setSubject("SmartRent - Password Reset Verification Code");
            
            String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 20px; border: 1px solid #eee; border-radius: 5px; max-width: 600px;'>"
                    + "<h2 style='color: #006666;'>SmartRent Password Reset</h2>"
                    + "<p>Hello,</p>"
                    + "<p>We received a request to reset your password. Use the following 6-digit verification code to proceed:</p>"
                    + "<div style='font-size: 24px; font-weight: bold; letter-spacing: 2px; color: #006666; margin: 20px 0;'>" + otpCode + "</div>"
                    + "<p>This code is valid for <strong>5 minutes</strong>. If you did not request a password reset, please ignore this email.</p>"
                    + "<hr style='border: none; border-top: 1px solid #eee;' />"
                    + "<p style='font-size: 12px; color: #888;'>This is an automated system email, please do not reply directly.</p>"
                    + "</div>";
            
            message.setContent(htmlContent, "text/html");
            
            Transport.send(message);
            System.out.println("Email sent successfully to " + recipientEmail);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed to send email: " + e.getMessage());
            return false;
        }
    }
}
