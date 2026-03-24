package microbank.accountservice.controller;

import lombok.RequiredArgsConstructor;
import microbank.accountservice.dtos.AccountDto;
import microbank.accountservice.entity.AccountEntity;
import microbank.accountservice.service.AccountServ;
import microbank.common.enums.CommonEnums;
import microbank.common.kafka.KafkaTopic;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountServ service;
    private final KafkaTemplate<String, KafkaTopic> kafkaTemplate;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountEntity accountEntity) {
        ResponseEntity<AccountDto> responseEntity = new ResponseEntity<>(this.service.createAccount(accountEntity), HttpStatus.CREATED);
        KafkaTopic kafkaTopic = new KafkaTopic();
        kafkaTopic.setEmail(accountEntity.getEmail());
        kafkaTopic.setMessage(CommonEnums.ACCOUNT_CREATED.value);
        kafkaTopic.setTopic(CommonEnums.ACCOUNT_TOPIC.value);
        this.sendKafkaMessage(kafkaTopic);
        return responseEntity;

    }

    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountEntity accountEntity) {
        ResponseEntity<AccountDto> responseEntity = new ResponseEntity<>(this.service.updateAccount(accountEntity), HttpStatus.OK);
        KafkaTopic kafkaTopic = new KafkaTopic();
        kafkaTopic.setEmail(accountEntity.getEmail());
        kafkaTopic.setMessage(CommonEnums.ACCOUNT_UPDATED.name());
        kafkaTopic.setTopic(CommonEnums.ACCOUNT_TOPIC.name());
        this.sendKafkaMessage(kafkaTopic);
        return responseEntity;
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> deleteAccount(@RequestBody AccountEntity accountEntity) {
        ResponseEntity<AccountDto> responseEntity = new ResponseEntity<>(this.service.deleteAccount(accountEntity), HttpStatus.OK);
        KafkaTopic kafkaTopic = new KafkaTopic();
        kafkaTopic.setEmail(accountEntity.getEmail());
        kafkaTopic.setMessage(CommonEnums.ACCOUNT_DELETED.name());
        kafkaTopic.setTopic(CommonEnums.ACCOUNT_TOPIC.name());
        this.sendKafkaMessage(kafkaTopic);
        return responseEntity;
    }

    @GetMapping(value = "fetch", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> getAccount(@RequestBody AccountEntity accountEntity) {
        return new ResponseEntity<>(this.service.getAccount(accountEntity), HttpStatus.OK);
    }

    private void sendKafkaMessage(KafkaTopic kafkaTopic) {
        kafkaTemplate.send("account-events", kafkaTopic);
    }
}
