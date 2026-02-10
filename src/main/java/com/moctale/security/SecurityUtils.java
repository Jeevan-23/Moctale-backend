package com.moctale.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.moctale.security.user.CustomUserDetails;

public final class SecurityUtils {

    public static Long getCurrentUserId() {
        Authentication auth =
            SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails user =
            (CustomUserDetails) auth.getPrincipal();

        return user.getId();
    }
}
