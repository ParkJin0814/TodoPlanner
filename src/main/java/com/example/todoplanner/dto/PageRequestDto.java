package com.example.todoplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageRequestDto {
    private int page;
    private int size;

    public long getOffset() {
        return (long) (page-1) * size;
    }
}