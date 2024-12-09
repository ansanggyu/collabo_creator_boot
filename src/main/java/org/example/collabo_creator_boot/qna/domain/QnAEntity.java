package org.example.collabo_creator_boot.qna.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.collabo_creator_boot.common.BasicEntity;
import org.example.collabo_creator_boot.creator.domain.CreatorEntity;
import org.example.collabo_creator_boot.customer.domain.CustomerEntity;
import org.example.collabo_creator_boot.product.domain.ProductEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "qna")
public class QnAEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="qna_no", nullable = false)
    private Long qnaNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no", referencedColumnName = "product_no")
    private ProductEntity productEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private CustomerEntity customerEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName = "creator_id")
    private CreatorEntity creatorEntity;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "answer")
    private String answer;
}
