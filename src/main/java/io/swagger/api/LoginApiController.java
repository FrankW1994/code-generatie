package io.swagger.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.configuration.ApiKeySecurityConfig;
import io.swagger.model.ApiKey;
import io.swagger.model.Login;
import io.swagger.model.Transaction;
import io.swagger.service.LoginApiService;
import io.swagger.service.UserApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.DataInput;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-28T15:21:59.457Z[GMT]")
@Controller
public class LoginApiController implements LoginApi {

    private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private ApiKeySecurityConfig apikeyService;

    private LoginApiService loginApiService;

    @org.springframework.beans.factory.annotation.Autowired
    public LoginApiController(ObjectMapper objectMapper, HttpServletRequest request, ApiKeySecurityConfig apikeyService, LoginApiService loginApiService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.apikeyService = apikeyService;
        this.loginApiService = loginApiService;
    }

    public ResponseEntity<ApiKey> loginUser(@ApiParam(value = ""  )  @Valid @RequestBody Login body
    ) {
        String accept = request.getHeader("Accept");
     //   String apikey = request.getHeader("X-AUTHENTICATION");
        if (accept != null && accept.contains("application/json")) {
            try {
                ApiKey apikey = loginApiService.loginUser(body);
                if(apikey != null)
                {
                    return new ResponseEntity<ApiKey>(objectMapper.readValue(objectMapper.writeValueAsString(apikey), ApiKey.class), HttpStatus.OK);
                }
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ApiKey>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<ApiKey>(HttpStatus.UNAUTHORIZED);
    }

    protected ApiKey mapApiKeyData(ApiKey body) {
        ApiKey apikey = new ApiKey(body.getApiKey(), body.getUserId(), body.getTokenCreated(), body.getTokenExpires());
        return apikey;
    }

}