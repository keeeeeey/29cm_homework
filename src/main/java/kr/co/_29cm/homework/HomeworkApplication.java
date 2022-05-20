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
public class HomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }

}
