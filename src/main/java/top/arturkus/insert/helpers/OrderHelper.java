package top.arturkus.insert.helpers;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.arturkus.insert.entities.OrderEntity;
import top.arturkus.insert.helpers.item.ItemHelper;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderHelper extends SimplifiedOrderHelper {
    @NotNull(message = "Items is mandatory")
    private List<ItemHelper> items;

    public OrderHelper(OrderEntity order) {
        super(order.getName(), order.getPrice());
        this.items = order.getItems().stream().map(ItemHelper::new).toList();
    }
}
