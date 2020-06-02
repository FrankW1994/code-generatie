package io.swagger.api;

import io.swagger.model.Account;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-02T09:24:14.507Z[GMT]")
@Controller
public class AccountApiController implements AccountApi {

    private static final Logger log = LoggerFactory.getLogger(AccountApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> accountIdDelete(@ApiParam(value = "the user",required=true) @PathVariable("Id") Long id
,@ApiParam(value = "the Account you want to delete",required=true) @PathVariable("IBAN") Account IBAN
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Account> accountIdGet(@ApiParam(value = "The id that needs to be fetched. Use user1 for testing.",required=true) @PathVariable("Id") List<Account> id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Account>(objectMapper.readValue("{\n  \"User\" : {\n    \"schema\" : {\n      \"password\" : \"password\",\n      \"birthdate\" : \"birthdate\",\n      \"phone\" : \"phone\",\n      \"firsname\" : \"firsname\",\n      \"rank\" : \"customer\",\n      \"registrationdate\" : \"registrationdate\",\n      \"id\" : 6,\n      \"email\" : \"email\",\n      \"lastname\" : \"lastname\"\n    }\n  },\n  \"IBAN\" : \"IBAN\",\n  \"ArrayOfTransactions\" : [ [ {\n    \"nameSender\" : \"nameSender\",\n    \"transferAmount\" : 6.027456183070403,\n    \"transactionDate\" : \"transactionDate\",\n    \"ibanSender\" : \"ibanSender\",\n    \"ibanReceiver\" : \"ibanReceiver\",\n    \"transactionId\" : 0\n  }, {\n    \"nameSender\" : \"nameSender\",\n    \"transferAmount\" : 6.027456183070403,\n    \"transactionDate\" : \"transactionDate\",\n    \"ibanSender\" : \"ibanSender\",\n    \"ibanReceiver\" : \"ibanReceiver\",\n    \"transactionId\" : 0\n  } ], [ {\n    \"nameSender\" : \"nameSender\",\n    \"transferAmount\" : 6.027456183070403,\n    \"transactionDate\" : \"transactionDate\",\n    \"ibanSender\" : \"ibanSender\",\n    \"ibanReceiver\" : \"ibanReceiver\",\n    \"transactionId\" : 0\n  }, {\n    \"nameSender\" : \"nameSender\",\n    \"transferAmount\" : 6.027456183070403,\n    \"transactionDate\" : \"transactionDate\",\n    \"ibanSender\" : \"ibanSender\",\n    \"ibanReceiver\" : \"ibanReceiver\",\n    \"transactionId\" : 0\n  } ] ],\n  \"id\" : 0\n}", Account.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Account>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> addAccount(@ApiParam(value = "the user",required=true) @PathVariable("Id") String id
,@ApiParam(value = ""  )  @Valid @RequestBody Account body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
