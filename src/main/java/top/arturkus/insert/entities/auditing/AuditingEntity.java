package top.arturkus.insert.entities.auditing;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@ToString
public class AuditingEntity extends SimplifiedAuditingEntity {

    @Column(name = "CANCEL_DATE")
    private LocalDateTime cancelDate;

    @Column(name = "DELIVERY_DATE")
    private LocalDateTime deliveryDate;

    public void setCancelDate() {
        this.cancelDate = LocalDateTime.now();
    }

    public void setDeliveryDate() {
        this.deliveryDate = LocalDateTime.now();
    }
}
