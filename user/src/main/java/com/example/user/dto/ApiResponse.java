package com.example.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    @ApiModelProperty(example = "200")
    private final int status;
    private final T data;
    
    public ApiResponse(int status , T data) {
        this.status = status;
        this.data = data;
    }

}
