package microbank.common.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaTopic {
    private String name;
    private String message;
    private String topic;
    private Map<String, Object> data;
}
