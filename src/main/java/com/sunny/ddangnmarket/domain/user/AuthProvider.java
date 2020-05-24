package com.sunny.ddangnmarket.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthProvider {
    local,
    facebook,
    google,
    github
}