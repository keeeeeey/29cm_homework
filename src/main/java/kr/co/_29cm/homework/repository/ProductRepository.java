package kr.co._29cm.homework.repository;

import kr.co._29cm.homework.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductNumber(Long productNumber);
}
