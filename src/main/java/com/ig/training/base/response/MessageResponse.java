package com.ig.training.base.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MessageResponse {
    private ResponseMessage response = new ResponseMessage().success();
}
