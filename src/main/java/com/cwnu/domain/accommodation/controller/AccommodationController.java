package com.cwnu.domain.accommodation.controller;

import com.cwnu.domain.accommodation.dto.AccommodationCreateRequest;
import com.cwnu.domain.accommodation.dto.AccommodationResponse;
import com.cwnu.domain.accommodation.dto.AccommodationSearchCondition;
import com.cwnu.domain.accommodation.service.AccommodationService;
import com.cwnu.global.response.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "02. Accommodation 테이블 API", description = "숙소 관련 API")
@RestController
@RequestMapping("/api/accommodation")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;

    @Operation(summary = "숙소 등록", description = "새로운 숙소를 등록합니다. (ROLE_HOST, ROLE_ADMIN 권한 필요)")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "숙소 등록 성공"),
        @ApiResponse(responseCode = "400", description = "요청 데이터 검증 실패"),
        @ApiResponse(responseCode = "401", description = "인증 실패 또는 토큰 만료"),
        @ApiResponse(responseCode = "403", description = "접근 권한 없음")
    })
    @Secured("ROLE_HOST")
    @PostMapping
    public ResponseEntity<ResponseDto<Void>> createAccommodation(
        @Valid @RequestBody AccommodationCreateRequest request
    ) {
        accommodationService.createAccommodation(request);
        return ResponseDto.created();
    }

    @Operation(summary = "숙소 전체 조회", description = "숙소 정보를 전체 조회합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "조회 성공"),
        @ApiResponse(responseCode = "404", description = "숙소를 찾을 수 없음")
    })

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<AccommodationResponse>>> getAllAccommodations(
    ) {
        List<AccommodationResponse> response = accommodationService.getAllAccommodations();
        return ResponseDto.ok(response);
    }

    @Operation(summary = "숙소 전체 조회 + 검색 조건 + 페이징", description = "숙소 정보를 전체 조회합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "조회 성공"),
        @ApiResponse(responseCode = "404", description = "숙소를 찾을 수 없음")
    })
    @GetMapping("/list/search")
    public ResponseEntity<ResponseDto<Page<AccommodationResponse>>> searchAccommodations(
        @ParameterObject AccommodationSearchCondition condition,
        @ParameterObject Pageable pageable
    ) {
        Page<AccommodationResponse> response = accommodationService.searchAccommodations(condition,
            pageable);
        return ResponseDto.ok(response);
    }


    @Operation(summary = "숙소 단건 조회", description = "숙소 ID로 숙소 상세 정보를 조회합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "조회 성공"),
        @ApiResponse(responseCode = "404", description = "숙소를 찾을 수 없음")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<AccommodationResponse>> getAccommodation(
        @Parameter(description = "숙소 ID", example = "1")
        @PathVariable Long id
    ) {
        AccommodationResponse response = accommodationService.getAccommodationById(id);
        return ResponseDto.ok(response);
    }

    @Operation(summary = "숙소 삭제", description = "숙소 ID로 숙소를 삭제합니다. (ROLE_HOST, ROLE_ADMIN 권한 필요)")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "삭제 성공"),
        @ApiResponse(responseCode = "404", description = "숙소를 찾을 수 없음"),
        @ApiResponse(responseCode = "401", description = "인증 실패 또는 토큰 만료"),
        @ApiResponse(responseCode = "403", description = "접근 권한 없음")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteAccommodation(
        @Parameter(description = "숙소 ID", example = "1")
        @PathVariable Long id
    ) {
        accommodationService.deleteAccommodation(id);
        return ResponseDto.noContent();
    }

}
