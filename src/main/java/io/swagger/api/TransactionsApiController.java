package io.swagger.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.dao.RepositoryApiKey;
import io.swagger.model.ApiKey;
import io.swagger.model.Transaction;
import io.swagger.service.AccountApiService;
import io.swagger.service.ApiKeyService;
import io.swagger.service.TransactionApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
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
import java.util.List;

import static org.springframework.http.ResponseEntity.status;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-02T09:24:14.507Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private TransactionApiService transactionApiService;

    private AccountApiService accountApiService;

    private ApiKeyService apikeyService;


    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(TransactionApiService transactionApiService, ObjectMapper objectMapper, HttpServletRequest request, ApiKeyService apikeyService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.transactionApiService = transactionApiService;
        this.apikeyService = apikeyService;
    }

    public ResponseEntity<List<Transaction>> getTransactions(@Min(0L) @ApiParam(value = "", required = true, allowableValues = "") @PathVariable("transactionId") Long transactionId
    ) {
        String accept = request.getHeader("Accept");
        String authKey = request.getHeader("X-AUTHENTICATION");

        if(apikeyService.findById(authKey) != null){

            if (accept != null && accept.contains("application/json")) {
                try {
                    List<Transaction> myList = (List<Transaction>) transactionApiService.getTransaction(transactionId);
                    return new ResponseEntity<List<Transaction>>(objectMapper.readValue(objectMapper.writeValueAsString(myList), List.class), HttpStatus.OK);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        else{ return new ResponseEntity<>(HttpStatus.FORBIDDEN); }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Transaction> getTransaction(@Min(0L) @ApiParam(value = "", required = true, allowableValues = "") @PathVariable("transactionId") Long transactionId
    ) {
        String accept = request.getHeader("Accept");
        String authKey = request.getHeader("X-AUTHENTICATION");
        if(apikeyService.findById(authKey) != null) {
        Transaction transaction = transactionApiService.getTransaction(transactionId);
        if (accept != null) {
            try {
                return status(HttpStatus.OK).body(transaction);
                //     return new ResponseEntity<Transaction>(objectMapper.readValue(objectMapper.writeValueAsString(transactionApiService.getTransaction(transactionId)), Transaction.class), HttpStatus.OK);
            } catch (IllegalArgumentException iae) {
                return status(HttpStatus.NOT_FOUND).build();
            }
        }
        }else{ return new ResponseEntity<>(HttpStatus.FORBIDDEN); }
        return status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    public ResponseEntity<List<Transaction>> searchTansaction
            (@ApiParam(value = "") @Valid @RequestParam(value = "nameSender", required = false) String nameSender,
             @ApiParam(value = "") @Valid @RequestParam(value = "transactionId", required = false) Long transactionId,
             @ApiParam(value = "") @Valid @RequestParam(value = "IBAN", required = false) String IBAN,
             @ApiParam(value = "") @Valid @RequestParam(value = "transferAmount", required = false) Double transferAmount,
             @ApiParam(value = "") @Valid @RequestParam(value = "MaxNumberOfResults", required = false) Integer maxNumberOfResults) {
        String accept = request.getHeader("Accept");
        String authKey = request.getHeader("X-AUTHENTICATION");
        if(apikeyService.findById(authKey) != null) {
            if (accept != null) {
                try {
                    List<Transaction> myList = transactionApiService.FindAllMatches(nameSender, transactionId, IBAN, transferAmount, maxNumberOfResults);
                    return new ResponseEntity<List<Transaction>>(objectMapper.readValue(objectMapper.writeValueAsString(myList), List.class), HttpStatus.OK);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }else{ return new ResponseEntity<>(HttpStatus.FORBIDDEN); }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Transaction> transferFunds(@ApiParam(value = "Transaction object", required = true) @Valid @RequestBody Transaction body) {
        String accept = request.getHeader("Accept");
        HttpHeaders responseHeaders = new HttpHeaders();
        String authKey = request.getHeader("X-AUTHENTICATION");
        if(apikeyService.findById(authKey) != null) {
            if (accept != null && accept.contains("application/json")) {
                try {
                    transactionApiService.checkValidTransaction(body);
                    Transaction transaction = new Transaction(body.getIbanSender(), body.getIbanReceiver(), body.getNameSender(), body.getTransferAmount());
                    Boolean transSucces = transactionApiService.makeTransaction(transaction);
                    if (transSucces == true) {
                        return new ResponseEntity<Transaction>(objectMapper.readValue(objectMapper.writeValueAsString(transaction), Transaction.class), HttpStatus.CREATED);
                    } else {
                        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((JsonNode) objectMapper.createObjectNode().put("message", "The transaction was not succesful"));
                        return responseEntity;
                    }
                } catch (Exception e) {
                    ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body((JsonNode) objectMapper.createObjectNode().put("message", e.getMessage()));
                    return responseEntity;
                }
            }
        }else{ return new ResponseEntity<>(HttpStatus.FORBIDDEN); }

        return null;
    }

    protected Transaction mapTransactionData(Transaction body) {
        Transaction transaction = new Transaction(body.getIbanSender(), body.getIbanReceiver(), body.getNameSender(), body.getTransferAmount());
        return transaction;
    }
}

