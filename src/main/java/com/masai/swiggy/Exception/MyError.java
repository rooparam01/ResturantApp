package com.masai.swiggy.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyError {
    private String timeStamp;
    private String message;
    private String info;
}
