package com.cwnu.domain.accommodation.service;

import com.cwnu.domain.accommodation.dto.AccommodationCreateRequest;
import com.cwnu.domain.accommodation.dto.AccommodationResponse;
import com.cwnu.domain.accommodation.dto.AccommodationSearchCondition;
import com.cwnu.domain.accommodation.entity.Accommodation;
import com.cwnu.domain.accommodation.repository.AccommodationRepository;
import com.cwnu.global.exception.custom.CustomRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.cwnu.global.exception.ApiExceptionCode.ACCOMMODATION_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    /**
     * 숙소 저장
     * @param dto 숙소 생성 요청 DTO
     */
    @Transactional
    public void createAccommodation(AccommodationCreateRequest dto) {
        Accommodation accommodation = dto.toEntity();
        accommodationRepository.save(accommodation);
    }

    /**
     * 숙소 단건 조회
     * @param id 숙소 ID
     * @return 숙소 응답 DTO
     */
    public AccommodationResponse getAccommodationById(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new CustomRuntimeException(ACCOMMODATION_NOT_FOUND));
        return AccommodationResponse.fromEntity(accommodation);
    }

    /**
     * 모든 숙소 조회
     * @return 숙소 응답 DTO 리스트
     */
    public List<AccommodationResponse> getAllAccommodations() {
        List<Accommodation> accommodations = accommodationRepository.findAll();
        return accommodations.stream()
                .map(AccommodationResponse::fromEntity)
                .toList();
    }

    /**
     * 숙소 검색 및 페이징
     * @param condition 검색 조건
     * @param pageable 페이징 정보
     * @return 숙소 응답 DTO 페이지
     */
    public Page<AccommodationResponse> searchAccommodations(AccommodationSearchCondition condition, Pageable pageable) {
        return accommodationRepository.search(condition, pageable);
    }

    /**
     * 숙소 삭제
     * @param id 숙소 ID
     */
    @Transactional
    public void deleteAccommodation(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new CustomRuntimeException(ACCOMMODATION_NOT_FOUND));
        accommodationRepository.delete(accommodation);
    }


}
