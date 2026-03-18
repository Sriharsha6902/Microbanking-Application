package microbank.notification_service.controller;

import lombok.RequiredArgsConstructor;
import microbank.notification_service.dto.MailInfo;
import microbank.notification_service.service.NotificationServ;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationServ emailService;

    public void sendEmail(MailInfo sendTo) {
        if (sendTo != null && !sendTo.getSendTo().trim().isEmpty()) {
            try {
                emailService.sendEmail(sendTo);
                System.out.println("Mail Sent successfully. Please check your mail.");
            } catch (Exception e) {
                ResponseEntity.internalServerError().body(e.getMessage());
            }
        } else {
            throw new RuntimeException("Please provide valid email address.");
        }

    }
}
