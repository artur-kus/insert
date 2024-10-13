package top.arturkus.insert.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.arturkus.insert.entities.OrderEntity;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("FROM OrderEntity o JOIN FETCH o.items WHERE o.id = ?1")
    Optional<OrderEntity> findByIdAndInitItems(Long orderId);
}