package top.arturkus.insert.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import top.arturkus.insert.helpers.OrderGridHelper;
import top.arturkus.insert.helpers.OrderHelper;
import top.arturkus.insert.utils.InitObjectUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final Gson gson = new GsonBuilder().create();

    @Test
    public void givenEmptyContentWhenCreateOrderThenReturn400() throws Exception {
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenValidHelperWhenCreateOrderThenReturn201() throws Exception {
        //GIVEN
        OrderHelper helper = InitObjectUtils.createOrderHelper();
        String request = gson.toJson(helper);
        //WHEN
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated());
    }


    @Test
    public void givenNotExistingOrderWhenGettingOrderThenReturn404() throws Exception {
        int notExistingOrderId = 999;

        mockMvc.perform(get("/orders/" + notExistingOrderId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenExistingOrderWhenGettingOrderThenReturn200() throws Exception {
        //GIVEN
        OrderHelper helper = InitObjectUtils.createOrderHelper();
        String request = gson.toJson(helper);
        MvcResult createdOrderResult = mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn();
        // WHEN & THEN
        OrderGridHelper order = gson.fromJson(
                createdOrderResult.getResponse().getContentAsString(), OrderGridHelper.class);
        mockMvc.perform(get("/orders/" + order.getId()))
                .andExpect(status().isOk());
    }
}