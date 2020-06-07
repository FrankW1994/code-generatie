package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.service.AccountApiService;
import io.swagger.service.TransactionApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-02T09:24:14.507Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private TransactionApiService transactionApiService;

    @Autowired
    private AccountApiService accountApiService;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(TransactionApiService transactionApiService, ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.transactionApiService = transactionApiService;
    }

    public ResponseEntity<List<Transaction>> getTransactions(@Min(0L) @ApiParam(value = "", required = true, allowableValues = "") @PathVariable("accountId") Long accountId
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Transaction>>(objectMapper.readValue("[ {\n  \"nameSender\" : \"nameSender\",\n  \"transferAmount\" : 6.027456183070403,\n  \"transactionDate\" : \"transactionDate\",\n  \"ibanSender\" : \"ibanSender\",\n  \"ibanReceiver\" : \"ibanReceiver\",\n  \"transactionId\" : 0\n}, {\n  \"nameSender\" : \"nameSender\",\n  \"transferAmount\" : 6.027456183070403,\n  \"transactionDate\" : \"transactionDate\",\n  \"ibanSender\" : \"ibanSender\",\n  \"ibanReceiver\" : \"ibanReceiver\",\n  \"transactionId\" : 0\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Transaction> getTransaction(@Min(0L) @ApiParam(value = "", required = true, allowableValues = "") @PathVariable("transactionId") Long transactionId
    ) {
        String accept = request.getHeader("Accept");
        Transaction transaction = transactionApiService.getTransaction(transactionId);
        if (accept != null) {
            try {
                return status(HttpStatus.OK).body(transaction);
                //     return new ResponseEntity<Transaction>(objectMapper.readValue(objectMapper.writeValueAsString(transactionApiService.getTransaction(transactionId)), Transaction.class), HttpStatus.OK);
            } catch (IllegalArgumentException iae) {
                return status(HttpStatus.NOT_FOUND).build();
            }
        }
        return status(HttpStatus.NOT_IMPLEMENTED).build();
    }


    public ResponseEntity<List<Transaction>> searchTansaction
            (@ApiParam(value = "") @Valid @RequestParam(value = "username", required = false) String username,
             @ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) String userId,
             @ApiParam(value = "") @Valid @RequestParam(value = "IBAN", required = false) String IBAN,
             @ApiParam(value = "") @Valid @RequestParam(value = "transactionSearchDateStart", required = false) LocalDate transactionSearchDateStart,
             @ApiParam(value = "") @Valid @RequestParam(value = "transactionSearchDateEnd", required = false) LocalDate transactionSearchDateEnd,
             @ApiParam(value = "") @Valid @RequestParam(value = "transactionAmount", required = false) Double transactionAmount,
             @ApiParam(value = "") @Valid @RequestParam(value = "MaxNumberOfResults", required = false) Integer maxNumberOfResults) {
        String accept = request.getHeader("Accept");
        if (accept != null) {
            try {
                List<Transaction> myList = new ArrayList<Transaction>();
/*                if (username != null) {
                    for (Transaction t : transactionApiService.getTransactionsFromName(username)) {
                        myList.add(t);
                    }
                }
                if (userId != null) {
                    for(Transaction t : transactionApiService.getTransactionsFromUserId(userId))
                    { myList.add(t); }
                }
                if (IBAN != null) {
                    for(Transaction t : transactionApiService.getTransactionsFromIBAN(IBAN))
                    {
                        myList.add(t);
                    }
                }
                if ((transactionSearchDateStart != null) || (transactionSearchDateEnd != null)) {
                    //                myList.add(transactionApiService.getTransactionsFromDates(transactionSearchDateStart, transactionSearchDateEnd));
                }
                if (transactionAmount != null) {
                    List<Transaction> transactions = transactionApiService.getTransactionsFromAmount(transactionAmount);
                    for(Transaction t : transactions) {
                        myList.add(t);
                    }
                }
                if (maxNumberOfResults != null) {
                    myList.subList(0, maxNumberOfResults);
                }*/
                if (myList.size() == 0) {
                    myList = transactionApiService.getTransactions();
                }
                return new ResponseEntity<List<Transaction>>(objectMapper.readValue(objectMapper.writeValueAsString(myList), List.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Transaction> transferFunds(@ApiParam(value = "Transaction object", required = true) @Valid @RequestBody Transaction body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {

                Account accountSender = accountApiService.getAccountFromIBAN(body.getIbanSender());
                Account accountReceiver = accountApiService.getAccountFromIBAN(body.getIbanReceiver());
                if ((accountSender == null) || accountReceiver == null) {
                    return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
                //  Account sender does not exists!
                }
                //	The maximum amount per transaction cannot be higher than a predefined number, referred to a transaction limit
                if ((body.getTransferAmount() < 0) || (body.getTransferAmount() >= 700)) {
                    return new ResponseEntity<Transaction>(HttpStatus.BAD_REQUEST);
                //    Transaction must be between 0 and 700
                } else if (body.getTransferAmount() > accountSender.getBalance()) {
                    return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
               //     Account has nog enough money
                }

                Transaction transaction = new Transaction(body.getIbanSender(), body.getIbanReceiver(), body.getNameSender(), body.getTransferAmount());
                transactionApiService.makeTransaction(transaction);
                return ResponseEntity.status(HttpStatus.CREATED).body(transaction);

            } catch (Exception e) {
                    log.error("Couldn't serialize response for content type application/json");
                    return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }
        else { return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED); }
    }

    protected Transaction mapTransactionData(Transaction body) {
        Transaction transaction = new Transaction(body.getIbanSender(), body.getIbanReceiver(), body.getNameSender(), body.getTransferAmount());
        return transaction;
    }
}

