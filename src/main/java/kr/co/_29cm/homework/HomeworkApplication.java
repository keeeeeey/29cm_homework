package kr.co._29cm.homework;

import kr.co._29cm.homework.entity.Product;
import kr.co._29cm.homework.service.OrderService;
import kr.co._29cm.homework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;

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
                productService.getAllProduct();

                boolean doOrder = true;

                while (doOrder) {
                    System.out.print("상품번호 : ");
                    String productNumber = scanner.nextLine();

                    boolean checkProduct = productService.checkProduct(Long.valueOf(productNumber));

                    if (checkProduct == false) {
                        System.out.println("존재하지 않는 상품입니다.");
                        continue;
                    }

                    if (productNumber.equals(" ")) {
                        doOrder = orderService.doOrder();
                    } else {
                        System.out.print("수량 : ");
                        String productCount = scanner.nextLine();
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
