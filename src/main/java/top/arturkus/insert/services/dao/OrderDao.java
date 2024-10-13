package top.arturkus.insert.services.dao;

import top.arturkus.insert.entities.OrderEntity;
import top.arturkus.insert.enums.OrderStatus;
import top.arturkus.insert.exceptions.NotFoundException;
import top.arturkus.insert.helpers.OrderGridHelper;
import top.arturkus.insert.helpers.OrderHelper;

import java.util.List;

public interface OrderDao {

    OrderEntity create(OrderHelper order);

    OrderEntity get(Long id) throws NotFoundException;

    OrderEntity getWithItems(Long id) throws NotFoundException;

    OrderEntity update(Long id, OrderHelper helper) throws NotFoundException;

    void delete(Long id);

    List<OrderGridHelper> findAll();

    void save(OrderEntity order);

    List<OrderEntity> save(List<OrderEntity> order);
}