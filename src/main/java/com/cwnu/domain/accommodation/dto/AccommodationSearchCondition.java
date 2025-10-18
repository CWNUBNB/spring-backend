package com.cwnu.domain.accommodation.dto;


import com.cwnu.domain.accommodation.entity.AccommodationStatus;
import com.cwnu.domain.accommodation.entity.AccommodationType;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "숙소 검색 조건 DTO")
public class AccommodationSearchCondition {

    @Schema(example = "오션뷰", description = "숙소 제목")
    private String title;

    @Schema(example = "ENTIRE_PLACE", description = "숙소 유형 (ENTIRE_PLACE, PRIVATE_ROOM, SHARED_ROOM)")
    private AccommodationType accommodationType;

    @Schema(example = "ACTIVE", description = "숙소 상태 (ACTIVE, INACTIVE, UNDER_REVIEW, DELETED)")
    private AccommodationStatus status;

    @Schema(example = "100000", description = "최소 숙박 요금 (원)")
    private BigDecimal minPrice;

    @Schema(example = "300000", description = "최대 숙박 요금 (원)")
    private BigDecimal maxPrice;

    @Schema(example = "4", description = "최대 수용 인원")
    private Integer maxGuests;

    @Schema(example = "2", description = "최소 수용 인원")
    private Integer minGuests;

}
