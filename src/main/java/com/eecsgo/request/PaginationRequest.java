package com.eecsgo.request;

import lombok.Data;

@Data
public class PaginationRequest {
    private Integer pageSize;
    private Integer pageIndex;
}
