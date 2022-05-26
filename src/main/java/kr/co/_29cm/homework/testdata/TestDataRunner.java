package kr.co._29cm.homework.testdata;

import kr.co._29cm.homework.entity.Product;
import kr.co._29cm.homework.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class TestDataRunner implements ApplicationRunner {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<List<String>> csvList = new ArrayList<List<String>>();
        File file = new File("/Users/kimkiyun/Downloads/items_.csv");
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                List<String> aLine = new ArrayList<>();
                String[] lineArr = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)",-1);
                aLine = Arrays.asList(lineArr);
                csvList.add(aLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br == null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i < csvList.size(); i++) {
            Product product = Product.builder()
                    .productNumber((long) Integer.parseInt(csvList.get(i).get(0)))
                    .productName(csvList.get(i).get(1))
                    .productPrice(Integer.parseInt(csvList.get(i).get(2)))
                    .productStock(Integer.parseInt(csvList.get(i).get(3)))
                    .build();
            productRepository.save(product);
        }

    }

}
