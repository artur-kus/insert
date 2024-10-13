package top.arturkus.insert.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.arturkus.insert.entities.OrderEntity;
import top.arturkus.insert.enums.OrderStatus;
import top.arturkus.insert.exceptions.NotFoundException;
import top.arturkus.insert.helpers.DefaultResponse;
import top.arturkus.insert.helpers.OrderGridHelper;
import top.arturkus.insert.helpers.OrderHelper;
import top.arturkus.insert.services.dao.OrderDao;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;

    public OrderGridHelper create(OrderHelper order) {
        return new OrderGridHelper(orderDao.create(order));
    }

    public OrderGridHelper get(Long id) throws NotFoundException {
        return new OrderGridHelper(orderDao.getWithItems(id));
    }

    public DefaultResponse changeStatus(Long id, OrderStatus status) throws NotFoundException {
        OrderEntity order = orderDao.get(id);
        if (order.getStatus().equals(status))
            return new DefaultResponse(false, "Order actually: " + status.name(), new Date());
        return switch (status) {
            case ACTIVE, DELIVERED -> {
                if (order.getStatus().equals(OrderStatus.DRAFT)) {
                    yield changeStatus(order, status);
                } else yield new DefaultResponse(
                        false, "Incorrect order status - " + order.getStatus(), new Date());
            }
            case CANCELED -> changeStatus(order, status);
            default -> new DefaultResponse(false, "empty message", new Date());
        };
    }

    private DefaultResponse changeStatus(OrderEntity order, OrderStatus status) {
        order.setStatus(status);
        orderDao.save(order);
        return getSuccessResponse(status);
    }

    DefaultResponse getSuccessResponse(OrderStatus status) {
        return new DefaultResponse(true, "Order " + status.name(), new Date());
    }

    public OrderGridHelper update(Long id, OrderHelper helper) throws NotFoundException {
        return new OrderGridHelper(orderDao.update(id, helper));
    }

    public DefaultResponse delete(Long id) {
        orderDao.delete(id);
        return new DefaultResponse(true, "success", new Date());
    }

    public List<OrderGridHelper> findAll() {
        return orderDao.findAll();
    }
}