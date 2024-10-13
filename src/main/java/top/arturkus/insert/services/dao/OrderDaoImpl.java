package top.arturkus.insert.services.dao;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import top.arturkus.insert.entities.OrderEntity;
import top.arturkus.insert.enums.OrderStatus;
import top.arturkus.insert.exceptions.NotFoundException;
import top.arturkus.insert.helpers.OrderGridHelper;
import top.arturkus.insert.helpers.OrderHelper;
import top.arturkus.insert.repositories.OrderRepository;

import java.util.List;

@Repository
@AllArgsConstructor
public class OrderDaoImpl implements OrderDao {

    private OrderRepository orderRepository;

    @Override
    public OrderEntity create(OrderHelper order) {
        return orderRepository.save(new OrderEntity(order));
    }

    @Override
    public OrderEntity get(Long id) throws NotFoundException {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(OrderEntity.class, id));
    }

    @Override
    public OrderEntity getWithItems(Long id) throws NotFoundException {
        return orderRepository.findByIdAndInitItems(id)
                .orElseThrow(() -> new NotFoundException(OrderEntity.class, id));
    }

    @Override
    public OrderEntity update(Long id, OrderHelper helper) throws NotFoundException {
        OrderEntity order = getWithItems(id);
        order.fillFields(helper);
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    //TODO Add pageable
    @Override
    public List<OrderGridHelper> findAll() {
        return  orderRepository.findAll().stream().map(OrderGridHelper::new).toList();
    }
    @Override
    public void save(OrderEntity order) {
        orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> save(List<OrderEntity> order) {
        return orderRepository.saveAll(order);
    }
}
