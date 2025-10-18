package com.cwnu.domain.accommodation.repository;

import com.cwnu.domain.accommodation.dto.AccommodationResponse;
import com.cwnu.domain.accommodation.dto.AccommodationSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccommodationRepositoryCustom {

    Page<AccommodationResponse> search(AccommodationSearchCondition condition, Pageable pageable);

}
