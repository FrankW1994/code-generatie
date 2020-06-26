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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.equalToObject;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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


    /* all wrong credentials for transaction */
    @Test
    public void makeWrongIBANTransactionsShouldReturnINTERNAL_SERVER_ERROR() throws Exception {
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
    public void makeWrongAmountTransactionsShouldReturnINTERNAL_SERVER_ERROR() throws Exception {
        Transaction newObjectInstance = new Transaction("NL22INHO9876543210",
                "NL33INHO3333333333", "GPOSSEL",
                new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()),
                40000D);

        mvc.perform(MockMvcRequestBuilders.post("/transactions")
                .content(asJsonString(newObjectInstance))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }


    /* all parameter for search for transaction */
    @Test
    public void searchTransactionOn_nameSender_ShouldReturnTransaction() throws Exception {
        String nameSender = "GPOSSEL";

        mvc.perform(get("/transactions?nameSender=" + nameSender)
                .header("Accept", "application/json"))
                .andExpect(ResultMatcher.matchAll(status().isOk(),
                MockMvcResultMatchers.jsonPath("$.[*].nameSender").value("GPOSSEL")));
    }

    @Test
    public void searchTransactionOn_transactionId_ShouldReturnTransaction() throws Exception {
        int transactionId = 1000052;

        mvc.perform(get("/transactions?transactionId=" + transactionId)
                .header("Accept", "application/json"))
                .andExpect(ResultMatcher.matchAll(status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.*.transactionId").value(1000052)));
    }

    @Test
    public void searchTransactionOn_IBAN_ShouldReturnTransaction() throws Exception {
        String ibanSender = "NL77INHO0123456789";

        mvc.perform(get("/transactions?IBAN=" + ibanSender)
                .header("Accept", "application/json"))
                .andExpect(ResultMatcher.matchAll(status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.[*].ibanSender").value("NL77INHO0123456789")));
        }

    @Test
    public void searchTransactionOn_transactionAmount_ShouldReturnTransaction() throws Exception {
        Double transactionAmount = 100D;
        mvc.perform(get("/transactions?transferAmount=" + transactionAmount.doubleValue())
                .header("Accept", "application/json"))
                .andExpect(ResultMatcher.matchAll(status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.[*].transferAmount").value(100.0)));
    }

    @Test
    public void searchTransactionOn_MaxNumberOfResults_ShouldReturnListofSize() throws Exception {
        Integer MaxNumberOfResults = 2;

        MvcResult result = mvc.perform(get("/transactions?MaxNumberOfResults=" + MaxNumberOfResults)
                .header("Accept", "application/json"))
                .andExpect(ResultMatcher.matchAll(status().isOk(),
                        MockMvcResultMatchers.jsonPath("$").isArray(),
                        MockMvcResultMatchers.jsonPath("$", hasSize(2))))
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        List<Transaction> transactionsList = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Transaction>>() {});
        transactionsList.forEach(System.out::println);
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