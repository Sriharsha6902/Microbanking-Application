package microbank.notification_service.kafka;

import lombok.RequiredArgsConstructor;
import microbank.common.kafka.KafkaTopic;
import microbank.notification_service.controller.NotificationController;
import microbank.notification_service.dto.MailInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@RequiredArgsConstructor
@KafkaListener(topics = "account")
public class NotificationKafkaListener {

    private final NotificationController notificationController;

    @KafkaHandler
    public void accountListener(KafkaTopic kafkaTopic) {
        MailInfo mailInfo = MailInfo.builder()
                .subject("Test")
                .sendTo(kafkaTopic.getEmail())
                .messageText("test mail")
                .build();
        notificationController.sendEmail(mailInfo);
    }
}
