package top.arturkus.insert.helpers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.arturkus.insert.entities.OrderEntity;
import top.arturkus.insert.enums.OrderStatus;

@Getter
@Setter
@NoArgsConstructor
public class OrderGridHelper extends OrderHelper {
    private Long id;
    private OrderStatus status;

    public OrderGridHelper(OrderEntity order) {
        super(order);
        this.id = order.getId();
        this.status = order.getStatus();
    }
}
