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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-11T15:46:56.875Z[GMT]")
@Controller
public class AccountsApiController implements AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

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

    public ResponseEntity<Account> accountsIBANGet(@ApiParam(value = "The Account you want to access",required=true) @PathVariable("IBAN") List<Account> IBAN
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Account>(objectMapper.readValue("{\n  \"IBAN\" : \"NLxxINHO0xxxxxxxxx\",\n  \"balance\" : 100,\n  \"rank\" : \"Current\",\n  \"currency\" : \"EUR\",\n  \"userId\" : 2\n}", Account.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Account>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> addAccount(@ApiParam(value = "created account" ,required=true )  @Valid @RequestBody Account body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> depositToAccount(@NotNull @ApiParam(value = "amount of deposit", required = true) @Valid @RequestParam(value = "Amount", required = true) Double amount
,@ApiParam(value = "the account",required=true) @PathVariable("IBAN") String IBAN
,@ApiParam(value = ""  )  @Valid @RequestBody Account body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Account>> getAccounts() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Account>>(objectMapper.readValue("[ {\n  \"IBAN\" : \"NLxxINHO0xxxxxxxxx\",\n  \"balance\" : 100,\n  \"rank\" : \"Current\",\n  \"currency\" : \"EUR\",\n  \"userId\" : 2\n}, {\n  \"IBAN\" : \"NLxxINHO0xxxxxxxxx\",\n  \"balance\" : 100,\n  \"rank\" : \"Current\",\n  \"currency\" : \"EUR\",\n  \"userId\" : 2\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Account>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Account>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> withdrawFromAccount(@NotNull @ApiParam(value = "amount of withdraw", required = true) @Valid @RequestParam(value = "Amount", required = true) Double amount
,@ApiParam(value = "the account",required=true) @PathVariable("IBAN") String IBAN
,@ApiParam(value = ""  )  @Valid @RequestBody Account body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
