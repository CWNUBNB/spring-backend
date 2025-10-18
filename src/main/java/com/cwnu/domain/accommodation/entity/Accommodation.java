package com.cwnu.domain.accommodation.entity;

import com.cwnu.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Accommodation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 숙소 ID

    @Column(name = "title", nullable = false, length = 200)
    private String title; // 숙소 제목

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // 숙소 설명

    @Enumerated(EnumType.STRING)
    @Column(name = "accommodation_type", nullable = false, length = 30)
    private AccommodationType accommodationType; // 숙소 유형

    @Column(name = "max_guests", nullable = false)
    private Integer maxGuests; // 최대 게스트 수

    @Column(nullable = false)
    private Integer beds; // 침실 수

    @Column(nullable = false)
    private Integer bathrooms; // 욕실 수

    @Column(name = "price_per_night", nullable = false)
    private BigDecimal pricePerNight; // 1박 가격

    @Column(name = "cleaning_fee", nullable = false)
    private BigDecimal cleaningFee; // 청소비

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AccommodationStatus status; // 숙소 상태

    @Column(name = "check_in_time")
    private LocalTime checkInTime; // 체크인 시간

    @Column(name = "check_out_time")
    private LocalTime checkOutTime; // 체크아웃 시간

}
