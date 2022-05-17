package kr.co._29cm.homework.repository;

import kr.co._29cm.homework.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductNumber(Long productNumber);

    Optional<Product> findByProductName(String productName);
}
