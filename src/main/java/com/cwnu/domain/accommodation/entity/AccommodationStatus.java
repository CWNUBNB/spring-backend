package com.cwnu.domain.accommodation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccommodationStatus {

    ACTIVE("활성"),
    INACTIVE("비활성");

    private final String description;
}
