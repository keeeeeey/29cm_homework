package kr.co._29cm.homework.dto.request;

import kr.co._29cm.homework.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequestDto {
    private String productName;
    private int productPrice;
    private int productCount;

    @Builder
    public OrderRequestDto(Product product, int productCount) {
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productCount = productCount;
    }
}
