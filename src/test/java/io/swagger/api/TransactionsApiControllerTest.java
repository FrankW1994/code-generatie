package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import io.swagger.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionsApiControllerTest{

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        this.mvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void transactionsControllerTest() throws Exception {
        mvc.perform(get("/transactions")
                .header("Accept", "application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void makeCorrectTransactionsShouldReturnCREATED() throws Exception {
        Transaction newObjectInstance = new Transaction("NL77INHO0123456789",
                "NL11INHO1111111111", "GPOSSEL",
                new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()),
                140D);

        mvc.perform(MockMvcRequestBuilders.post("/transactions")
                .content(asJsonString(newObjectInstance))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void makeWrongCredentialsTransactionsShouldReturnINTERNAL_SERVER_ERROR() throws Exception {
        Transaction newObjectInstance = new Transaction("NL22INHO9876543210",
                "NL33INHO3333333333", "GPOSSEL",
                new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()),
                40D);

        mvc.perform(MockMvcRequestBuilders.post("/transactions")
                .content(asJsonString(newObjectInstance))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }


    @Test
    public void searchTransactionOn_nameSender_ShouldReturnTransaction() throws Exception {
        String nameSender = "GPOSSEL";

        mvc.perform(get("/transactions?nameSender=" + nameSender)
                .header("Accept", "application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void searchTransactionOn_transactionId_ShouldReturnTransaction() throws Exception {
        Long transactionId = 1l;

        mvc.perform(get("/transactions?transactionId=" + transactionId)
                .header("Accept", "application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void searchTransactionOn_IBAN_ShouldReturnTransaction() throws Exception {
        String IBAN = "NL22INHO9876543210";

        mvc.perform(get("/transactions?IBAN=" + IBAN)
                .header("Accept", "application/json"))
                .andExpect(status().isOk());
        }

    @Test
    public void searchTransactionOn_transactionAmount_ShouldReturnTransaction() throws Exception {
        Double transactionAmount = 90d;

        mvc.perform(get("/transactions?transactionAmount=" + transactionAmount)
                .header("Accept", "application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void searchTransactionOn_MaxNumberOfResults_ShouldReturnTransaction() throws Exception {
        Integer MaxNumberOfResults = 2;
        ObjectMapper mapper = new ObjectMapper();

        MvcResult result = mvc.perform(get("/transactions?MaxNumberOfResults=" + MaxNumberOfResults)
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andReturn();

        List<Transaction> pp3 = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Transaction>>() {});
        pp3.forEach(System.out::println);
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}