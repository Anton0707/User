package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by admin on 9/1/17.
 */
@Data
@NoArgsConstructor
public class Item {
    private long itemId;
    private String brand;
    private String model;
    private double price;
    public Item(long itemId, String brand, String model, double price){
        this.itemId = itemId;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }
}
