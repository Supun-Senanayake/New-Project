package lk.ijse.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Cartdto {
    private int id;
    private String name;
    private double price;
    private  String qty;
    private double total;
}
