package com.pghome.utils;

/**
 * @Auther: tianws
 * @Date: 2018/11/23 15:03
 * @Description: 自定义响应数据结构
 * 这个类是提供给门户，ios，安卓，微信商城用的
 * 门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 其他自行处理
 * 200：表示成功
 * 500：表示错误，错误信息在msg字段中
 * 501：bean验证错误，不管多少个错误都以map形式返回
 * 502：拦截器拦截到用户token出错
 * 555：异常抛出信息
 */
public class ResultUtil {

    //响应业务状态
    private Integer code;

    //响应消息
    private String message;

    //响应中的数据
    private Object data;

    public ResultUtil(){

    }

    public ResultUtil(Integer code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultUtil(Object data){
        this.code = 200;
        this.message = "ok";
        this.data = data;
    }

    public static ResultUtil ok(){
        return new ResultUtil(null);
    }

    public static ResultUtil ok(Object data){
        return new ResultUtil(data);
    }

    public static ResultUtil error(String message){
        return new ResultUtil(200,message,null);
    }

    public static ResultUtil error(Integer code, String message){
       return new ResultUtil(code,message,null);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
