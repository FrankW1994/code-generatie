package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.dao.RepositoryUser;
import io.swagger.model.User;
import io.swagger.model.User.RankEnum;
import io.swagger.model.User.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
public class UsersApiControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private  RepositoryUser repositoryUser;

    private MockMvc mvc;

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setUp() {
        this.mvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void usersControllerTest() throws Exception {
        mvc.perform(get("/users?firstname=&lastname=&RankOfUser=&StatusOfUser=")
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh"))
                .andExpect(status().isOk());
    }
    @Test
    public void getUserByIdShouldReturnISOK() throws Exception {
        long userId =  1000052;

        repositoryUser.findAll().forEach(System.out::println);

        mvc.perform(get("/users/" + userId)
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh"))
                .andExpect(status().isOk());
    }


    /*Create a User*/
    @Test
    public void makeCorrectUsersShouldReturnCREATED() throws Exception {
        User newObjectInstance = new User("Sander", "Boeree", "625874@student.inholland.nl", "Welkom123",
                "0615022324", "23-04-2000", "16-01-2020", RankEnum.ADMIN, StatusEnum.BLOCKED);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh")
                .content(asJsonString(newObjectInstance))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    /*Delete a User*/
    @Test
    public void deleteCorrectUserShouldReturnDeleted() throws Exception{
        long userId =  1000105;
        mvc.perform(delete("/users/" + userId)
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh"))
                .andExpect(status().isOk());
    }

    /* all wrong credentials for transaction */
    @Test
    public void makeWrong_FirstNameWithInteger_UserShouldReturnINTERNAL_SERVER_ERROR() throws Exception {
        User newObjectInstance = new User("Sander1", "Boeree", "625874@student.inholland.nl", "Welkom123",
                "0615992392", "23-04-2000", "16-01-2020", RankEnum.ADMIN, StatusEnum.BLOCKED);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh")
                .content(asJsonString(newObjectInstance))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void makeWrong_FirstNameToLong_UserShouldReturnINTERNAL_SERVER_ERROR() throws Exception {
        User newObjectInstance = new User("SanderAlexanderrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", "Boeree", "625874@student.inholland.nl", "Welkom123",
                "0615992392", "23-04-2000", "16-01-2020", RankEnum.ADMIN, StatusEnum.BLOCKED);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh")
                .content(asJsonString(newObjectInstance))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void makeWrong_LastNameWithInteger_UserShouldReturnINTERNAL_SERVER_ERROR() throws Exception {
        User newObjectInstance = new User("Sander", "Boeree1", "625874@student.inholland.nl", "Welkom123",
                "0615992392", "23-04-2000", "16-01-2020", RankEnum.ADMIN, StatusEnum.BLOCKED);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh")
                .content(asJsonString(newObjectInstance))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void makeWrong_LastNameToLong_UserShouldReturnINTERNAL_SERVER_ERROR() throws Exception {
        User newObjectInstance = new User("Sander", "Boereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", "625874@student.inholland.nl", "Welkom123",
                "0615992392", "23-04-2000", "16-01-2020", RankEnum.ADMIN, StatusEnum.BLOCKED);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh")
                .content(asJsonString(newObjectInstance))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void makeInvalid_Email_UserShouldReturnINTERNAL_SERVER_ERROR() throws Exception {
        User newObjectInstance = new User("Sander", "Boeree", "foute#email.com", "Welkom123",
                "0615992392", "23-04-2000", "16-01-2020", RankEnum.ADMIN, StatusEnum.BLOCKED);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh")
                .content(asJsonString(newObjectInstance))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void makeInvalid_Password_UserShouldReturnINTERNAL_SERVER_ERROR() throws Exception {
        User newObjectInstance = new User("Sander", "Boeree", "625874@student.inholland.nl", "test",
                "0615992392", "23-04-2000", "16-01-2020", RankEnum.ADMIN, StatusEnum.BLOCKED);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh")
                .content(asJsonString(newObjectInstance))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void makeInvalid_PhoneNumber_UserShouldReturnINTERNAL_SERVER_ERROR() throws Exception {
        User newObjectInstance = new User("Sander", "Boeree", "625874@student.inholland.nl", "Welkom123",
                "261G599A2C392", "23-04-2000", "16-01-2020", RankEnum.ADMIN, StatusEnum.BLOCKED);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh")
                .content(asJsonString(newObjectInstance))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void makeInvalid_BirtDate_UserShouldReturnINTERNAL_SERVER_ERROR() throws Exception {
        User newObjectInstance = new User("Sander", "Boeree", "625874@student.inholland.nl", "Welkom123",
                "261G599A2C392", "16-01-2024", "16-01-2020", RankEnum.ADMIN, StatusEnum.BLOCKED);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh")
                .content(asJsonString(newObjectInstance))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }


    /* all parameter for search for transaction */
    @Test
    public void searchUserOn_FirstName_ShouldReturnUser() throws Exception {
        String firstname = "Henk";

        mvc.perform(get("/users?firstname=" + firstname)
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh"))
                .andExpect(ResultMatcher.matchAll(status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.[*].firstname").value("Henk")));
    }

    @Test
    public void searchUserOn_LastName_ShouldReturnUser() throws Exception {
        String lastname = "Anders";

        mvc.perform(get("/users?lastname=" + lastname)
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh"))
                .andExpect(ResultMatcher.matchAll(status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.[*].lastname").value("Anders")));
    }

    @Test
    public void searchUserOn_RankOfUser_ShouldReturnUser() throws Exception {
        String rank = "Customer";
        List<String> expectedResult = new ArrayList<>(Arrays.asList("Customer", "Customer"));

        mvc.perform(get("/users?RankOfUser=" + rank)
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh"))
                .andExpect(ResultMatcher.matchAll(status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.[*].rank").value(expectedResult)));
    }

    @Test
    public void searchUserOn_StatusOfUser_ShouldReturnUser() throws Exception {
        String status = "Active";
        List<String> expectedResult = new ArrayList<>(Arrays.asList("Active", "Active", "Active", "Active"));

        mvc.perform(get("/users?StatusOfUser=" + status)
                .header("Accept", "application/json")
                .header("X-AUTHENTICATION", "1234-abcd-5678-efgh"))
                .andExpect(ResultMatcher.matchAll(status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.[*].status").value(expectedResult)));
    }
}
