package com.cwnu.domain.accommodation.repository;

import com.cwnu.domain.accommodation.dto.AccommodationResponse;
import com.cwnu.domain.accommodation.dto.AccommodationSearchCondition;
import com.cwnu.domain.accommodation.dto.QAccommodationResponse;
import com.cwnu.domain.accommodation.entity.AccommodationStatus;
import com.cwnu.domain.accommodation.entity.AccommodationType;
import com.cwnu.domain.accommodation.entity.QAccommodation;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

import static com.cwnu.domain.accommodation.entity.QAccommodation.*;

@Repository
@RequiredArgsConstructor
public class AccommodationRepositoryCustomImpl implements AccommodationRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<AccommodationResponse> search(AccommodationSearchCondition condition, Pageable pageable) {
        List<AccommodationResponse> content = jpaQueryFactory
                .select(
                        new QAccommodationResponse(
                                accommodation.id.as("accommodationId"),
                                accommodation.title,
                                accommodation.description,
                                accommodation.accommodationType,
                                accommodation.maxGuests,
                                accommodation.beds,
                                accommodation.bathrooms,
                                accommodation.pricePerNight,
                                accommodation.cleaningFee,
                                accommodation.status,
                                accommodation.checkInTime,
                                accommodation.checkOutTime,
                                accommodation.createdAt,
                                accommodation.updatedAt

                        ))
                .from(accommodation)
                .where(
                        titleContains(condition.getTitle()),
                        statusEq(condition.getStatus()),
                        priceGoe(condition.getMinPrice()),
                        priceLoe(condition.getMaxPrice()),
                        typeEq(condition.getAccommodationType()),
                        maxGuestsGoe(condition.getMinGuests()),
                        minGuestsLoe(condition.getMaxGuests())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(content, pageable, content.size());
    }

    private BooleanExpression titleContains(String title) {
        return StringUtils.hasText(title) ? accommodation.title.contains(title) : null;
    }

    private BooleanExpression minGuestsLoe(Integer maxGuests) {
        return maxGuests != null ? accommodation.maxGuests.loe(maxGuests) : null; // 최소 수용 인원
    }

    private BooleanExpression maxGuestsGoe(Integer minGuests) {
        return minGuests != null ? accommodation.maxGuests.goe(minGuests) : null; // 최대 수용 인원
    }

    private BooleanExpression typeEq(AccommodationType accommodationType) {
        return accommodationType != null ? accommodation.accommodationType.eq(accommodationType) : null;
    }

    private BooleanExpression priceGoe(BigDecimal minPrice) {
        return minPrice != null ? accommodation.pricePerNight.goe(minPrice) : null;
    }

    private BooleanExpression priceLoe(BigDecimal maxPrice) {
        return maxPrice != null ? accommodation.pricePerNight.loe(maxPrice) : null;
    }

    private BooleanExpression statusEq(AccommodationStatus status) {
        return status != null ? accommodation.status.eq(status) : null;
    }


}
