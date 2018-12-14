package com.pghome.exception;

/**
 * @Auther: tianws
 * @Date: 2018/11/23 15:30
 * @Description:
 */
public class PGException extends RuntimeException{

    private Integer code;

    public PGException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public PGException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
