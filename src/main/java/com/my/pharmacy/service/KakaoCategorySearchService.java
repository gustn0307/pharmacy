package com.my.pharmacy.service;

import com.my.pharmacy.dto.KakaoApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class KakaoCategorySearchService {
    private final RestTemplate restTemplate;

    // 환경변수에서 ${KAKAO_REST_API_KEY} 값을 가져와 변수에 저장
    // properties파일이나 yml 파일에 있는 환경변수의 변수명의 계층값을 순서대로 '.'으로 연결해서 표현
    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;

    // 카테고리 검색에 필요한 기초 URL을 상수로 선언
    private static final String KAKAO_CATEGORY_URL = "https://dapi.kakao.com/v2/local/search/category";

    // 카테고리 그룹 코드(약국)
    private static final String CATEGORY = "PM9";
//    private static final String CATEGORY = "CE7"; // 카페

    // 위도(latitude), 경도(longitude)를 인자로 받아서 카테고리 검색
    public KakaoApiResponseDto resultCategorySearch(double latitude, double longitude) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(KAKAO_CATEGORY_URL);

        // 1. 카테고리
        uriBuilder.queryParam("category_group_code", CATEGORY);

        // 2. x, y 값
        uriBuilder.queryParam("x", longitude);
        uriBuilder.queryParam("y", latitude);

        // 3. 검색 반경
        uriBuilder.queryParam("radius", 1000);

        // 4. 검색 사이즈(나중에 처리)
//        uriBuilder.queryParam("size", 3);

        // 5. 정렬 처리
        uriBuilder.queryParam("sort", "distance");

        // url에 포함된 한글을 UTF-8로 인코딩 처리
        URI uri = uriBuilder.build().encode().toUri();

        // Header에 권한 설정 작업
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        // 카카오 api 호출
        return restTemplate
                .exchange(
                        uri,
                        HttpMethod.GET,
                        httpEntity,
                        KakaoApiResponseDto.class
                ).getBody();
    }
}
