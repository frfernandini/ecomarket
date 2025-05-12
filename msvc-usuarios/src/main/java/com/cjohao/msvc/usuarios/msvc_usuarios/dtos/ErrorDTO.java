package com.cjohao.msvc.usuarios.msvc_usuarios.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Setter
@Getter
public class ErrorDTO {
    private Integer status;
    private Date date;
    private Map<String,String> errors;

    @Override
    public String toString(){
        return "{" +
                "status:"+status+
                "date:"+date+
                "erros:"+errors+
                "}";
    }
}
