package lk.ijse.Dto;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Productdto {
    private int id;
    private String productId;
    private String productName;
    private String type;
    private int stock;
    private double price;
    private String status;
    private String image;
    private Date date;


    public Productdto(String productId, String productName, String type, int stock, double price, String status) {
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.stock = stock;
        this.price = price;
        this.status = status;

    }
}

