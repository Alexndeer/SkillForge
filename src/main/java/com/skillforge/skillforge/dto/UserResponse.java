package com.skillforge.skillforge.dto;

import java.time.OffsetDateTime;

public record UserResponse(
        Long id,
        String email,
        OffsetDateTime createdAt
) {}
