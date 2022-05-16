package kr.co._29cm.homework.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_id")
    private Long id;

    @Column
    private Long productNumber;

    @Column
    private String productName;

    @Column
    private int productPrice;

    @Column
    private int productStock;

    @Builder
    public Product(final Long productNumber, final String productName,
                   final int productPrice, final int productStock) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }

    public void minusProductStock(int productCount) {
        this.productStock -= productCount;
    }
}
