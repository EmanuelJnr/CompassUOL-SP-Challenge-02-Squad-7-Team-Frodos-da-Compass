package br.com.compassuol.sp.challenge.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Cpf and email must be unique")
public class CustomerRepeatedData extends Exception {

    public CustomerRepeatedData() {
        super(String.format("Cpf and email must be unique"));
    }

}
