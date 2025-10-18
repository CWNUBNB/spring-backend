package com.cwnu.domain.accommodation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccommodationType {

    ENTIRE_PLACE("전체 숙소"),
    PRIVATE_ROOM("개인실"),
    SHARED_ROOM("다인실");

    private final String description;

}
