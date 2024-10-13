package top.arturkus.insert.services;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import top.arturkus.insert.enums.OrderStatus;
import top.arturkus.insert.exceptions.NotFoundException;
import top.arturkus.insert.helpers.DefaultResponse;
import top.arturkus.insert.helpers.OrderGridHelper;
import top.arturkus.insert.helpers.OrderHelper;
import top.arturkus.insert.utils.InitObjectUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void shouldCreateOrder() {
        //GIVEN
        OrderGridHelper createdOrder = createOrder();
        //THEN
        Assertions.assertNotNull(createdOrder);
    }

    @Test
    public void givenIncorrectHelperDataWhenCreateOrderThenThrowException() {
        //GIVEN
        OrderHelper helper = InitObjectUtils.createOrderHelper();
        helper.setName(null);
        // WHEN & THEN
        assertThrows(DataIntegrityViolationException.class, () -> orderService.create(helper));
    }

    @Test
    public void shouldThrowExceptionWhenOrderNotFound() {
        Long notExistingId = 999L;
        Assertions.assertThrows(NotFoundException.class, () -> orderService.get(notExistingId));
    }

    @Test
    public void whenCreatedOrderThenChangeOrderStatusToActiveThenReturnSuccessResponse() throws NotFoundException {
        //GIVEN
        OrderGridHelper createdOrder = createOrder();
        OrderStatus newOrderStatus = OrderStatus.ACTIVE;
        //WHEN
        DefaultResponse response = orderService.changeStatus(createdOrder.getId(), newOrderStatus);
        //THEN
        Assertions.assertEquals(orderService.getSuccessResponse(newOrderStatus), response);
    }

    @Test
    public void whenCreatedOrderThenChangeOrderStatusToCanceledThenReturnSuccessResponse() throws NotFoundException {
        //GIVEN
        OrderGridHelper createdOrder = createOrder();
        OrderStatus newOrderStatus = OrderStatus.CANCELED;
        //WHEN
        DefaultResponse response = orderService.changeStatus(createdOrder.getId(), newOrderStatus);
        //THEN
        Assertions.assertEquals(orderService.getSuccessResponse(newOrderStatus), response);
    }

    @Test
    public void whenCreatedOrderThenTryToChangeAtTheSameStatusThenReturn() throws NotFoundException {
        //GIVEN
        OrderGridHelper createdOrder = createOrder();
        OrderStatus newOrderStatus = OrderStatus.CANCELED;
        //WHEN
        DefaultResponse response = orderService.changeStatus(createdOrder.getId(), newOrderStatus);
        //THEN
        Assertions.assertEquals(orderService.getSuccessResponse(newOrderStatus), response);
    }

    @Test
    public void shouldGetOrderAndNotThrowException() {
        //GIVEN
        OrderGridHelper createdOrder = createOrder();
        //WHEN THEN
        Assertions.assertDoesNotThrow(() -> orderService.get(createdOrder.getId()));
    }

    @Test
    @Transactional
    public void whenFindAllOrdersThenReturnContent() {
        //GIVEN
        createOrder();
        //WHEN THEN
        List<OrderGridHelper> list = orderService.findAll();
        Assertions.assertTrue(list != null && !list.isEmpty());
    }

    @Test
    public void shouldDeleteOrder() {
        //GIVEN
        OrderGridHelper order = createOrder();
        //WHEN
        orderService.delete(order.getId());
        // THEN
        Assertions.assertThrows(NotFoundException.class, () -> orderService.get(order.getId()));
    }

    private OrderGridHelper createOrder() {
        OrderHelper helper = InitObjectUtils.createOrderHelper();
        return orderService.create(helper);
    }
}