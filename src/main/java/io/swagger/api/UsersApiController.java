package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.User;
import io.swagger.service.UserApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.threeten.bp.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T09:28:40.437Z[GMT]")
@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private UserApiService userApiService;

    public ResponseEntity<User> addUser(@ApiParam(value = "created users" ,required=true )  @Valid @RequestBody User body
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                System.out.println(body);
                User user = new User((long)body.getId(), body.getFirstname(), body.getLastname(), body.getEmail(), body.getPassword(), body.getPhone(), body.getBirthdate(), body.getRegistrationdate(), body.getRank(), body.getStatus());
                User checkUser = userApiService.postUser(user);
                if (user != null)
                {
                    return new ResponseEntity<User>(objectMapper.readValue(objectMapper.writeValueAsString(checkUser), User.class), HttpStatus.CREATED);
                }
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
        if (accept != null) {

                return new ResponseEntity<Void>(userApiService.delete(userId));

        }
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> getUserById(@ApiParam(value = "Id of the user to return",required=true) @PathVariable("userId") Long userId
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null /*&& accept.contains("application/json")*/) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue(objectMapper.writeValueAsString(userApiService.getById(userId)), User.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<User>> getUsers(@ApiParam(value = "") @Valid @RequestParam(value = "firstname", required = false) String firstname
, @ApiParam(value = "") @Valid @RequestParam(value = "lastname", required = false) String lastname
, @ApiParam(value = "") @Valid @RequestParam(value = "registrationDateStart", required = false) LocalDate registrationDateStart
, @ApiParam(value = "") @Valid @RequestParam(value = "registrationDateEnd", required = false) LocalDate registrationDateEnd
, @ApiParam(value = "", allowableValues = "Customer, Employee, Admin") @Valid @RequestParam(value = "RankOfUser", required = false) String rankOfUser
, @ApiParam(value = "", allowableValues = "Active, Blocked") @Valid @RequestParam(value = "StatusOfUser", required = false) String statusOfUser
) {
        String accept = request.getHeader("Accept");
        if (accept != null /*&& accept.contains("application/json")*/) {
            try {
                return new ResponseEntity<List<User>>(objectMapper.readValue(objectMapper.writeValueAsString(userApiService.getUsers()), List.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> updateUser(@ApiParam(value = "Updated user object" ,required=true )  @Valid @RequestBody User body
            , @ApiParam(value = "userId that need to be updated",required=true) @PathVariable("userId") String userId
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue(objectMapper.writeValueAsString(userApiService.update(userId, body)), User.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }


}
