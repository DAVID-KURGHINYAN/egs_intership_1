package com.egs.employee.model;

import lombok.Data;

@Data
public class ResponseModel<T> {
    public T data;
    public String message;
    public boolean success;
    public int statusCode;
}
