package com.pghome.netty;

import java.io.Serializable;

public class DataContent implements Serializable {

	private static final long serialVersionUID = 8021381444738260454L;

	private Integer action;			// 动作类型
	private CallMeg callMeg;		// 乘客
	private DriverMeg driverMeg;	// 司机
	private String extand;			// 扩展字段
	
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}

	public DriverMeg getDriverMeg() {
		return driverMeg;
	}

	public void setDriverMeg(DriverMeg driverMeg) {
		this.driverMeg = driverMeg;
	}

	public CallMeg getCallMeg() {
		return callMeg;
	}

	public void setCallMeg(CallMeg callMeg) {
		this.callMeg = callMeg;
	}

	public String getExtand() {
		return extand;
	}
	public void setExtand(String extand) {
		this.extand = extand;
	}
}
