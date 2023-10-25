package inhatc.spring.shop.dto;

import inhatc.spring.shop.constant.ItemSellStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {

    private Long id;             // 상품 코드

    private String itemNm;       // 상품명

    private int price;           // 상품 가격

    private int stockNumber;     // 상품 재고 수량

    private String itemDetail;   // 상품 상세 설명

    private String itemSellStatus;

    private LocalDateTime regTime;      // 등록일 (추후 제거)

    private LocalDateTime updateTime;   // 수정일 (추후 제거)
}
