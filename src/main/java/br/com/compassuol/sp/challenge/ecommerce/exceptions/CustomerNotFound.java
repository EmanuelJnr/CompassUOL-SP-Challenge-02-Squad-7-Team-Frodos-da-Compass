package br.com.compassuol.sp.challenge.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFound extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    public CustomerNotFound(long customerId) {
        super(String.format("Id " + customerId + " does not exist in the database "));
    }
}
