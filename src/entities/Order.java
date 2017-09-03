package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

/**
 * Created by admin on 9/3/17.
 */
@Data
@NoArgsConstructor
public class Order {
    private long orderId;
    private long userId;
    private Date date;
    private List<OrderToItem> orderToItems;

    public Order(long orderId, long userId, Date date){
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
    }
}
