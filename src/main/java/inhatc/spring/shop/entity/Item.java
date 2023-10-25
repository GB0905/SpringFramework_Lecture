package inhatc.spring.shop.entity;

import jakarta.persistence.*;
import inhatc.spring.shop.constant.ItemSellStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;             // 상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm;       // 상품명

    private int price;           // 상품 가격

    private int stockNumber;     // 상품 재고 수량

    @Lob // Large Object - CLOB, BLOB
    @Column(nullable = false)
    private String itemDetail;   // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;


    private LocalDateTime regTime;      // 등록일 (추후 제거)


    private LocalDateTime updateTime;   // 수정일 (추후 제거)

}