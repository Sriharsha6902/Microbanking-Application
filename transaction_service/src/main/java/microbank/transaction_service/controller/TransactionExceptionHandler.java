package microbank.transaction_service.controller;

import microbank.transaction_service.dto.TransactionResDto;
import microbank.transaction_service.enums.TransactionStatus;
import microbank.transaction_service.exceptions.TransactionCreationException;
import microbank.transaction_service.exceptions.TransactionProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransactionExceptionHandler {

    @ExceptionHandler(TransactionCreationException.class)
    public ResponseEntity<String> handleTransactionCreationException(TransactionCreationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionProcessingException.class)
    public ResponseEntity<TransactionResDto> handleTransactionProcessingException(TransactionProcessingException ex) {
        TransactionResDto transactionResDto = TransactionResDto.builder()
                .status(TransactionStatus.FAILED)
                .failureReason(ex.getMessage())
                .build();
        return new ResponseEntity<>(transactionResDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
