package top.arturkus.insert.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.arturkus.insert.entities.auditing.SimplifiedAuditingEntity;
import top.arturkus.insert.helpers.item.ItemHelper;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ITEMS")
public class ItemEntity extends SimplifiedAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "PRICE", nullable = false)
    private Float price;
    @Column(name = "COUNT", nullable = false)
    private Integer count;
    @Column(name = "WEIGHT", nullable = false)
    private Float weight;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ID_ORDER", nullable = false)
    private OrderEntity order;

    @Builder
    public ItemEntity(String name, Float price, int count, Float weight, OrderEntity order) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.weight = weight;
        this.order = order;
    }

    public ItemEntity(ItemHelper item, OrderEntity order) {
        this.name = item.getName();
        this.price = item.getPrice();
        this.count = item.getCount();
        this.weight = item.getWeight();
        this.order = order;
    }
}