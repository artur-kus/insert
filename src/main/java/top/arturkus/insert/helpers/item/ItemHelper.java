package top.arturkus.insert.helpers.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.arturkus.insert.entities.ItemEntity;

@Getter
@Setter
@NoArgsConstructor
public class ItemHelper {
    private String name;
    private Float price;
    private int count;
    private float weight;


    public ItemHelper(ItemEntity item) {
        this.name = item.getName();
        this.price = item.getPrice();
        this.count = item.getCount();
        this.weight = item.getWeight();
    }
}
