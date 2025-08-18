package com.ig.training.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@NoArgsConstructor
public class ObjectResponse<T> {
    T results;
    ResponseMessage response = new ResponseMessage().success();

    public ObjectResponse(T results) {
        this.results = results;
    }
}
