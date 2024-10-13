package top.arturkus.insert.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import top.arturkus.insert.entities.auditing.AuditingEntity;
import top.arturkus.insert.enums.OrderStatus;
import top.arturkus.insert.helpers.OrderHelper;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class OrderEntity extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "PRICE", nullable = false)
    private Float price;
    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<ItemEntity> items;


    @Builder
    public OrderEntity(String name, Float price, OrderStatus status, List<ItemEntity> items) {
        this.name = name;
        this.status = status;
        this.price = price;
        this.items = items;
    }

    public OrderEntity(OrderHelper order) {
        this.name = order.getName();
        this.status = OrderStatus.DRAFT;
        this.price = order.getPrice();
        this.items = order.getItems().stream().map(i -> new ItemEntity(i, this)).toList();
    }

    public void fillFields(OrderHelper helper) {
        this.name = helper.getName();
        this.price = helper.getPrice();
    }
}
