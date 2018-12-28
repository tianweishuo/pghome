package com.pghome.enums.driver;

/**
 * 
 * @Description: 发送消息的动作 枚举
 */
public enum MsgActionEnum {
	
	CONNECT(1, "司机上线或重连"),
	CALL_ORDER(2, "发送订单"),
	KEEPALIVE(3,"心跳检查")
	;
	
	public final Integer type;
	public final String content;
	
	MsgActionEnum(Integer type, String content){
		this.type = type;
		this.content = content;
	}
	
	public Integer getType() {
		return type;
	}  
}
