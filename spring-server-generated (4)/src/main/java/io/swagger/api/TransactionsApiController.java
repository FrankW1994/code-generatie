package io.swagger.api;

import org.threeten.bp.LocalDate;
import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T09:28:40.437Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Transaction> getTransaction(@Min(0L)@ApiParam(value = "",required=true, allowableValues="") @PathVariable("transactionId") Long transactionId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Transaction>(objectMapper.readValue("{\n  \"ibanSender\" : \"NL00RABO0000000000\",\n  \"ibanReceiver\" : \"NL00INGB0000000000\",\n  \"transactionId\" : 12345678,\n  \"nameSender\" : \"Frank Wester\",\n  \"transactionDate\" : \"06-05-2020\",\n  \"transferAmount\" : 20\n}", Transaction.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Transaction>> searchTansaction(@ApiParam(value = "") @Valid @RequestParam(value = "username", required = false) String username
,@ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) String userId
,@ApiParam(value = "") @Valid @RequestParam(value = "IBAN", required = false) String IBAN
,@ApiParam(value = "") @Valid @RequestParam(value = "transactionSearchDateStart", required = false) LocalDate transactionSearchDateStart
,@ApiParam(value = "") @Valid @RequestParam(value = "transactionSearchDateEnd", required = false) LocalDate transactionSearchDateEnd
,@ApiParam(value = "") @Valid @RequestParam(value = "transactionAmount", required = false) Double transactionAmount
,@ApiParam(value = "") @Valid @RequestParam(value = "MaxNumberOfResults", required = false) Long maxNumberOfResults
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Transaction>>(objectMapper.readValue("[ {\n  \"ibanSender\" : \"NL00RABO0000000000\",\n  \"ibanReceiver\" : \"NL00INGB0000000000\",\n  \"transactionId\" : 12345678,\n  \"nameSender\" : \"Frank Wester\",\n  \"transactionDate\" : \"06-05-2020\",\n  \"transferAmount\" : 20\n}, {\n  \"ibanSender\" : \"NL00RABO0000000000\",\n  \"ibanReceiver\" : \"NL00INGB0000000000\",\n  \"transactionId\" : 12345678,\n  \"nameSender\" : \"Frank Wester\",\n  \"transactionDate\" : \"06-05-2020\",\n  \"transferAmount\" : 20\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Long> transferFunds(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Transaction body
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Long>(objectMapper.readValue("19726482", Long.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Long>(HttpStatus.NOT_IMPLEMENTED);
    }

}
