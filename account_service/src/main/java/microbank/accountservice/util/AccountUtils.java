package microbank.accountservice.util;

import microbank.common.util.SnowFlakeGen;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountUtils {
    @Value("${snowflake.node-id}")
    private long nodeId;

    private final SnowFlakeGen snowFlakeGen = new SnowFlakeGen(nodeId);

    public String createAccountId() {
        return "ACCOUNT" + snowFlakeGen.createEntityId();
    }
}
