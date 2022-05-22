package kr.co._29cm.homework.service;

import kr.co._29cm.homework.entity.Order;
import kr.co._29cm.homework.entity.Product;
import kr.co._29cm.homework.repository.OrderRepository;
import kr.co._29cm.homework.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public void addOrder(Product product, int productCount) {
        Order order = Order.builder()
                .product(product)
                .productCount(productCount)
                .build();
        orderRepository.save(order);
    }

    @Transactional
    public boolean doOrder() {
        boolean checkProduct = checkProductStock();

        if (checkProduct == false) {
            return false;
        }

        int totalPrice = 0;

        System.out.println("주문내역 : ");
        System.out.println("--------------------------------------");

        totalPrice = getTotalPriceAndPrintOrderList(totalPrice);

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
        orderRepository.deleteAll();
        return false;
    }

    private boolean checkProductStock() {
        List<Order> orderList = orderRepository.findAll();
        try {
            for (Order order : orderList) {
                Product product = productRepository.findByProductName(order.getProductName())
                        .orElseThrow(() -> new RuntimeException("존재하지 않는 상품입니다."));

                if (order.getProductCount() > product.getProductStock()) {
                    throw new Exception();
                }
            }
            return true;
        } catch(Exception e) {
            System.out.println("SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.");
            orderRepository.deleteAll();
            return false;
        }

    }

    private int getTotalPriceAndPrintOrderList(int totalPrice) {
        List<Order> orderList = orderRepository.findAll();
        for (Order order : orderList) {
            Product product = productRepository.findByProductName(order.getProductName())
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 상품입니다."));
            product.minusProductStock(order.getProductCount());
            System.out.println(order.getProductName() + " - " + order.getProductCount() + "개");
            totalPrice += order.getProductPrice() * order.getProductCount();
        }
        return totalPrice;
    }

}
