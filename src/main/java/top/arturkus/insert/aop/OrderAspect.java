package top.arturkus.insert.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import top.arturkus.insert.entities.OrderEntity;
import top.arturkus.insert.services.dao.OrderDao;

import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class OrderAspect {

    private final OrderDao orderDao;

    @Pointcut("execution(* top.arturkus.insert.services.OrderService.changeStatus(..))")
    public void changeStatusPointcut() {
    }

    @Pointcut("execution(* top.arturkus.insert.services.InitDataService.init(..))")
    public void initDataPointcut() {
    }

    @AfterReturning(pointcut = "changeStatusPointcut()", returning = "order")
    public void afterChangeStatus(OrderEntity order) {
        setOrderDates(order);
    }

    @AfterReturning(pointcut = "initDataPointcut()", returning = "orderList")
    public void afterInitData(List<OrderEntity> orderList) {
        orderList.forEach(this::setOrderDates);
        orderDao.save(orderList);
    }

    private void setOrderDates(OrderEntity order) {
        if (order.getStatus() == null) return;
        switch (order.getStatus()) {
            case CANCELED -> order.setCancelDate();
            case DELIVERED -> order.setDeliveryDate();
        }
        orderDao.save(order);
    }
}