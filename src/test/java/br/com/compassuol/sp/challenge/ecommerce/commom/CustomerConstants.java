package br.com.compassuol.sp.challenge.ecommerce.commom;

import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;

public class CustomerConstants {
    public static final Customer CUSTOMER = new Customer("Lucas", "46780770817",
            "glucas038@gmail.com", true);

    public static final Customer CUSTOMER_TWO = new Customer("Gabriel", "57627510074",
            "lucas-gabriel70@live.com", true);

    public static final Customer ALTER_CUSTOMER_DTO_CPF = new Customer("Lucas", "57627510074",
            "glucas038@gmail.com", true);

    public static final Customer ALTER_CUSTOMER_CPF = new Customer("Lucas", "57627510074",
            "glucas038@gmail.com", true);

    public static final Customer EMPTY_CUSTOMER = new Customer();
    public static final Customer INVALID_CUSTOMER = new Customer("","", "", false);

}
