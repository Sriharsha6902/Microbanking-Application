package microbank.common.util;

public class SnowFlakeGen {

    private static final long EPOCH = 1735689600000L;

    private static final long NODE_ID_BITS = 10L;
    private static final long SEQUENCE_BITS = 12L;

    private static final long MAX_NODE_ID = (1L << NODE_ID_BITS) - 1;
    private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;

    private static final long NODE_ID_SHIFT = SEQUENCE_BITS;
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + NODE_ID_BITS;

    private final long nodeId;

    private long lastTimestamp = -1L;
    private long sequence = 0L;

    public SnowFlakeGen(long nodeId) {
        if (nodeId < 0 || nodeId > MAX_NODE_ID) {
            throw new IllegalArgumentException("nodeId must be between 0 and " + MAX_NODE_ID);
        }
        this.nodeId = nodeId;
    }

    public synchronized long createEntityId() {
        long currentTimestamp = currentTime();

        if (currentTimestamp < lastTimestamp) {
            long diff = lastTimestamp - currentTimestamp;
            throw new IllegalStateException("Clock moved backwards. Refusing for " + diff + " ms");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;

            if (sequence == 0) {
                currentTimestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        return ((currentTimestamp - EPOCH) << TIMESTAMP_SHIFT)
                | (nodeId << NODE_ID_SHIFT)
                | sequence;
    }

//    public synchronized long createRefId() {
//        long currentTimestamp = currentTime();
//
//        if (currentTimestamp < lastTimestamp) {
//            long diff = lastTimestamp - currentTimestamp;
//            throw new IllegalStateException("Clock moved backwards. Refusing for " + diff + " ms");
//        }
//
//        if (currentTimestamp == lastTimestamp) {
//            sequence = (sequence + 1) & MAX_SEQUENCE;
//
//            if (sequence == 0) {
//                currentTimestamp = waitNextMillis(lastTimestamp);
//            }
//        } else {
//            sequence = 0L;
//        }
//
//        lastTimestamp = currentTimestamp;
//
//        return ((currentTimestamp - EPOCH) << TIMESTAMP_SHIFT)
//                | (nodeId << NODE_ID_SHIFT)
//                | sequence;
//    }

    private long waitNextMillis(long lastTimestamp) {
        long ts = currentTime();
        while (ts <= lastTimestamp) {
            ts = currentTime();
        }
        return ts;
    }

    private long currentTime() {
        return System.currentTimeMillis();
    }
}
