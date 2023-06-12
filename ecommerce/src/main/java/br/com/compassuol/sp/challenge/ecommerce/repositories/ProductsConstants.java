package br.com.compassuol.sp.challenge.ecommerce.commom;

import br.com.compassuol.sp.challenge.ecommerce.entities.Products;

public class ProductsConstants {
    public static final Products PRODUCT = new Products(123456,"Feijão",11.99,"comida",true);

    public static final Products PRODUCT_TWO = new Products(564789,"Arroz",15.00,"comida",true);

    public static final Products PRODUCT_ID = new Products(112233,"Batata",11.00,"comida",true);

    public static final Products ALTER_PRODUCT_ID = new Products(112233,"Beterraba",5.30,"comida",true);

    public static final Products EMPTY_PRODUCT = new Products();
    public static final Products INVALID_PRODUCT = new Products(null, "",0, "",false);

}