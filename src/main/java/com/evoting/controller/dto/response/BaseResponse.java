package com.evoting.controller.dto.response;

import com.evoting.util.MessageUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private String result;
    private String reason;

    public BaseResponse(){
        this.result = MessageUtils.SUCCESS;
        this.reason  = "";
    }
    public BaseResponse(String result){
        this.reason = MessageUtils.FAIL;
        this.result = result;
    }

}
