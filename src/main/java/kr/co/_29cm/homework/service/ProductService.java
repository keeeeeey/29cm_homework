package kr.co._29cm.homework.service;

import kr.co._29cm.homework.dto.response.ProductResponseDto;
import kr.co._29cm.homework.entity.Product;
import kr.co._29cm.homework.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public void getAllProduct() {
        List<Product> productList = productRepository.findAll();
        System.out.println("상품정보    상호명                              판매가격            재고수");
        productList.forEach((product) -> {
            System.out.println(product.getProductNumber() + "   " + product.getProductName() + "    " + product.getProductPrice() + "   " + product.getProductStock());
        });
    }

    @Transactional(readOnly = true)
    public Product getProduct(Long productNumber) {
        Product product = productRepository.findByProductNumber(productNumber)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 상품입니다."));
        return product;
    }
}
