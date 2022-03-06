package com.shop.dto;

import com.shop.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ItemImgDto {       // 상품 저장 후 상품 이미지에 대한 데이터를 전달할 dto 클래스

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();     // 멤버 변수로 ModelMapper를 추가한다.

    public static ItemImgDto of(ItemImg itemImg) {
    // ItemImg 엔티티 객체를 파라미터로 받아서 ItemImg 객체의 자료형과 멤버변수의 이름이 같을 때 ItemImgDto로 값을 복사해서 반환한다.
    // static 메소드로 선언해 ItemImgDto 객체를 생성하지 않아도 호출할 수 있도록 한다.
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}
