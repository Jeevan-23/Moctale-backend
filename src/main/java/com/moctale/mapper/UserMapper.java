package com.moctale.mapper;

import com.moctale.dto.UserProfileResponse;
import com.moctale.models.User;

public final class UserMapper {

    private UserMapper() {}

    public static UserProfileResponse toProfileResponse(User user) {
        UserProfileResponse dto = new UserProfileResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setBio(user.getBio());
        return dto;
    }
}
