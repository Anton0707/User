package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by admin on 9/3/17.
 */
@Data
@NoArgsConstructor
public class OrderToItem {
    private long orderId;
    private long itemId;

    public OrderToItem(long orderId, long itemId){
        this.orderId = orderId;
        this.itemId = itemId;
    }
}
