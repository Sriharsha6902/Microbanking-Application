package microbank.notification_service.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import microbank.notification_service.constants.Constants;
import microbank.notification_service.dto.MailInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServ {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String username;

    public void sendEmail(MailInfo mailInfo) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {

            mimeMessageHelper.setTo(mailInfo.getSendTo());
            mimeMessageHelper.setSubject(mailInfo.getSubject());
            mimeMessageHelper.setFrom(this.username);
            mimeMessageHelper.setText(Constants.MAIL_DEMO_TEXT_HTML, true);

            new Thread(() -> {

                System.out.println("Sending mail...");
                javaMailSender.send(mimeMessage);
                System.out.println("Mail sent successfully...");
            }).start();

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
