package top.arturkus.insert.services;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import top.arturkus.insert.entities.ItemEntity;
import top.arturkus.insert.entities.OrderEntity;
import top.arturkus.insert.enums.OrderStatus;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitDataService {

    private final static Faker faker = new Faker();


    @EventListener(ApplicationReadyEvent.class)
    public List<OrderEntity> init() {
       return List.of(createOrder(), createOrder(), createOrder(), createOrder());
    }

    public OrderEntity createOrder() {
        OrderEntity order = OrderEntity.builder()
                .name(faker.lorem().characters(5, 30))
                .status(faker.options().option(OrderStatus.class))
                .build();
        List<ItemEntity> items = List.of(createItem(order), createItem(order), createItem(order));
        order.setPrice((float) items.stream().mapToDouble(ItemEntity::getWeight).sum());
        order.setItems(items);
        return order;
    }

    public ItemEntity createItem(OrderEntity order) {
        return ItemEntity.builder()
                .name(faker.lorem().characters(5, 30))
                .price((float) faker.number().randomDouble(4, 1, 3000))
                .count(faker.number().numberBetween(1, 10))
                .weight((float) faker.number().randomDouble(2, 1, 25))
                .order(order)
                .build();
    }
}