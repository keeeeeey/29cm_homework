package kr.co._29cm.homework.service;

import kr.co._29cm.homework.dto.response.ProductResponseDto;
import kr.co._29cm.homework.entity.Product;
import kr.co._29cm.homework.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponseDto> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponseDto> dtoList = productList
                .stream()
                .map(o -> new ProductResponseDto(o))
                .collect(toList());
        return dtoList;
    }

    public Product getProduct(Long productNumber) {
        Product product = productRepository.findByProductNumber(productNumber)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 상품입니다."));
        return product;
    }
}
