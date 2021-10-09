package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wwjie
 * @date 2021/9/25 21:52
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> { //泛型：如果装的payment 返回payment,装的order 返回order
    private Integer code;
    private String message;
    private T data;


    public CommonResult(Integer code, String message){
        this(code,message,null);  //null 爆红是因为idea没有安装lombok插件
    }
}
