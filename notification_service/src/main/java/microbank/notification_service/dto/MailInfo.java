package microbank.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MailInfo {

    private String sendTo;
    private String subject;
    private String messageText;

}