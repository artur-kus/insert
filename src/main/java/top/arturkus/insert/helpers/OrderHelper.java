package top.arturkus.insert.helpers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.arturkus.insert.entities.OrderEntity;
import top.arturkus.insert.helpers.item.ItemHelper;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderHelper {
    @NotBlank(message = "Name is mandatory")
    private String name;
    private Float price;
    @NotNull(message = "Items is mandatory")
    private List<ItemHelper> items;

    public OrderHelper(OrderEntity order) {
        this.name = order.getName();
        this.price = order.getPrice();
        this.items = order.getItems().stream().map(ItemHelper::new).toList();
    }
}
