package kr.co._29cm.homework.service;

import kr.co._29cm.homework.dto.request.OrderRequestDto;
import kr.co._29cm.homework.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    List<OrderRequestDto> requestDtoList = new ArrayList<>();

    public void addOrder(Product product, int productCount) {
        OrderRequestDto requestDto = OrderRequestDto.builder()
                .product(product)
                .productCount(productCount)
                .build();
        requestDtoList.add(requestDto);
    }

    public List<OrderRequestDto> doOrder() {
        return requestDtoList;
    }

    public void clearOrderList() {
        requestDtoList.clear();
    }
}
