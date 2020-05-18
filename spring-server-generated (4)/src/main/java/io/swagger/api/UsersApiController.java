package io.swagger.api;

import org.threeten.bp.LocalDate;
import io.swagger.model.User;
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
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<User> addUser(@ApiParam(value = "created users" ,required=true )  @Valid @RequestBody User body
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\n  \"id\" : 102,\n  \"firstname\" : \"Henk\",\n  \"lastname\" : \"De Jong\",\n  \"email\" : \"H.DJong@gmail.com\",\n  \"password\" : \"Wachtwoord123\",\n  \"phone\" : \"+31-638288204\",\n  \"birthdate\" : \"12-03-1989\",\n  \"registrationdate\" : \"20-11-2019\",\n  \"rank\" : \"Customer\",\n  \"status\" : \"Active\"\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "The userId that needs to be deleted",required=true) @PathVariable("userId") String userId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<User>> getUserById(@ApiParam(value = "Id of the user to return",required=true) @PathVariable("userId") Long userId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"id\" : 102,\n  \"firstname\" : \"Henk\",\n  \"lastname\" : \"De Jong\",\n  \"email\" : \"H.DJong@gmail.com\",\n  \"password\" : \"Wachtwoord123\",\n  \"phone\" : \"+31-638288204\",\n  \"birthdate\" : \"12-03-1989\",\n  \"registrationdate\" : \"20-11-2019\",\n  \"rank\" : \"Customer\",\n  \"status\" : \"Active\"\n}, {\n  \"id\" : 102,\n  \"firstname\" : \"Henk\",\n  \"lastname\" : \"De Jong\",\n  \"email\" : \"H.DJong@gmail.com\",\n  \"password\" : \"Wachtwoord123\",\n  \"phone\" : \"+31-638288204\",\n  \"birthdate\" : \"12-03-1989\",\n  \"registrationdate\" : \"20-11-2019\",\n  \"rank\" : \"Customer\",\n  \"status\" : \"Active\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<User>> getUsers(@ApiParam(value = "") @Valid @RequestParam(value = "firstname", required = false) String firstname
,@ApiParam(value = "") @Valid @RequestParam(value = "lastname", required = false) String lastname
,@ApiParam(value = "") @Valid @RequestParam(value = "registrationDateStart", required = false) LocalDate registrationDateStart
,@ApiParam(value = "") @Valid @RequestParam(value = "registrationDateEnd", required = false) LocalDate registrationDateEnd
,@ApiParam(value = "", allowableValues = "Customer, Employee, Admin") @Valid @RequestParam(value = "RankOfUser", required = false) String rankOfUser
,@ApiParam(value = "", allowableValues = "Active, Blocked") @Valid @RequestParam(value = "StatusOfUser", required = false) String statusOfUser
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"id\" : 102,\n  \"firstname\" : \"Henk\",\n  \"lastname\" : \"De Jong\",\n  \"email\" : \"H.DJong@gmail.com\",\n  \"password\" : \"Wachtwoord123\",\n  \"phone\" : \"+31-638288204\",\n  \"birthdate\" : \"12-03-1989\",\n  \"registrationdate\" : \"20-11-2019\",\n  \"rank\" : \"Customer\",\n  \"status\" : \"Active\"\n}, {\n  \"id\" : 102,\n  \"firstname\" : \"Henk\",\n  \"lastname\" : \"De Jong\",\n  \"email\" : \"H.DJong@gmail.com\",\n  \"password\" : \"Wachtwoord123\",\n  \"phone\" : \"+31-638288204\",\n  \"birthdate\" : \"12-03-1989\",\n  \"registrationdate\" : \"20-11-2019\",\n  \"rank\" : \"Customer\",\n  \"status\" : \"Active\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "Updated user object" ,required=true )  @Valid @RequestBody User body
,@ApiParam(value = "userId that need to be updated",required=true) @PathVariable("userId") String userId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
