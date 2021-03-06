package kr.co._29cm.homework;

import kr.co._29cm.homework.entity.Product;
import kr.co._29cm.homework.service.OrderService;
import kr.co._29cm.homework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(2)
@RequiredArgsConstructor
public class OrderApplication implements CommandLineRunner {

    Scanner scanner = new Scanner(System.in);

    private final ProductService productService;
    private final OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        boolean run = true;
        do {
            System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
            String answer = scanner.nextLine();
            if (answer.equals("o") || answer.equals("order")) {
                productService.getAllProduct();

                boolean doOrder = true;

                while (doOrder) {
                    System.out.print("상품번호 : ");
                    String productNumber = scanner.nextLine();

                    boolean checkProductNumber = true;

                    if (!productNumber.isEmpty() && !productNumber.equals(" ")) {
                        checkProductNumber = productService.checkProduct(Long.valueOf(productNumber));
                    }

                    if (checkProductNumber == false || productNumber.isEmpty()) {
                        System.out.println("존재하지 않는 상품입니다.");
                        continue;
                    }

                    if (productNumber.equals(" ")) {
                        doOrder = orderService.doOrder();
                    } else {
                        System.out.print("수량 : ");
                        String productCount = scanner.nextLine();

                        if (productCount.trim().isEmpty() || !productCount.matches("^[1-9][0-9]*$")) {
                            System.out.println("정확한 수량을 입력해주세요.");
                            continue;
                        }

                        Product product = productService.getProduct(Long.valueOf(productNumber));
                        orderService.addOrder(product, Integer.parseInt(productCount));
                    }

                }

            } else if (answer.equals("q") || answer.equals("quit")) {
                run = false;
            } else if (answer.trim().isEmpty()) {
                System.out.println("올바른 입력값이 아닙니다.");
            } else {
                System.out.println("올바른 입력값이 아닙니다.");
            }
        } while (run);
        System.out.println("고객님의 주문 감사합니다.");
    }

}
