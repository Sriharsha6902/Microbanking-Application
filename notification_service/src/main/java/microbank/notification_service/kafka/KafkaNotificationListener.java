package microbank.notification_service.kafka;

import lombok.RequiredArgsConstructor;
import microbank.common.kafka.KafkaTopic;
import microbank.notification_service.dto.MailInfo;
import microbank.notification_service.service.NotificationServ;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@RequiredArgsConstructor
public class KafkaNotificationListener {

    private final NotificationServ notificationServ;

    @KafkaListener(topics = "account-events")
    public void accountListener(KafkaTopic kafkaTopic) {
        MailInfo mailInfo = MailInfo.builder()
                .subject("Test")
                .sendTo(kafkaTopic.getEmail())
                .messageText("test mail")
                .build();
        notificationServ.sendEmail(mailInfo);
    }

    @KafkaListener(topics = "transaction-events")
    public void transactionListener(KafkaTopic kafkaTopic) {
        MailInfo mailInfo = MailInfo.builder()
                .subject("Test")
                .sendTo(kafkaTopic.getEmail())
                .messageText("test mail")
                .build();
        notificationServ.sendEmail(mailInfo);
    }
}
