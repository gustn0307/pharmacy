package com.my.pharmacy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class OutputDto {

    private String pharmacyName; // 약국 이름
    private String pharmacyAddress; // 약국 주소
    private String pharmacyPhone; // 약국 전화번호
    private String distance; // 거리(m)
    private String directionUrl; // 길안내 URL
    private String roadViewUrl; // 로드뷰 URL

}
