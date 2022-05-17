package kr.co._29cm.homework;

import kr.co._29cm.homework.dto.request.OrderRequestDto;
import kr.co._29cm.homework.dto.response.ProductResponseDto;
import kr.co._29cm.homework.entity.Product;
import kr.co._29cm.homework.service.OrderService;
import kr.co._29cm.homework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
@Order(2)
public class HomeworkApplication implements CommandLineRunner {

    Scanner scanner = new Scanner(System.in);

    private final ProductService productService;
    private final OrderService orderService;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(HomeworkApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        System.out.println("고객님의 주문 감사합니다.");
    }

    @Override
    public void run(String... args) throws Exception {
        boolean run = true;
        do {
            System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
            String answer = scanner.next();
            scanner.nextLine();
            if (answer.equals("o") || answer.equals("order")) {
                List<ProductResponseDto> productList = productService.getAllProduct();
                System.out.println("상품정보    상호명                              판매가격            재고수");
                productList.forEach((product) -> {
                    System.out.println(product.getProductNumber() + "   " + product.getProductName() + "    " + product.getProductPrice() + "   " + product.getProductStock());
                });

                boolean doOrder = true;

                while (doOrder) {
                    System.out.print("상품번호 : ");
                    String productNumber = scanner.nextLine();
                    System.out.print("수량 : ");
                    String productCount = scanner.nextLine();

                    if (productNumber.equals(" ") && productCount.equals(" ")) {
                        System.out.println("주문내역 : ");
                        System.out.println("--------------------------------------");
                        List<OrderRequestDto> orderList = orderService.doOrder();
                        int totalPrice = 0;

                        for (OrderRequestDto order : orderList) {
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
                        orderService.clearOrderList();
                        doOrder = false;
                    } else {
                        Product product = productService.getProduct(Long.valueOf(productNumber));
                        orderService.addOrder(product, Integer.parseInt(productCount));
                    }

                }

            } else if (answer.equals("q") || answer.equals("quit")) {
                run = false;
            } else {
                System.out.println("올바른 입력값이 아닙니다.");
            }
        } while (run);
    }

}
