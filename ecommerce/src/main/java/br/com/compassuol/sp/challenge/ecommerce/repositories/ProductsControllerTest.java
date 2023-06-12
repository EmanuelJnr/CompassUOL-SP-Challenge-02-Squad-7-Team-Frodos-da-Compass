package br.com.compassuol.sp.challenge.ecommerce.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.compassuol.sp.challenge.ecommerce.dtos.ProductsDTO;
import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;
import br.com.compassuol.sp.challenge.ecommerce.entities.Products;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerNotFound;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerRepeatedData;
import br.com.compassuol.sp.challenge.ecommerce.services.ProductsService;

@WebMvcTest(ProductsController.class)
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductsService productsService;

    private static final Products PRODUCT = new Products();

	private static final Products EMPTY_PRODUCT = null;
	
	private static final Products INVALID_PRODUCT = null;

	private static final String ALTER_PRODUCT_ID = null;
    
    ProductsDTO productDTO = new ProductsDTO();
    
    @Test
    public void createProduct_WithValidData_ReturnsCreated() throws Exception {
        when(productsService.save(PRODUCT)).thenReturn(PRODUCT);

        mockMvc.perform(post("/v1/products").contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(PRODUCT));

    }

    @Test
    public void createProduct_WithInvalidData_ReturnsBadRequest() throws Exception{
        Products emptyProducts = new Products();

        mockMvc.perform(post("/v1/customers")
                        .content(objectMapper.writeValueAsString(EMPTY_PRODUCT))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());


        mockMvc.perform(post("/v1/customers").contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(INVALID_PRODUCT
                        )))
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    public void findProduct_ByExistingId_ReturnsCustomer() throws Exception{
        when(productsService.findById(1)).thenReturn(Optional.of(PRODUCT));

        mockMvc.perform(get("/v1/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(PRODUCT));
    }

    @Test
    public void findProduct_ByNotExistingId_ReturnsNotFound() throws Exception{
        when(productsService.findById(any())).thenThrow(CustomerNotFound.class);

        mockMvc.perform(get("/v1/products/1").contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(PRODUCT)))
                .andExpect(status().isNotFound());
    }

}