package br.com.compassuol.sp.challenge.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFound extends Exception{

    private static final long serialVersionUID = 1L;

    public CustomerNotFound(long customerId) {
        super(String.format("Id " + customerId + " does not exist in the database "));
    }
}
