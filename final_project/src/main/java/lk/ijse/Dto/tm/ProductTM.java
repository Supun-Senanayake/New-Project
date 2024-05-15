package lk.ijse.Dto.tm;


import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ProductTM {
    private String productId;
    private String productName;
    private String productType;
    private int productStock;
    private double productPrice;
    private String productStatus;
}
