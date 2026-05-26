package com.my.pharmacy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DocumentDto {
    @JsonProperty("address_name")
    private String addressName; // 주소 이름

    @JsonProperty("x")
    private Double longitude; // 경도

    @JsonProperty("y")
    private Double latitude; // 위도

    @JsonProperty("distance")
    private String distance; // 기준 주소와의 거리

    @JsonProperty("place_name")
    private String placeName; // 약국 이름

    @JsonProperty("phone")
    private String phone; // 약국 전화번호
}
