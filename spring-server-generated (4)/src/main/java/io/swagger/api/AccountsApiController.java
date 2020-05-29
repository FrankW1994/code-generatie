package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.model.WithdrawDeposit;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.AccountApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T09:28:40.437Z[GMT]")
@Controller
public class AccountsApiController implements AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public AccountApiService accountApiService;


    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> accountsIBANDelete(@ApiParam(value = "the Account you want to delete",required=true) @PathVariable("IBAN") Account IBAN
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Account> accountsIBANGet(@ApiParam(value = "The Account you want to access",required=true) @PathVariable("IBAN") String IBAN
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Account>(objectMapper.readValue("{\n  \"IBAN\" : \"NLxxINHO0xxxxxxxxx\",\n  \"balance\" : 100,\n  \"rank\" : \"Current\",\n  \"currency\" : \"EUR\",\n  \"userId\" : 2,\n  \"status\" : \"Active\"\n}", Account.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Account>(HttpStatus.NOT_IMPLEMENTED);
    }


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> createAccount(@RequestBody Account body) {
        Account NEWAccount = new Account(body.getUserId(), body.getIBAN(), body.getRank(), body.getStatus(), body.getBalance(), body.getCurrency());
        accountApiService.addAccount(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    public ResponseEntity<Account> addAccount(@ApiParam(value = "created account" ,required=true )  @Valid @RequestBody Account body
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Account NEWAccount = new Account(body.getUserId(), body.getIBAN(), body.getRank(), body.getStatus(), body.getBalance(), body.getCurrency());
                return new ResponseEntity<Account>(objectMapper.readValue(objectMapper.writeValueAsString(NEWAccount), Account.class), HttpStatus.CREATED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Account>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> depositToAccount(@ApiParam(value = ""  )  @Valid @RequestBody WithdrawDeposit body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Account>> getAccounts(@ApiParam(value = "", allowableValues = "Current, Saving") @Valid @RequestParam(value = "RankOfAccount", required = false) String rankOfAccount
,@ApiParam(value = "") @Valid @RequestParam(value = "Balance", required = false) Double balance
,@ApiParam(value = "") @Valid @RequestParam(value = "UserId", required = false) Long userId
,@ApiParam(value = "", allowableValues = "Active, Blocked") @Valid @RequestParam(value = "StatusOfAccount", required = false) String statusOfAccount
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            List<Account> accounts = accountApiService.getAllAccounts();
            return ResponseEntity
                    .status(200)
                    .body(accounts);
        }
        return new ResponseEntity<List<Account>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> withdrawFromAccount(@ApiParam(value = ""  )  @Valid @RequestBody WithdrawDeposit body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
