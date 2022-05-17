package kr.co._29cm.homework.testdata;

import kr.co._29cm.homework.entity.Product;
import kr.co._29cm.homework.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class TestDataRunner implements ApplicationRunner {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
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

    public Product createProduct(Long productNumber, String productName, int productPrice, int productStock) {
        Product product = Product.builder()
                .productNumber(productNumber)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();
        return product;
    }
}
