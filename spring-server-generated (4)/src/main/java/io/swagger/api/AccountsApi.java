/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.WithdrawDeposit;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T09:28:40.437Z[GMT]")
@Api(value = "accounts", description = "the accounts API")
public interface AccountsApi {

    @ApiOperation(value = "Delete the account for your user", nickname = "accountsIBANDelete", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "account", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succesful deleted account"),
        @ApiResponse(code = 401, message = "unauthorized"),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/accounts/{IBAN}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> accountsIBANDelete(@ApiParam(value = "the Account you want to delete",required=true) @PathVariable("IBAN") Account IBAN
);


    @ApiOperation(value = "Get Account by IBAN from accounts", nickname = "accountsIBANGet", notes = "", response = Account.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "account", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The account was succesfully retrieved", response = Account.class),
        @ApiResponse(code = 400, message = "Invalid username supplied"),
        @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/accounts/{IBAN}",
        produces = { "application/json", "application/xml" }, 
        method = RequestMethod.GET)
    ResponseEntity<Account> accountsIBANGet(@ApiParam(value = "The Account you want to access",required=true) @PathVariable("IBAN") String IBAN
);


    @ApiOperation(value = "Add a new account", nickname = "addAccount", notes = "", response = Account.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created an account succesfully.", response = Account.class),
        @ApiResponse(code = 401, message = "Unauthorized action.") })
    @RequestMapping(value = "/accounts",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Account> addAccount(@ApiParam(value = "created account" ,required=true )  @Valid @RequestBody Account body
);


    @ApiOperation(value = "deposit money to IBAN account", nickname = "depositToAccount", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "account", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succesful deposit of money to account."),
        @ApiResponse(code = 401, message = "unauthorized") })
    @RequestMapping(value = "/accounts/deposit",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> depositToAccount(@ApiParam(value = ""  )  @Valid @RequestBody WithdrawDeposit body
);


    @ApiOperation(value = "returns list of accounts", nickname = "getAccounts", notes = "", response = Account.class, responseContainer = "List", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "succesful operation.", response = Account.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "invalid operation.") })
    @RequestMapping(value = "/accounts",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Account>> getAccounts(@ApiParam(value = "", allowableValues = "Current, Saving") @Valid @RequestParam(value = "RankOfAccount", required = false) String rankOfAccount
,@ApiParam(value = "") @Valid @RequestParam(value = "Balance", required = false) Double balance
,@ApiParam(value = "") @Valid @RequestParam(value = "UserId", required = false) Long userId
,@ApiParam(value = "", allowableValues = "Active, Blocked") @Valid @RequestParam(value = "StatusOfAccount", required = false) String statusOfAccount
);


    @ApiOperation(value = "withdraw money from IBAN account", nickname = "withdrawFromAccount", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "account", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succesful deposit of money to account."),
        @ApiResponse(code = 401, message = "unauthorized") })
    @RequestMapping(value = "/accounts/withdraw",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> withdrawFromAccount(@ApiParam(value = ""  )  @Valid @RequestBody WithdrawDeposit body
);

}
