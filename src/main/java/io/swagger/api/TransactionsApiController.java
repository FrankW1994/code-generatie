package io.swagger.api;

import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.TransactionApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-02T09:24:14.507Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private TransactionApiService transactionApiService;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(TransactionApiService transactionApiService, ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.transactionApiService = transactionApiService;
    }

    public ResponseEntity<List<Transaction>> getTransactions(@Min(0L)@ApiParam(value = "",required=true, allowableValues="") @PathVariable("accountId") Long accountId
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
                return ResponseEntity.status(HttpStatus.OK).body(transaction);
                //     return new ResponseEntity<Transaction>(objectMapper.readValue(objectMapper.writeValueAsString(transactionApiService.getTransaction(transactionId)), Transaction.class), HttpStatus.OK);
            } catch (IllegalArgumentException iae) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    public ResponseEntity<Transaction> transferFunds(@ApiParam(value = "") @Valid @RequestBody Transaction body
            , @ApiParam(value = "") @RequestHeader(value = "transaction", required = false) Object transaction
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Transaction NEWtransaction = transactionApiService.makeTransaction(mapTransactionData(body));
                return ResponseEntity.status(HttpStatus.CREATED).body(NEWtransaction);
                //  return new ResponseEntity<Transaction>(objectMapper.readValue(objectMapper.writeValueAsString(NEWtransaction), Transaction.class), HttpStatus.CREATED);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    protected Transaction mapTransactionData(Transaction body) {
        Transaction transaction = new Transaction(body.getTransactionId(), body.getIbanSender(), body.getIbanReceiver(), body.getNameSender(), body.getTransactionDate(), body.getTransferAmount());
        return transaction;
    }

        public ResponseEntity<List<Transaction>> searchTansaction
        (@ApiParam(value = "") @Valid @RequestParam(value = "username", required = false) String username,
         @ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) String userId,
         @ApiParam(value = "") @Valid @RequestParam(value = "IBAN", required = false) String IBAN,
         @ApiParam(value = "") @Valid @RequestParam(value = "transactionSearchDateStart", required = false) LocalDate transactionSearchDateStart,
         @ApiParam(value = "") @Valid @RequestParam(value = "transactionSearchDateEnd", required = false) LocalDate transactionSearchDateEnd,
         @ApiParam(value = "") @Valid @RequestParam(value = "transactionAmount", required = false) Double transactionAmount,
         @ApiParam(value = "") @Valid @RequestParam(value = "MaxNumberOfResults", required = false) Integer maxNumberOfResults)
        {
            String accept = request.getHeader("Accept");
            if (accept != null) {
                try {
                    List<Transaction> myList = new ArrayList<Transaction>();
                    if (username != null) {
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
                    }
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

}
