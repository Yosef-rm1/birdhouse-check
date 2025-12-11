package com.birdhousecheck;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IconSize
{
    SMALL(16),
    MEDIUM(32),
    LARGE(48);

    private final int size;
}