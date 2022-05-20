package kr.co._29cm.homework.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    private Long id;

    @Column
    private String productName;

    @Column
    private int productPrice;

    @Column
    private int productCount;

    @Builder
    public Order(final Product product, final int productCount) {
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productCount = productCount;
    }
}
