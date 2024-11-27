package com.smartlearntool.SmartLearnTool.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomPageResponse<T> {

    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private boolean isLast;
    private List<T> content;
}
