package top.arturkus.insert.entities.auditing;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@ToString
public class SimplifiedAuditingEntity {

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;

    @Column(name = "DELETED_DATE")
    private LocalDateTime deletedDate;

    @Column(name = "IS_DELETED", nullable = false)
    private Boolean isDeleted = false;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }

    @PreRemove
    protected void onRemove() {
        this.isDeleted = true;
        this.deletedDate = LocalDateTime.now();
    }
}