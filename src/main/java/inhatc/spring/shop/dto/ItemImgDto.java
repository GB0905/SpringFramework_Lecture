package inhatc.spring.shop.dto;

import inhatc.spring.shop.entity.ItemImg;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemImgDto {
    private Long id;

    private String imgName;             // 저장된 파일명
    private String oriImgName;          // 원본 파일명
    private String imgUrl;              // 이미지 URL
    private String repImgYn;            // 대표 이미지 여부

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto of(ItemImg itemImg) {
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}
