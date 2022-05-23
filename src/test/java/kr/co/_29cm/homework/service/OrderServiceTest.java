package kr.co._29cm.homework.service;

import kr.co._29cm.homework.entity.Order;
import kr.co._29cm.homework.entity.Product;
import kr.co._29cm.homework.repository.OrderRepository;
import kr.co._29cm.homework.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    OrderRepository orderRepository;

    @Test
    @DisplayName("SoldOutException 테스트")
    public void test() {

        Product product = Product.builder()
                .productNumber((long) 768848)
                .productName("[STANLEY] GO CERAMIVAC 진공 텀블러/보틀 3종")
                .productPrice(21000)
                .productStock(45)
                .build();

        Order order = Order.builder()
                .product(product)
                .productCount(5)
                .build();

        List<Order> orderList = new ArrayList<>();
        orderList.add(order);

        OrderService orderService = new OrderService(productRepository, orderRepository);
        when(productRepository.findByProductName(any()))
                .thenReturn(Optional.of(product));
        when(orderRepository.findAll()).thenReturn(orderList);

        Runnable r = new CheckThread(order, product, orderService);

        Thread t1 = new Thread(r);
        t1.setName("쓰레드1");
        Thread t2 = new Thread(r);
        t2.setName("쓰레드2");
        Thread t3 = new Thread(r);
        t3.setName("쓰레드3");
        Thread t4 = new Thread(r);
        t4.setName("쓰레드4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    public class CheckThread implements Runnable {

        Order order;
        Product product;
        OrderService orderService;
        
        CheckThread(Order order, Product product, OrderService orderService) {
            this.order = order;
            this.product = product;
            this.orderService = orderService;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            try{
                System.out.println(threadName + " 쓰레드 시작");
                for (int i = 0; i < 5; i++) {
                    orderService.doOrder();
                }
                System.out.println(threadName + " 쓰레드 종료");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
