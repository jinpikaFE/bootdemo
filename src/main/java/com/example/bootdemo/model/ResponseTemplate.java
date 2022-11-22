package com.example.bootdemo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseTemplate<T> {
    public Integer code;
    public String message;
    public T data;
}
