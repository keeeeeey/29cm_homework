package kr.co._29cm.homework.dto.response;

import kr.co._29cm.homework.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {

    private Long productNumber;
    private String productName;
    private int productPrice;
    private int productStock;

    @Builder
    public ProductResponseDto(Product product) {
        this.productNumber = product.getProductNumber();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productStock = product.getProductStock();
    }
}
