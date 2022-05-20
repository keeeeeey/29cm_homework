package kr.co._29cm.homework.service;

import kr.co._29cm.homework.entity.Order;
import kr.co._29cm.homework.entity.Product;
import kr.co._29cm.homework.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    List<Order> orderList = new ArrayList<>();

    private final ProductRepository productRepository;

    public void addOrder(Product product, int productCount) {
        Order order = Order.builder()
                .product(product)
                .productCount(productCount)
                .build();
        orderList.add(order);
    }

    @Transactional
    public boolean doOrder() {
        for (Order order : orderList) {
            Product product = productRepository.findByProductName(order.getProductName())
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 상품입니다."));

            if (order.getProductCount() > product.getProductStock()) {
                System.out.println("SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.");
                return false;
            }
        }

        int totalPrice = 0;

        System.out.println("주문내역 : ");
        System.out.println("--------------------------------------");

        for (Order order : orderList) {
            Product product = productRepository.findByProductName(order.getProductName())
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 상품입니다."));
            product.minusProductStock(order.getProductCount());
            System.out.println(order.getProductName() + " - " + order.getProductCount() + "개");
            totalPrice += order.getProductPrice() * order.getProductCount();
        }

        System.out.println("--------------------------------------");
        if (totalPrice < 50000) {
            System.out.println("주문금액 : " + totalPrice);
            System.out.println("배송비 : " + 2500);
            System.out.println("--------------------------------------");
            System.out.println("지불금액 : " + (totalPrice + 2500));
            System.out.println("--------------------------------------");
        } else {
            System.out.println("주문금액 : " + totalPrice);
            System.out.println("--------------------------------------");
            System.out.println("지불금액 : " + totalPrice);
            System.out.println("--------------------------------------");
        }
        orderList.clear();
        return false;
    }

}
