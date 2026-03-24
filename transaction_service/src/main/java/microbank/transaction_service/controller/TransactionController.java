package microbank.transaction_service.controller;


import lombok.RequiredArgsConstructor;
import microbank.common.kafka.KafkaTopic;
import microbank.transaction_service.dto.TransactionReqDto;
import microbank.transaction_service.dto.TransactionResDto;
import microbank.transaction_service.entity.TransactionEntity;
import microbank.transaction_service.service.TransactionService;
import microbank.transaction_service.util.TransactionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/transaction")
@RequiredArgsConstructor
@RestController
public class TransactionController {
    private final KafkaTemplate<String, KafkaTopic> kafkaTemplate;

    private final TransactionUtils transactionUtils;

    private final TransactionService transactionService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createTransaction(@RequestParam String idempotencyKey, @RequestBody TransactionReqDto transactionReqDto) {
        TransactionEntity transactionEntity = transactionUtils.mapFromDto(transactionReqDto);
        transactionEntity.setIdempotencyKey(idempotencyKey);
        this.transactionService.createTransaction(transactionEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/transfer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResDto> transferAmount(@RequestParam String idempotencyKey, @RequestBody TransactionReqDto transactionReqDto) {
        TransactionEntity transactionEntity = transactionUtils.mapFromDto(transactionReqDto);
        transactionEntity.setIdempotencyKey(idempotencyKey);
        ResponseEntity<TransactionResDto> response = new ResponseEntity<>(this.transactionService.transferAmount(transactionEntity), HttpStatus.OK);
        this.sendEvent(transactionEntity);
        return response;
    }

    @PostMapping(value = "/withdraw", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResDto> withdrawAmount(@RequestParam String idempotencyKey, @RequestBody TransactionReqDto transactionReqDto) {
        TransactionEntity transactionEntity = transactionUtils.mapFromDto(transactionReqDto);
        transactionEntity.setIdempotencyKey(idempotencyKey);
        ResponseEntity<TransactionResDto> response = new ResponseEntity<>(this.transactionService.withdrawAmount(transactionEntity), HttpStatus.OK);
        this.sendEvent(transactionEntity);
        return response;
    }

    @PostMapping(value = "/deposit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResDto> depositAmount(@RequestParam String idempotencyKey, @RequestBody TransactionReqDto transactionReqDto) {
        TransactionEntity transactionEntity = transactionUtils.mapFromDto(transactionReqDto);
        transactionEntity.setIdempotencyKey(idempotencyKey);
        ResponseEntity<TransactionResDto> response = new ResponseEntity<>(this.transactionService.depositAmount(transactionEntity), HttpStatus.OK);
        this.sendEvent(transactionEntity);
        return response;
    }

    private void sendEvent(TransactionEntity transactionEntity) {
        KafkaTopic kafkaTopic = new KafkaTopic();
        kafkaTopic.setName(transactionEntity.getTransactionId());
        kafkaTopic.setMessage(transactionEntity.getStatus().getValue());
        kafkaTemplate.send("transaction-events", kafkaTopic);
    }
}
