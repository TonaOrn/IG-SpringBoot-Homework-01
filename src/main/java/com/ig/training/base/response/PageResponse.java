package com.ig.training.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> results;
    private long total;
    private ResponseMessage response = new ResponseMessage().success();

    public PageResponse(List<T> results, long total) {
        this.results = results;
        this.total = total;
    }
}
