package top.arturkus.insert.utils;

import com.github.javafaker.Faker;
import top.arturkus.insert.helpers.OrderHelper;
import top.arturkus.insert.helpers.item.ItemHelper;

import java.util.List;

public class InitObjectUtils {

    private final static Faker faker = new Faker();

    public static OrderHelper createOrderHelper() {
        List<ItemHelper> items = List.of(createItemHelper(), createItemHelper(), createItemHelper());
        OrderHelper order = new OrderHelper();
        order.setName(faker.lorem().characters(5, 30));
        order.setPrice((float) items.stream().mapToDouble(ItemHelper::getWeight).sum());
        order.setItems(items);
        return order;
    }

    public static ItemHelper createItemHelper() {
        ItemHelper item = new ItemHelper();
        item.setName(faker.lorem().characters(5, 30));
        item.setPrice((float) faker.number().randomDouble(4, 1, 3000));
        item.setCount(faker.number().numberBetween(1, 10));
        item.setWeight((float) faker.number().randomDouble(2, 1, 25));
        return item;
    }
}
