package practice_7Oct2025;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class ECommerceAPI_Pojos {

}

@Getter
@Setter
class LoginRequestPojo {

    private String userEmail;
    private String userPassword;

}

@Getter
@Setter
class LoginResponsePojo {

    private String token;
    private String userId;
    private String message;

}

@Getter
@Setter
class CreateProductResponsePojo {

    private String productId;
    private String message;

}

@Getter
@Setter
class CreateOrderRequestPojo {

    private List<Order> orders;

}

@Getter
@Setter
class Order {

    private String country;
    private String productOrderedId;

}

@Getter
@Setter
class CreateOrderResponsePojo {

    private List<String> orders;
    private List<String> productOrderId;
    private String message;

}

@Getter
@Setter
class DeleteProductResponsePojo {

    private String message;

}
