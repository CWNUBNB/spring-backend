package com.cwnu.domain.accommodation.dto;

import com.cwnu.domain.accommodation.entity.Accommodation;
import com.cwnu.domain.accommodation.entity.AccommodationStatus;
import com.cwnu.domain.accommodation.entity.AccommodationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "숙소 생성 요청 DTO")
public class AccommodationCreateRequest {

    @NotBlank(message = "{accommodation.title.NotBlank}")
    @Size(max = 200, message = "{accommodation.title.Size}")
    @Schema(example = "부산 해운대 오션뷰 숙소", description = "숙소 제목")
    private String title;

    @Size(max = 2000, message = "{accommodation.description.Size}")
    @Schema(example = "탁 트인 오션뷰와 함께하는 프리미엄 숙소입니다.", description = "숙소 설명")
    private String description;

    @NotNull(message = "{accommodation.accommodationType.NotNull}")
    @Schema(example = "ENTIRE_PLACE", description = "숙소 유형 (ENTIRE_PLACE, PRIVATE_ROOM, SHARED_ROOM 등)")
    private AccommodationType accommodationType;

    @NotNull(message = "{accommodation.maxGuests.NotNull}")
    @Positive(message = "{accommodation.maxGuests.Positive}")
    @Schema(example = "4", description = "최대 수용 인원")
    private Integer maxGuests;

    @NotNull(message = "{accommodation.beds.NotNull}")
    @PositiveOrZero(message = "{accommodation.beds.PositiveOrZero}")
    @Schema(example = "2", description = "침실 수")
    private Integer beds;

    @NotNull(message = "{accommodation.bathrooms.NotNull}")
    @PositiveOrZero(message = "{accommodation.bathrooms.PositiveOrZero}")
    @Schema(example = "1", description = "욕실 수")
    private Integer bathrooms;

    @NotNull(message = "{accommodation.pricePerNight.NotNull}")
    @Positive(message = "{accommodation.pricePerNight.Positive}")
    @Schema(example = "120000", description = "1박당 숙박 요금 (원)")
    private BigDecimal pricePerNight;

    @NotNull(message = "{accommodation.cleaningFee.NotNull}")
    @PositiveOrZero(message = "{accommodation.cleaningFee.PositiveOrZero}")
    @Schema(example = "20000", description = "청소비 (원)")
    private BigDecimal cleaningFee;

    @NotNull(message = "{accommodation.status.NotNull}")
    @Schema(example = "ACTIVE", description = "숙소 상태 (ACTIVE, INACTIVE, UNDER_REVIEW, DELETED)")
    private AccommodationStatus status;

    @Schema(example = "15:00", description = "체크인 시간 (HH:mm)")
    private LocalTime checkInTime;

    @Schema(example = "11:00", description = "체크아웃 시간 (HH:mm)")
    private LocalTime checkOutTime;

    // ✅ Entity 변환 메서드
    public Accommodation toEntity() {
        return Accommodation.builder()
            .title(title)
            .description(description)
            .accommodationType(accommodationType)
            .maxGuests(maxGuests)
            .beds(beds)
            .bathrooms(bathrooms)
            .pricePerNight(pricePerNight)
            .cleaningFee(cleaningFee)
            .status(status)
            .checkInTime(checkInTime)
            .checkOutTime(checkOutTime)
            .build();
    }

}
