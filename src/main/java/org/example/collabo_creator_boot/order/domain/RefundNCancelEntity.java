package org.example.collabo_creator_boot.order.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.collabo_creator_boot.common.BasicEntity;

@Entity
@Getter
@Table(name = "refund_and_cancel")
public class RefundNCancelEntity extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="refund_cancel_no", nullable = false)
    private Long refundCancelNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_no", referencedColumnName = "order_no")
    private OrdersEntity ordersEntity;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "status", nullable = false)
    private Integer status;
}
