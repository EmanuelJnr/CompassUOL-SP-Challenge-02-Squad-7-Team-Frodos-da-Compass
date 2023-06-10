package br.com.compassuol.sp.challenge.ecommerce.common;

import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;

public class CustomerConstants {
    public static final Customer CUSTOMER =
            new Customer("Lucas", "46780770817", "glucas038@gmail.com", true);

    public static final Customer INVALID_CUSTOMER =
            new Customer("Lucas", " ", " ", true);

    public static final Customer EMPTY_CUSTOMER =
            new Customer();
}
