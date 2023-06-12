package br.com.compassuol.sp.challenge.ecommerce.controllers;

import br.com.compassuol.sp.challenge.ecommerce.services.CustomerService;
import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerNotFound;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerRepeatedData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static br.com.compassuol.sp.challenge.ecommerce.commom.CustomerConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;


    @Test
    public void createCustomer_WithValidData_ReturnsCreated() throws Exception {
        when(customerService.createCustomer(CUSTOMER)).thenReturn(CUSTOMER);

        mockMvc.perform(post("/v1/customers").contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(CUSTOMER)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(CUSTOMER));

    }

    @Test
    public void createCustomer_WithInvalidData_ReturnsBadRequest() throws Exception{
        Customer emptyCustomer = new Customer();

        mockMvc.perform(post("/v1/customers")
                        .content(objectMapper.writeValueAsString(EMPTY_CUSTOMER))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());


        mockMvc.perform(post("/v1/customers").contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(INVALID_CUSTOMER
                        )))
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    public void createCustomer_WithExistingCpfAndEmail_ReturnsConflict() throws Exception {
        when(customerService.createCustomer(any())).thenThrow(CustomerRepeatedData.class);

        mockMvc.perform(post("/v1/customers").contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(CUSTOMER)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void findCustomer_ByExistingId_ReturnsCustomer() throws Exception{
        when(customerService.findCustomer(1)).thenReturn(Optional.of(CUSTOMER));

        mockMvc.perform(get("/v1/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(CUSTOMER));
    }

    @Test
    public void findCustomer_ByNotExistingId_ReturnsNotFound() throws Exception{
        when(customerService.findCustomer(any())).thenThrow(CustomerNotFound.class);

        mockMvc.perform(get("/v1/customers/1").contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(CUSTOMER)))
                .andExpect(status().isNotFound());
    }



    @Test
    public void changeCustomer_WithValidData_ReturnsCustomer() throws Exception {
        when(customerService.changeCustomer(1, ALTER_CUSTOMER_CPF)).thenReturn(ALTER_CUSTOMER_CPF);

        mockMvc.perform(put("/v1/customers/1").contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(ALTER_CUSTOMER_DTO_CPF)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(ALTER_CUSTOMER_CPF));
    }

    @Test
    public void changeCustomer_WithNotFoundCustomer_ReturnsNotFound() throws Exception {
        when(customerService.changeCustomer(any(), any())).thenThrow(CustomerNotFound.class,
                CustomerRepeatedData.class);

        mockMvc.perform(put("/v1/customers/1").contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(ALTER_CUSTOMER_DTO_CPF)))
                .andExpect(status().isNotFound());

    }


}