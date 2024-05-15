package lk.ijse.Dto.tm;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartTM {
    private int id;
    private String name;
    private double price;
    private  String qty;
    private double total;
}
