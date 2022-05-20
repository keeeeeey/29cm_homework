package kr.co._29cm.homework.service;

import kr.co._29cm.homework.entity.Order;
import kr.co._29cm.homework.entity.Product;
import kr.co._29cm.homework.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("SoldOutException 테스트")
    public void test() {
        Optional<Product> product1 = productRepository.findByProductNumber((long) 768848);
        Order order1 = Order.builder()
                .product(product1.get())
                .productCount(55)
                .build();
        Optional<Product> product2 = productRepository.findByProductNumber((long) 748943);
        Order order2 = Order.builder()
                .product(product1.get())
                .productCount(55)
                .build();
        Optional<Product> product3 = productRepository.findByProductNumber((long) 779989);
        Order order3 = Order.builder()
                .product(product1.get())
                .productCount(55)
                .build();
        Optional<Product> product4 = productRepository.findByProductNumber((long) 779943);
        Order order4 = Order.builder()
                .product(product1.get())
                .productCount(55)
                .build();

        Runnable d1 = new CheckThread(order1, product1.get());
        Runnable d2 = new CheckThread(order2, product2.get());
        Runnable d3 = new CheckThread(order3, product3.get());
        Runnable d4 = new CheckThread(order4, product4.get());

        Thread thread1 = new Thread(d1);
        Thread thread2 = new Thread(d2);
        Thread thread3 = new Thread(d3);
        Thread thread4 = new Thread(d4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    public class CheckThread implements Runnable {

        Order order;
        Product product;
        
        CheckThread(Order order, Product product) {
            this.order = order;
            this.product = product;
        }

        @Override
        public void run() {
            try{
                for(int i = 0; i < 5; i++){
                    if (order.getProductCount() > product.getProductStock()) {
                        System.out.println(product.getProductNumber() + "SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.");
                    } else {
                        System.out.println(product.getProductNumber() + "주문 완료");
                    }
                    Thread.sleep(1000);
                }

            } catch (Exception e) {
                System.out.println(e.toString());
            }

            System.out.println("쓰레드 종료 : " + product.getProductNumber());
        }

    }

    @BeforeEach
    public void setUp() {
        Product product1 = createProduct((long) 768848, "[STANLEY] GO CERAMIVAC 진공 텀블러/보틀 3종", 21000, 45);
        Product product2 = createProduct((long) 748943, "디오디너리 데일리 세트 (Daily set)", 19000, 89);
        Product product3 = createProduct((long) 779989, "버드와이저 HOME DJing 굿즈 세트", 35000, 43);
        Product product4 = createProduct((long) 779943, "Fabrik Pottery Flat Cup & Saucer - Mint", 24900, 89);
        Product product5 = createProduct((long) 768110, "네페라 손 세정제 대용량 500ml 이더블유지", 7000, 79);
        Product product6 = createProduct((long) 517643, "에어팟프로 AirPods PRO 블루투스 이어폰(MWP22KH/A)", 260800, 26);
        Product product7 = createProduct((long) 706803, "ZEROVITY™ Flip Flop Cream 2.0 (Z-FF-CRAJ-)", 38000, 81);
        Product product8 = createProduct((long) 759928, "마스크 스트랩 분실방지 오염방지 목걸이", 2800, 85);
        Product product9 = createProduct((long) 213341, "20SS 오픈 카라/투 버튼 피케 티셔츠 (6color)", 33250, 99);
        Product product10 = createProduct((long) 377169, "[29Edition.]_[스페셜구성] 뉴코튼베이직 브라렛 세트 (브라1+팬티2)", 24900, 60);
        Product product11 = createProduct((long) 744775, "SHUT UP [TK00112]", 28000, 35);
        Product product12 = createProduct((long) 779049, "[리퍼브/키친마켓] Fabrik Pottery Cup, Saucer (단품)", 10000, 64);
        Product product13 = createProduct((long) 611019, "플루크 new 피그먼트 오버핏 반팔티셔츠 FST701 / 7color M", 19800, 7);
        Product product14 = createProduct((long) 628066, "무설탕 프로틴 초콜릿 틴볼스", 12900, 8);
        Product product15 = createProduct((long) 502480, "[29Edition.]_[스페셜구성] 렉시 브라렛 세트(브라1+팬티2)", 24900, 41);
        Product product16 = createProduct((long) 782858, "폴로 랄프로렌 남성 수영복반바지 컬렉션 (51color)", 39500, 50);
        Product product17 = createProduct((long) 760709, "파버카스텔 연필1자루", 200, 70);
        Product product18 = createProduct((long) 778422, "캠핑덕 우드롤테이블", 45000, 7);
        Product product19 = createProduct((long) 648418, "BS 02-2A DAYPACK 26 (BLACK)", 238000, 5);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);
        productRepository.save(product7);
        productRepository.save(product8);
        productRepository.save(product9);
        productRepository.save(product10);
        productRepository.save(product11);
        productRepository.save(product12);
        productRepository.save(product13);
        productRepository.save(product14);
        productRepository.save(product15);
        productRepository.save(product16);
        productRepository.save(product17);
        productRepository.save(product18);
        productRepository.save(product19);
    }

    private Product createProduct(Long productNumber, String productName, int productPrice, int productStock) {
        Product product = Product.builder()
                .productNumber(productNumber)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();
        return product;
    }

    @AfterEach
    public void setDown() {
        productRepository.deleteAll();
    }
}
