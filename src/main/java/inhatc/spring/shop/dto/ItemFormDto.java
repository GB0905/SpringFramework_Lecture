package inhatc.spring.shop.dto;

import inhatc.spring.shop.constant.ItemSellStatus;
import inhatc.spring.shop.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemFormDto {

    private Long id;             // 상품 코드

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;       // 상품명

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private int price;           // 상품 가격

    @NotNull(message = "수량은 필수 입력 값입니다.")
    private int stockNumber;     // 상품 재고 수량

    // Lob -> database랑 관련된 애, 자료형 타입
    @NotBlank(message = "상품 상세 설명은 필수 입력 값입니다.")
    private String itemDetail;   // 상품 상세 설명

    private ItemSellStatus itemSellStatus; // 상품 판매 상태


    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    // 모델 매핑 시 이름이 겹치는 내용만 매핑한다.
    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem() {
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto entityToDto(Item item) {
        return modelMapper.map(item, ItemFormDto.class);
    }
}