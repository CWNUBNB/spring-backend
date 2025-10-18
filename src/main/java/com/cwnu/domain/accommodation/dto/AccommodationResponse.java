package com.cwnu.domain.accommodation.dto;

import com.cwnu.domain.accommodation.entity.Accommodation;
import com.cwnu.domain.accommodation.entity.AccommodationStatus;
import com.cwnu.domain.accommodation.entity.AccommodationType;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@Schema(description = "숙소 단건 조회 응답 DTO")
public class AccommodationResponse {

    @Schema(example = "1", description = "숙소 ID")
    private Long id;

    @Schema(example = "부산 해운대 오션뷰 숙소", description = "숙소 제목")
    private String title;

    @Schema(example = "탁 트인 오션뷰와 함께하는 프리미엄 숙소입니다.", description = "숙소 설명")
    private String description;

    @Schema(example = "ENTIRE_PLACE", description = "숙소 유형 (ENTIRE_PLACE, PRIVATE_ROOM, SHARED_ROOM 등)")
    private AccommodationType accommodationType;

    @Schema(example = "4", description = "최대 수용 인원")
    private Integer maxGuests;

    @Schema(example = "2", description = "침실 수")
    private Integer beds;

    @Schema(example = "1", description = "욕실 수")
    private Integer bathrooms;

    @Schema(example = "120000", description = "1박당 숙박 요금 (원)")
    private BigDecimal pricePerNight;

    @Schema(example = "20000", description = "청소비 (원)")
    private BigDecimal cleaningFee;

    @Schema(example = "ACTIVE", description = "숙소 상태 (ACTIVE, INACTIVE, UNDER_REVIEW, DELETED)")
    private AccommodationStatus status;

    @Schema(example = "15:00", description = "체크인 시간 (HH:mm)")
    private LocalTime checkInTime;

    @Schema(example = "11:00", description = "체크아웃 시간 (HH:mm)")
    private LocalTime checkOutTime;

    @Schema(example = "2025-01-01T15:20:30", description = "등록 일시")
    private LocalDateTime createdAt;

    @Schema(example = "2025-01-03T11:10:00", description = "수정 일시")
    private LocalDateTime updatedAt;

    // ✅ Entity → DTO 변환 메서드
    public static AccommodationResponse fromEntity(Accommodation accommodation) {
        return AccommodationResponse.builder()
                .id(accommodation.getId())
                .title(accommodation.getTitle())
                .description(accommodation.getDescription())
                .accommodationType(accommodation.getAccommodationType())
                .maxGuests(accommodation.getMaxGuests())
                .beds(accommodation.getBeds())
                .bathrooms(accommodation.getBathrooms())
                .pricePerNight(accommodation.getPricePerNight())
                .cleaningFee(accommodation.getCleaningFee())
                .status(accommodation.getStatus())
                .checkInTime(accommodation.getCheckInTime())
                .checkOutTime(accommodation.getCheckOutTime())
                .createdAt(accommodation.getCreatedAt())
                .updatedAt(accommodation.getUpdatedAt())
                .build();
    }

    @QueryProjection
    public AccommodationResponse(Long id, String title, String description, AccommodationType accommodationType, Integer maxGuests, Integer beds, Integer bathrooms, BigDecimal pricePerNight, BigDecimal cleaningFee, AccommodationStatus status, LocalTime checkInTime, LocalTime checkOutTime, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.accommodationType = accommodationType;
        this.maxGuests = maxGuests;
        this.beds = beds;
        this.bathrooms = bathrooms;
        this.pricePerNight = pricePerNight;
        this.cleaningFee = cleaningFee;
        this.status = status;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}