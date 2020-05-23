package io.swagger.api;

import io.swagger.service.TransactionApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.threeten.bp.LocalDate;
import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T09:28:40.437Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    private TransactionApiService transactionApiService;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Transaction> getTransaction(@ApiParam(value = "Id of the transaction to return", required = true) @PathVariable("transactionId") Long transactionId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
             return new ResponseEntity<Transaction>(objectMapper.readValue(objectMapper.writeValueAsString(transactionApiService.getTransaction(transactionId)), Transaction.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Transaction>> searchTansaction(@ApiParam(value = "") @Valid @RequestParam(value = "username", required = false) String username
,@ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) String userId
,@ApiParam(value = "") @Valid @RequestParam(value = "IBAN", required = false) String IBAN
,@ApiParam(value = "") @Valid @RequestParam(value = "transactionSearchDateStart", required = false) LocalDate transactionSearchDateStart
,@ApiParam(value = "") @Valid @RequestParam(value = "transactionSearchDateEnd", required = false) LocalDate transactionSearchDateEnd
,@ApiParam(value = "") @Valid @RequestParam(value = "transactionAmount", required = false) Double transactionAmount
,@ApiParam(value = "") @Valid @RequestParam(value = "MaxNumberOfResults", required = false) Integer maxNumberOfResults
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try
            {
                List<Transaction> myList = new ArrayList<Transaction>();
                if(username != null)
                {
                    myList.add(transactionApiService.getTransactionsFromName(username));
                }
                if(userId != null)
                {
                    // Account/userId is not mentioned in Transaction
            //        myList.add(transactionApiService.getTransactionsFromUserId(userId));
                }
                if(IBAN != null)
                {
                    for(Transaction a : transactionApiService.getTransactionsFromIBAN(IBAN))
                    {
                        myList.add(a);
                    }
                }
                if((transactionSearchDateStart != null) || (transactionSearchDateEnd != null))
                {
                    myList.add(transactionApiService.getTransactionsFromDates(transactionSearchDateStart, transactionSearchDateEnd));
                }
                if(transactionAmount != null)
                {
                    List<Transaction> matches = transactionApiService.getTransactionsFromAmount(transactionAmount);
                    for(Transaction a : matches) {
                        myList.add(a);
                    }
                }
                if(maxNumberOfResults != null)
                {
                    myList.subList(0, maxNumberOfResults);
                }
                if(myList.size() == 0)
                {
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

    public ResponseEntity<Transaction> transferFunds(@ApiParam(value = "") @Valid @RequestBody Transaction body)
    {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json"))
        {
            try {
                Transaction NEWtransaction = transactionApiService.makeTransaction(mapTransactionData(body));
                return new ResponseEntity<Transaction>(objectMapper.readValue(objectMapper.writeValueAsString(NEWtransaction), Transaction.class), HttpStatus.OK);
        }catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }else{
            return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED); }
    }

    protected Transaction mapTransactionData(Transaction body)
    {
        Transaction transaction = new Transaction(body.getTransactionId(), body.getIbanSender(), body.getIbanReceiver(), body.getNameSender(), body.getTransactionDate(), body.getTransferAmount());
        return transaction;
    }

}
